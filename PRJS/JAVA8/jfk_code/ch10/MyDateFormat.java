/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.util.Date;
import java.text.SimpleDateFormat;

public class MyDateFormat {

 public static void main( String [] args ) {
  // Create an object Date 
  // and print it in a default format 		
  Date today = new Date();
  System.out.println( "The date is " + today );
	
  // Format that prints dates like 02-27-04 
  SimpleDateFormat sdf=
              new SimpleDateFormat("MM-dd-yy"); 
  String formattedDate=sdf.format(today);
  System.out.println( "The date(dd-mm-yy) is " 
	                + formattedDate );
	                
  // Format that prints dates like 27-02-2004                
  sdf = new SimpleDateFormat("dd-MM-yyyy"); 
  formattedDate=sdf.format(today);
  System.out.println( "The date(dd-mm-yyyy) is " 
    			    + formattedDate );
							
  // Format that prints dates like Fri, Feb 27, ‘04
  sdf = new SimpleDateFormat("EEE, MMM d, ''yy"); 
  formattedDate=sdf.format(today);
  System.out.println(
  "The date(EEE, MMM d, ''yy) is "+ formattedDate);							
  // Format that prints time  like 07:18:51 AM	 
  sdf = new SimpleDateFormat("hh:mm:ss a"); 
  formattedDate=sdf.format(today);
  System.out.println( "The time(hh:mm:ss a) is " 
		                    + formattedDate );    
 }
}
