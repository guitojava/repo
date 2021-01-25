/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
class TooManyBikesException extends Exception{

  // Constructor 
  TooManyBikesException (){
  // Just call the constructor of the superclass
  // and pass to it the error message to display
  super("Can't ship this many bikes in one shipment.");      
  }  
}
