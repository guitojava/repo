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
public class PetMaster {
	public static void main(String[] args) {

	   String petReaction;	

	   Pet myPet = new Pet();

	   myPet.eat();
	   petReaction = myPet.say("Tweet!! Tweet!!");
	   System.out.println(petReaction);
  
	   myPet.sleep();
	  
	  }
}
