/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.util.ArrayList;

public class ArrayListDemo {
  public static void main(String[] args) {
	// Create and populate an ArrayList
	ArrayList friends = new ArrayList();
	friends.add("Mary");
	friends.add("Ann");
	friends.add("David");
	friends.add("Roy");
	
	// How many friends are there?
	int friendsCount = friends.size();  
	 
    // Print the content of the ArrayList
    for (int i=0; i<friendsCount; i++){
    	System.out.println("Friend #" + i + " is " 
    	    + friends.get(i));
    }
  }
}
