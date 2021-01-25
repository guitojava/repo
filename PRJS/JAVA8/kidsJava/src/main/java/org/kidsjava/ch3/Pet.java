/*
 * created by leon
 */

package org.kidsjava.ch3;

/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
public class Pet {
 	 int age;
	 float weight;
	 float height;
	 String color;
   
	 public void sleep(){
		  System.out.println(
						"Good night, see you tomorrow");
	 }
 
	 public void eat(){
	  System.out.println(
	  "I'm so hungry let me have a snack like nachos!");
	 }
 
	 public String say(String aWord){
		String petResponse = "OK!! OK!! " +aWord;
			return petResponse; 
	 }

}
