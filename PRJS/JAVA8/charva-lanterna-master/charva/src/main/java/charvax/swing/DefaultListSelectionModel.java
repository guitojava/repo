/* class DefaultListSelectionModel
 *
 * Copyright (C) 2001-2003  R M Pitman
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

package charvax.swing;

import java.util.BitSet;
import java.util.EventListener;
import charvax.swing.event.EventListenerList;
import charvax.swing.event.ListSelectionEvent;
import charvax.swing.event.ListSelectionListener;


/**
 * Default data model for list selections.
 */
public class DefaultListSelectionModel implements ListSelectionModel {

    private static final int MIN = -1;
    private static final int MAX = Integer.MAX_VALUE;

    private int selectionMode = MULTIPLE_INTERVAL_SELECTION;
    private int minIndex = MAX;
    private int maxIndex = MIN;
    private int anchorIndex = -1;
    private int leadIndex = -1;
    private int firstAdjustedIndex = MAX;
    private int lastAdjustedIndex = MIN;
    private boolean isAdjusting;

    private int firstChangedIndex = MAX; 
    private int lastChangedIndex = MIN; 

    private BitSet value = new BitSet(32);
    protected EventListenerList listenerList = new EventListenerList();

    protected boolean leadAnchorNotificationEnabled = true;

    public int getMinSelectionIndex() { return isSelectionEmpty() ? -1 : minIndex; }

    public int getMaxSelectionIndex() { return maxIndex; }

    public boolean getValueIsAdjusting() { return isAdjusting; }

    /**
     * Returns the selection mode.
     * @return  one of the these values:
     * <ul>
     * <li>SINGLE_SELECTION
     * <li>SINGLE_INTERVAL_SELECTION
     * <li>MULTIPLE_INTERVAL_SELECTION
     * </ul>
     * @see #getSelectionMode
     */
    public int getSelectionMode() { return selectionMode; }

    /**
     * Sets the selection mode.  The default is
     * MULTIPLE_INTERVAL_SELECTION.
     * @param selectionMode  one of three values:
     * <ul>
     * <li>SINGLE_SELECTION
     * <li>SINGLE_INTERVAL_SELECTION
     * <li>MULTIPLE_INTERVAL_SELECTION
     * </ul>
     * @exception IllegalArgumentException  if <code>selectionMode</code>
     *      is not one of the legal values shown above
     * @see #setSelectionMode
     */
    public void setSelectionMode(int selectionMode) {
        switch (selectionMode) {
        case SINGLE_SELECTION:
        case SINGLE_INTERVAL_SELECTION:
        case MULTIPLE_INTERVAL_SELECTION:
            this.selectionMode = selectionMode;
            break;
        default:
            throw new IllegalArgumentException("invalid selectionMode");
        }
    }

    public boolean isSelectedIndex(int index) {
        return ((index < minIndex) || (index > maxIndex)) ? 
                false : value.get(index);
    }

    public boolean isSelectionEmpty() {
        return (minIndex > maxIndex);
    }

    public void addListSelectionListener(ListSelectionListener l) {
        listenerList.add(ListSelectionListener.class, l);
    }

    public void removeListSelectionListener(ListSelectionListener l) {
        listenerList.remove(ListSelectionListener.class, l);
    }

    /**
     * Returns an array of all the list selection listeners 
     * registered on this <code>DefaultListSelectionModel</code>.
     *
     * @return all of this model's <code>ListSelectionListener</code>s 
     *         or an empty
     *         array if no list selection listeners are currently registered
     *
     * @see #addListSelectionListener
     * @see #removeListSelectionListener
     */
    public ListSelectionListener[] getListSelectionListeners() {
        return (ListSelectionListener[])listenerList.getListeners(
                ListSelectionListener.class);
    }

    /**
     * Notifies listeners that we have ended a series of adjustments. 
     */
    protected void fireValueChanged(boolean isAdjusting) {  
        if (lastChangedIndex == MIN) {
            return; 
        }
        
        /* Change the values before sending the event to the
         * listeners in case the event causes a listener to make
         * another change to the selection.
         */
        int oldFirstChangedIndex = firstChangedIndex;
        int oldLastChangedIndex = lastChangedIndex;
        firstChangedIndex = MAX;
        lastChangedIndex = MIN; 
        fireValueChanged(oldFirstChangedIndex, oldLastChangedIndex, isAdjusting); 
    }


