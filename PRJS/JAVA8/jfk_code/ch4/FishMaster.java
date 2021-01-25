/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
public class FishMaster {

  public static void main(String[] args) {
		
	Fish myFish = new Fish();
 
	// Try to have the fish go below 100 feet	
	myFish.dive(2);
	myFish.dive(97);
	myFish.dive(3);
		
	myFish.sleep();
  }
}

