/*
 * created by leon
 */

package org.kidsjava.ch4;

/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 * <p>
 * This is a code sample from  the book
 * Java Programming for Kids, Parents and Grandparents.
 */
public class ReportCard {

    /**
     * This method takes one integer argument - the result of
     * the  test and returns one letter A, B, C or D depending on the argument.
     */
    public char convertGrades(int testResult) {
        char grade;

        if (testResult >= 90) {
            grade = 'A';
        } else if (testResult >= 80 && testResult < 90) {
            grade = 'B';
        } else if (testResult >= 70 && testResult < 80) {
            grade = 'C';
        } else {
            grade = 'D';
        }
        return grade;
    }

    public static void main(String[] args) {

        ReportCard rc = new ReportCard();

        char yourGrade = rc.convertGrades(88);
        System.out.println("Your first grade is " +
                yourGrade);

        yourGrade = rc.convertGrades(79);
        System.out.println("Your second grade is " +
                yourGrade);
    }
}

