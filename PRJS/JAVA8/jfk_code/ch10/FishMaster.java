/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FishMaster {

  public static void main(String[] args) {
		
   Fish myFish = new Fish(20);
   String feetString="";
   int feets;
   // create a input stream reader connected to 
   // System.in, and pass it to the buffered reader
	BufferedReader stdin = new BufferedReader
			 (new InputStreamReader(System.in));
		
   // Keep diving until the user presses "Q"
	while (true) {
	  System.out.println("Ready to dive.How deep?");
	  try {
		feetString = stdin.readLine();
		if (feetString.equals("Q")){
		// Exit the program
  		  System.out.println("Good bye!");
		  System.exit(0);
		}else {
	  // Convert the feetString into an integer and
	  // Dive according to the value of variable feet
		 feets = Integer.parseInt(feetString);
		 myFish.dive(feets);
	      }
	   } catch (IOException e) {
			e.printStackTrace();
	   }
	 } // End while
  } // End main
}
