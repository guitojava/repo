/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.util.ArrayList;

public class FishTank {
  public static void main(String[] args) {
	ArrayList fishTank = new ArrayList();
	Fish theFish;
	       
	Fish aFish = new Fish(20);
	
	aFish.color = "Red";
	aFish.weight = 2;
	fishTank.add(aFish);
  
	aFish = new Fish(10); 
	aFish.color = "Green";
	aFish.weight = 5;
	fishTank.add(aFish);
	
	int fishCount = fishTank.size();
	
	for (int i=0;i<fishCount; i++){
 	  theFish = (Fish) fishTank.get(i);
	  System.out.println("Got the " +   
         theFish.color + " fish that weighs " +
           theFish.weight + " pounds. Depth:" +  
                         theFish.currentDepth);
	}
  }
}

