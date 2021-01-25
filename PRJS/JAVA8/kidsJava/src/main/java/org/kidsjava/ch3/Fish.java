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
public class Fish extends Pet{
	int currentDepth=0;

	public int dive(int howDeep){
	   currentDepth=currentDepth + howDeep;
	 System.out.println("Diving for " + howDeep +
											  " feet");
	   System.out.println("I'm at " + currentDepth +
						  " feet below sea level");
	 return currentDepth; 
	}

}
