/* Copyright (C) 2015 charva-lanterna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package charvax.swing.event;

import java.lang.reflect.Array;
import java.util.EventListener;


/**
 * A class that holds a list of EventListeners. A single instance can be used to
 * hold all listeners (of all types) for the instance using the list. It is the
 * responsiblity of the class using the EventListenerList to provide type-safe
 * API (preferably conforming to the JavaBeans spec) and methods which dispatch
 * event notification methods to appropriate Event Listeners on the list.
 * <p>
 * The main benefits that this class provides are that it is relatively cheap in
 * the case of no listeners, and it provides serialization for event-listener
 * lists in a single place, as well as a degree of MT safety (when used
 * correctly).
 * <p>
 * Usage example: Say one is defining a class that sends out FooEvents, and one
 * wants to allow users of the class to register FooListeners and receive
 * notification when FooEvents occur. The following should be added to the class
 * definition:
 * <pre>
 * EventListenerList listenerList = new EventListenerList();
 * FooEvent fooEvent = null;
 * 
 * public void addFooListener(FooListener l) {
 *     listenerList.add(FooListener.class, l);
 * }
 * 
 * public void removeFooListener(FooListener l) {
 *     listenerList.remove(FooListener.class, l);
 * }
 * 
 * // Notify all listeners that have registered interest for
 * // notification on this event type.  The event instance 
 * // is lazily created using the parameters passed into 
 * // the fire method.
 * 
 * protected void fireFooXXX() {
 *     // Guaranteed to return a non-null array
 *     Object[] listeners = listenerList.getListenerList();
 *     // Process the listeners last to first, notifying
 *     // those that are interested in this event
 *     for (int i = listeners.length - 2; i &gt;= 0; i -= 2) {
 *         if (listeners[i] == FooListener.class) {
 *             // Lazily create the event:
 *             if (fooEvent == null)
 *                 fooEvent = new FooEvent(this);
 *             ((FooListener) listeners[i + 1]).fooXXX(fooEvent);
 *         }
 *     }
 * }
 * </pre>
 * 
 * foo should be changed to the appropriate name, and fireFooXxx to the
 * appropriate method name. One fire method should exist for each notification
 * method in the FooListener interface.
 */
public class EventListenerList {
    
    /** A null array to be shared by all empty listener lists */
    private final static Object[] NULL_ARRAY = new Object[0];
    
    /** The list of ListenerType - Listener pairs */
    protected transient Object[] listenerList = NULL_ARRAY;

    /**
     * Passes back the event listener list as an array of ListenerType-listener
     * pairs. Note that for performance reasons, this implementation passes back
     * the actual data structure in which the listener data is stored
     * internally! This method is guaranteed to pass back a non-null array, so
     * that no null-checking is required in fire methods. A zero-length array of
     * Object should be returned if there are currently no listeners.
     * 
     * WARNING!!! Absolutely NO modification of the data contained in this array
     * should be made -- if any such manipulation is necessary, it should be
     * done on a copy of the array returned rather than the array itself.
     */
    public Object[] getListenerList() {
        return listenerList;
    }

    /**
     * Return an array of all the listeners of the given type.
     * 
     * @return all of the listeners of the specified type.
     * @exception ClassCastException
     *                if the supplied class is not assignable to EventListener
     */
    public EventListener[] getListeners(Class t) {
        Object[] lList = listenerList;
        int n = getListenerCount(lList, t);
        EventListener[] result = (EventListener[]) Array.newInstance(t, n);
        int j = 0;
        for (int i = lList.length - 2; i >= 0; i -= 2) {
            if (lList[i] == t) {
                result[j++] = (EventListener) lList[i + 1];
            }
        }
        return result;
    }

    /**
     * Returns the total number of listeners for this listener list.
     */
    public int getListenerCount() {
        return listenerList.length / 2;
    }

    /**
     * Returns the total number of listeners of the supplied type for this
     * listener list.
     */
    public int getListenerCount(Class t) {
        Object[] lList = listenerList;
        return getListenerCount(lList, t);
    }

    private int getListenerCount(Object[] list, Class t) {
        int count = 0;
        for (int i = 0; i < list.length; i += 2) {
            if (t == (Class) list[i])
                count++;
        }
        return count;
    }

    /**
     * Adds the listener as a listener of the specified type.
     * 
     * @param t  the type of the listener to be added
     * @param l  the listener to be added
     */
    public synchronized void add(Class t, EventListener l) {
        if (l == null) {
            // In an ideal world, we would do an assertion here
            // to help developers know they are probably doing
            // something wrong
            return;
        }
        if (!t.isInstance(l)) {
            throw new IllegalArgumentException("Listener " + l
                    + " is not of type " + t);
        }
        if (listenerList == NULL_ARRAY) {
            // if this is the first listener added,
            // initialize the lists
            listenerList = new Object[] { t, l };
        } else {
            // Otherwise copy the array and add the new listener
            int i = listenerList.length;
            Object[] tmp = new Object[i + 2];
            System.arraycopy(listenerList, 0, tmp, 0, i);

            tmp[i] = t;
            tmp[i + 1] = l;

            listenerList = tmp;
        }
    }

    /**
     * Removes the listener as a listener of the specified type.
     * 
     * @param t  the type of the listener to be removed
     * @param l  the listener to be removed
     */
    public synchronized void remove(Class t, EventListener l) {
        if (l == null) {
            // In an ideal world, we would do an assertion here
            // to help developers know they are probably doing
            // something wrong
            return;
        }
        if (!t.isInstance(l)) {
            throw new IllegalArgumentException("Listener " + l
                    + " is not of type " + t);
        }
        // Is l on the list?
        int index = -1;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if ((listenerList[i] == t)
                    && (listenerList[i + 1].equals(l) == true)) {
                index = i;
                break;
            }
        }

        // If so, remove it
        if (index != -1) {
            Object[] tmp = new Object[listenerList.length - 2];
            // Copy the list up to index
            System.arraycopy(listenerList, 0, tmp, 0, index);
            // Copy from two past the index, up to
            // the end of tmp (which is two elements
            // shorter than the old list)
            if (index < tmp.length)
                System.arraycopy(listenerList, index + 2, tmp, index,
                        tmp.length - index);
            // set the listener array to the new array or null
            listenerList = (tmp.length == 0) ? NULL_ARRAY : tmp;
        }
    }

    /**
     * Returns a string representation of the EventListenerList.
     */
    public String toString() {
        Object[] lList = listenerList;
        String s = "EventListenerList: ";
        s += lList.length / 2 + " listeners: ";
        for (int i = 0; i <= lList.length - 2; i += 2) {
            s += " type " + ((Class) lList[i]).getName();
            s += " listener " + lList[i + 1];
        }
        return s;
    }
}
