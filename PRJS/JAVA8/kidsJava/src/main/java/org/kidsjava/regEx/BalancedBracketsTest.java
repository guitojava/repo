/*
 * created by leon
 */

/*
 * created by leon
 */

package org.kidsjava.regEx;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class BalancedBracketsTest {

    private BalancedBracketsTest() {};


    public static void main(String[] args) {

        assertEquals(true, isBalanced("{[()]}"));
        assertEquals(false,isBalanced("([}])"));
        assertEquals(false, isBalanced("{ [ } [ ] ( )"));
        assertEquals(true, isBalanced("()[]{}[][]"));
        assertEquals(false, isBalanced("("));
        assertEquals(true, isBalanced("()"));
        assertEquals(true, isBalanced("()[]{}"));

    }
    
//        4.	Write a program to validate a aStringing composed of parenthesis:
//    { [ ( ) ] }
//    A valid aStringing is when the parenthesis is opened and closed in the correct order, for example:
//    { } ( ) [ ] { [ ] }
//    An invalid aStringing:
//    { [ } [ ] ( )
//    For now we have only 3 types of parenthesis: { } [ ] ( ) but we might need to add more types in the future.



        public static boolean isBalanced(String  aString) {

            final Map<Character, Character> parenthesis = new HashMap<>();
            parenthesis.put('[', ']');
            parenthesis.put('{', '}');
            parenthesis.put('(', ')');


            aString = aString.replaceAll("[^(){}\\[\\]]","");
            System.out.print(  aString );

            if ( aString.isEmpty() ) {
                System.out.println(  " TRUE" );
                return true;
            }

            if ((aString.length() % 2) != 0) {
                System.out.println(  " FALSE" );
                return false;
            }

            final Deque<Character> deque = new ArrayDeque<>();
            for (int i = 0; i < aString.length(); i++) {

                if (parenthesis.containsKey(aString.charAt(i))) {
                    deque.addFirst(aString.charAt(i));

//                    Iterator iterator = deque.iterator();
//                    while (iterator.hasNext()) {
//                        System.out.print("\t" + iterator.next());
//                    }

                } else if (aString.charAt(i) != parenthesis.get(deque.removeFirst())) {
                    System.out.println(  " FALSE" );
                    return false;
                }



            }


            System.out.println(  " TRUE" );
            return true;
        }

       

}
