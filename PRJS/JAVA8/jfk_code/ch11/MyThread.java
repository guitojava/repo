/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
public class MyThread extends Thread{

  public static void main(String[] args) {
	MyThread worker = new MyThread();
	worker.start();
  }
  public void run(){
    // your code goes here
  }
}