    /**
     * Notifies <code>ListSelectionListeners</code> that the value
     * of the selection, in the closed interval <code>firstIndex</code>,
     * <code>lastIndex</code>, has changed.
     */
    protected void fireValueChanged(int firstIndex, int lastIndex) {
        fireValueChanged(firstIndex, lastIndex, getValueIsAdjusting());
    }

    /**
     * @param firstIndex the first index in the interval
     * @param lastIndex the last index in the interval
     * @param isAdjusting true if this is the final change in a series of
     *      adjustments
     * @see EventListenerList
     */
    protected void fireValueChanged(int firstIndex, int lastIndex, 
            boolean isAdjusting) {
        
        Object[] listeners = listenerList.getListenerList();
        ListSelectionEvent e = null;
    
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListSelectionListener.class) {
                if (e == null) {
                    e = new ListSelectionEvent(this, firstIndex, lastIndex, 
                            isAdjusting);
                }
                
                ((ListSelectionListener)listeners[i+1]).valueChanged(e);
            }
        }
    }

    private void fireValueChanged() {
        if (lastAdjustedIndex == MIN) 
            return;
        
        /* If getValueAdjusting() is true, (eg. during a drag opereration) 
         * record the bounds of the changes so that, when the drag finishes (and 
         * setValueAdjusting(false) is called) we can post a single event 
         * with bounds covering all of these individual adjustments.  
         */ 
        if (getValueIsAdjusting()) { 
            firstChangedIndex = Math.min(firstChangedIndex, firstAdjustedIndex);
            lastChangedIndex = Math.max(lastChangedIndex, lastAdjustedIndex);
        }
        
        /* Change the values before sending the event to the
         * listeners in case the event causes a listener to make
         * another change to the selection.
         */
        int oldFirstAdjustedIndex = firstAdjustedIndex;
        int oldLastAdjustedIndex = lastAdjustedIndex;
        firstAdjustedIndex = MAX;
        lastAdjustedIndex = MIN; 
    
        fireValueChanged(oldFirstAdjustedIndex, oldLastAdjustedIndex);
    }

    /**
     * Returns an array of all the objects currently registered as
     * <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s
     * are registered using the <code>add<em>Foo</em>Listener</code> method.
     * <p>
     * You can specify the <code>listenerType</code> argument
     * with a class literal, such as <code><em>Foo</em>Listener.class</code>.
     * For example, you can query a <code>DefaultListSelectionModel</code>
     * instance <code>m</code>
     * for its list selection listeners
     * with the following code:
     *
     * <pre>ListSelectionListener[] lsls = (ListSelectionListener[])(m.getListeners(ListSelectionListener.class));</pre>
     *
     * If no such listeners exist,
     * this method returns an empty array.
     *
     * @param listenerType  the type of listeners requested;
     *          this parameter should specify an interface
     *          that descends from <code>java.util.EventListener</code>
     * @return an array of all objects registered as
     *          <code><em>Foo</em>Listener</code>s
     *          on this model,
     *          or an empty array if no such
     *          listeners have been added
     * @exception ClassCastException if <code>listenerType</code> doesn't
     *          specify a class or interface that implements
     *          <code>java.util.EventListener</code>
     *
     * @see #getListSelectionListeners
     */
    public EventListener[] getListeners(Class listenerType) { 
        return listenerList.getListeners(listenerType); 
    }

    // Updates first and last change indices
    private void markAsDirty(int r) {
        firstAdjustedIndex = Math.min(firstAdjustedIndex, r);
        lastAdjustedIndex  = Math.max(lastAdjustedIndex, r);
    }

    // Sets the state at this index and update all relevant state.
    private void set(int r) {
        if (value.get(r))
            return;
        
        value.set(r);
        markAsDirty(r);

        // Update minimum and maximum indices
        minIndex = Math.min(minIndex, r);
        maxIndex = Math.max(maxIndex, r);
    }

    // Clears the state at this index and update all relevant state.
    private void clear(int r) {
        if (!value.get(r))
            return;
        
        value.clear(r);
        markAsDirty(r);

        // Update minimum and maximum indices
        /*
           If (r > minIndex) the minimum has not changed.
           The case (r < minIndex) is not possible because r'th value was set.
           We only need to check for the case when lowest entry has been cleared,
           and in this case we need to search for the first value set above it.
        */
        if (r == minIndex) {
            for(minIndex = minIndex + 1; minIndex <= maxIndex; minIndex++) {
                if (value.get(minIndex)) {
                    break;
                }
            }
        }
        /*
           If (r < maxIndex) the maximum has not changed.
           The case (r > maxIndex) is not possible because r'th value was set.
           We only need to check for the case when highest entry has been cleared,
           and in this case we need to search for the first value set below it.
        */
        if (r == maxIndex) {
            for(maxIndex = maxIndex - 1; minIndex <= maxIndex; maxIndex--) {
                if (value.get(maxIndex)) {
                    break;
                }
            }
        }
        /* Performance note: This method is called from inside a loop in
           changeSelection() but we will only iterate in the loops
           above on the basis of one iteration per deselected cell - in total.
           Ie. the next time this method is called the work of the previous
           deselection will not be repeated.
    
           We also don't need to worry about the case when the min and max
           values are in their unassigned states. This cannot happen because
           this method's initial check ensures that the selection was not empty
           and therefore that the minIndex and maxIndex had 'real' values.
    
           If we have cleared the whole selection, set the minIndex and maxIndex
           to their cannonical values so that the next set command always works
           just by using Math.min and Math.max.
        */
        if (isSelectionEmpty()) {
            minIndex = MAX;
            maxIndex = MIN;
        }
    }

    /**
     * Sets the value of the leadAnchorNotificationEnabled flag.
     * @see     #isLeadAnchorNotificationEnabled()
     */
    public void setLeadAnchorNotificationEnabled(boolean flag) {
        leadAnchorNotificationEnabled = flag;
    }

    /**
     * Returns the value of the <code>leadAnchorNotificationEnabled</code> flag.
     * When <code>leadAnchorNotificationEnabled</code> is true the model
     * generates notification events with bounds that cover all the changes to
     * the selection plus the changes to the lead and anchor indices.
     * Setting the flag to false causes a narrowing of the event's bounds to
     * include only the elements that have been selected or deselected since
     * the last change. Either way, the model continues to maintain the lead
     * and anchor variables internally. The default is true.
     * @return  the value of the <code>leadAnchorNotificationEnabled</code> flag
     * @see     #setLeadAnchorNotificationEnabled(boolean)
     */
    public boolean isLeadAnchorNotificationEnabled() {
        return leadAnchorNotificationEnabled;
    }

    private void updateLeadAnchorIndices(int anchorIndex, int leadIndex) {
        if (leadAnchorNotificationEnabled) {
            if (this.anchorIndex != anchorIndex) {
                if (this.anchorIndex != -1) { // The unassigned state.
                    markAsDirty(this.anchorIndex);
                }
                markAsDirty(anchorIndex);
            }

            if (this.leadIndex != leadIndex) {
                if (this.leadIndex != -1) { // The unassigned state.
                    markAsDirty(this.leadIndex);
                }
                markAsDirty(leadIndex);
            }
        }
        this.anchorIndex = anchorIndex;
        this.leadIndex = leadIndex;
    }

    private boolean contains(int a, int b, int i) {
        return (i >= a) && (i <= b);
    }

    private void changeSelection(int clearMin, int clearMax,
                                 int setMin, int setMax, boolean clearFirst) {
        
        for(int i = Math.min(setMin, clearMin); 
                i <= Math.max(setMax, clearMax); i++) {

            boolean shouldClear = contains(clearMin, clearMax, i);
            boolean shouldSet = contains(setMin, setMax, i);

            if (shouldSet && shouldClear) {
                if (clearFirst) {
                    shouldClear = false;
                }
                else {
                    shouldSet = false;
                }
            }

            if (shouldSet) {
                set(i);
            }
            if (shouldClear) {
                clear(i);
            }
        }
        
        fireValueChanged();
    }

   /**
     * Change the selection with the effect of first clearing the values in the
     * inclusive range [clearMin, clearMax] then setting the values in the
     * inclusive range [setMin, setMax]. Do this in one pass so that no values
     * are cleared if they would later be set.
     */
    private void changeSelection(int clearMin, int clearMax, int setMin, int setMax) {
        changeSelection(clearMin, clearMax, setMin, setMax, true);
    }

    public void clearSelection() {
        removeSelectionInterval(minIndex, maxIndex);
    }

    public void setSelectionInterval(int index0, int index1) {
        if (index0 == -1 || index1 == -1) {
            return;
        }

        if (getSelectionMode() == SINGLE_SELECTION) {
            index0 = index1;
        }

        updateLeadAnchorIndices(index0, index1);

        int clearMin = minIndex;
        int clearMax = maxIndex;
        int setMin = Math.min(index0, index1);
        int setMax = Math.max(index0, index1);
        changeSelection(clearMin, clearMax, setMin, setMax);
    }

    public void addSelectionInterval(int index0, int index1) {
        if (index0 == -1 || index1 == -1)
            return;

        if (getSelectionMode() != MULTIPLE_INTERVAL_SELECTION) {
            setSelectionInterval(index0, index1);
            return;
        }

        updateLeadAnchorIndices(index0, index1);

        int clearMin = MAX;
        int clearMax = MIN;
        int setMin = Math.min(index0, index1);
        int setMax = Math.max(index0, index1);
        changeSelection(clearMin, clearMax, setMin, setMax);
    }

    public void removeSelectionInterval(int index0, int index1) {
        if (index0 == -1 || index1 == -1)
            return;

        updateLeadAnchorIndices(index0, index1);

        int clearMin = Math.min(index0, index1);
        int clearMax = Math.max(index0, index1); 
        int setMin = MAX;
        int setMax = MIN;
    
            // If the removal would produce to two disjoint selections in a mode 
        // that only allows one, extend the removal to the end of the selection. 
        if (getSelectionMode() != MULTIPLE_INTERVAL_SELECTION && 
               clearMin > minIndex && clearMax < maxIndex) {
            
            clearMax = maxIndex; 
        }

        changeSelection(clearMin, clearMax, setMin, setMax);
    }

    private void setState(int index, boolean state) {
        if (state) {
            set(index); 
        }
        else {
            clear(index); 
        }
    }

    /**
     * Insert length indices beginning before/after index. If the value 
     * at index is itself selected and the selection mode is not
     * SINGLE_SELECTION, set all of the newly inserted items as selected.
     * Otherwise leave them unselected. This method is typically
     * called to sync the selection model with a corresponding change
     * in the data model.
     */
    public void insertIndexInterval(int index, int length, boolean before) {
        /* The first new index will appear at insMinIndex and the last
         * one will appear at insMaxIndex
         */
        int insMinIndex = (before) ? index : index + 1;
        int insMaxIndex = (insMinIndex + length) - 1;
    
        /* Right shift the entire bitset by length, beginning with
         * index-1 if before is true, index+1 if it's false (i.e. with
         * insMinIndex).
         */
        for(int i = maxIndex; i >= insMinIndex; i--) {
            setState(i + length, value.get(i)); 
        }
    
        /* Initialize the newly inserted indices.
         */
        boolean setInsertedValues = ((getSelectionMode() == SINGLE_SELECTION) ?
                                        false : value.get(index)); 
        
        for(int i = insMinIndex; i <= insMaxIndex; i++) { 
            setState(i, setInsertedValues); 
        }
        
        fireValueChanged();
    }

    /**
     * Remove the indices in the interval index0,index1 (inclusive) from
     * the selection model.  This is typically called to sync the selection
     * model width a corresponding change in the data model.  Note
     * that (as always) index0 need not be <= index1.
     */
    public void removeIndexInterval(int index0, int index1) {
        int rmMinIndex = Math.min(index0, index1);
        int rmMaxIndex = Math.max(index0, index1);
        int gapLength = (rmMaxIndex - rmMinIndex) + 1;
    
        /* Shift the entire bitset to the left to close the index0, index1
         * gap.
         */
        for(int i = rmMinIndex; i <= maxIndex; i++) {
            setState(i, value.get(i + gapLength)); 
        }
            fireValueChanged();
    }

    public void setValueIsAdjusting(boolean isAdjusting) {
        if (isAdjusting != this.isAdjusting) {
            this.isAdjusting = isAdjusting;
            this.fireValueChanged(isAdjusting);
        }
    }

    /**
     * Returns a string that displays and identifies this
     * object's properties.
     *
     * @return a <code>String</code> representation of this object
     */
    public String toString() {
        String s = ((getValueIsAdjusting()) ? "~" : "=") + value.toString();
        return getClass().getName() + " " + Integer.toString(hashCode()) 
                + " " + s;
    }

    /**
     * Returns a clone of this selection model with the same selection.
     * <code>listenerLists</code> are not duplicated.
     *
     * @exception CloneNotSupportedException if the selection model does not
     *    both (a) implement the Cloneable interface and (b) define a
     *    <code>clone</code> method.
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultListSelectionModel clone = (DefaultListSelectionModel)super.clone();
        clone.value = (BitSet)value.clone();
        clone.listenerList = new EventListenerList();
        return clone;
    }

    public int getAnchorSelectionIndex() {
        return anchorIndex;
    }

    public int getLeadSelectionIndex() {
        return leadIndex;
    }

    /**
     * Set the anchor selection index, leaving all selection values unchanged. 
     * If leadAnchorNotificationEnabled is true, send a notification covering 
     * the old and new anchor cells. 
     *
     * @see #getAnchorSelectionIndex     
     * @see #setLeadSelectionIndex
     */   
    public void setAnchorSelectionIndex(int anchorIndex) { 
        updateLeadAnchorIndices(anchorIndex, this.leadIndex); 
        this.anchorIndex = anchorIndex;
        fireValueChanged(); 
    }

    /**
     * Sets the lead selection index, ensuring that values between the 
     * anchor and the new lead are either all selected or all deselected. 
     * If the value at the anchor index is selected, first clear all the 
     * values in the range [anchor, oldLeadIndex], then select all the values 
     * values in the range [anchor, newLeadIndex], where oldLeadIndex is the old
     * leadIndex and newLeadIndex is the new one. 
     * <p> 
     * If the value at the anchor index is not selected, do the same thing in 
     * reverse selecting values in the old range and deslecting values in the
     * new one. 
     * <p>
     * Generate a single event for this change and notify all listeners. 
     * For the purposes of generating minimal bounds in this event, do the 
     * operation in a single pass; that way the first and last index inside the 
     * ListSelectionEvent that is broadcast will refer to cells that actually 
     * changed value because of this method. If, instead, this operation were 
     * done in two steps the effect on the selection state would be the same 
     * but two events would be generated and the bounds around the changed 
     * values would be wider, including cells that had been first cleared only 
     * to later be set. 
     * <p>
     * This method can be used in the <code>mouseDragged</code> method
     * of a UI class to extend a selection.  
     *
     * @see #getLeadSelectionIndex     
     * @see #setAnchorSelectionIndex
     */   
    public void setLeadSelectionIndex(int leadIndex) { 
        int anchorIndex = this.anchorIndex;

        if ((anchorIndex == -1) || (leadIndex == -1)) { 
            return; 
        }
    
        if (this.leadIndex == -1) { 
            this.leadIndex = leadIndex; 
        }
    
        boolean shouldSelect = value.get(this.anchorIndex); 

        if (getSelectionMode() == SINGLE_SELECTION) { 
            anchorIndex = leadIndex;
            shouldSelect = true; 
        }

        int oldMin = Math.min(this.anchorIndex, this.leadIndex);
        int oldMax = Math.max(this.anchorIndex, this.leadIndex);
        int newMin = Math.min(anchorIndex, leadIndex);
        int newMax = Math.max(anchorIndex, leadIndex);
    
        updateLeadAnchorIndices(anchorIndex, leadIndex); 

        if (shouldSelect) {
            changeSelection(oldMin, oldMax, newMin, newMax);
        } else {
            changeSelection(newMin, newMax, oldMin, oldMax, false);
        }
    }
}
