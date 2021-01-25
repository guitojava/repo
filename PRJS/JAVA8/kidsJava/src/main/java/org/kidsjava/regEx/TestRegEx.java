/*
 * created by leon
 */

package org.kidsjava.regEx;

public class TestRegEx {


    public static void main(String[] args) {
        int[] intArray = new int[]{1, 2, 1, 2};
        run();
        //    System.out.println(      ru(  intArray, 44 )    );
// [4,6,3,7]  true
// [3,1,1,1]  false
    }
    public static  void run() {
        String regExp1 = "/*^[\\p{IsLatin}0-9 !@#$%^&*()_+{}|:?]+$/";
        //String regExp = "\\p{IsLatin}*\\d*";
        //    String regExp = "\\b[^q]*";
        //working
        //      String regExp = "^(\\p{IsLatin}*|\\d*|\\s*|\\p{Blank}*|\\p{Punct})*$";
        String regExp = "^(\\p{IsLatin}|[_@().-]|\\d|\\p{Blank})*$";
        String inputText = "()aaa";
        if (inputText.matches(regExp)) {
            System.out.println("TRUE");
        }
        //  https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
//        "Σήμε".matches("\\p{IsLatin}+"); // greek characters do not match
//
//        System.out.println( "".matches("") ) ;
//
//
        System.out.close();
    }

}
