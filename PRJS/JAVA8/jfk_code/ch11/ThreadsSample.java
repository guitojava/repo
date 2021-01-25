/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThreadsSample extends JFrame 
             implements ActionListener, Runnable{
	
  // Constructor
  ThreadsSample(){
   // Create a frame with a button and a text field
	GridLayout gl =new GridLayout(2,1); 
	this.getContentPane().setLayout(gl);
	JButton myButton = new JButton("Kill Time");
	myButton.addActionListener(this);
	this.getContentPane().add(myButton);    
	this.getContentPane().add(new JTextField());
  }

  public void actionPerformed(ActionEvent e){
  // Create a thread and execute the kill-time-code
  // without blockiing the window
    Thread worker = new Thread(this);
    worker.start();  // this calls the method run()
  }

  public void run(){
  // Just  kill some time to show that
  // window controls are NOT locked
	for (int i=0; i<30000;i++){
  	  this.setTitle("i="+i);	
	}
   }
    
  public static void main(String[] args) {

	ThreadsSample myWindow = new ThreadsSample();
  // Ensure that the window can be closed 
  // by pressing a little cross in the corner
	myWindow.setDefaultCloseOperation(
                    WindowConstants.EXIT_ON_CLOSE);
		
  // Set the frame's size and make it visible
	myWindow.setBounds(0,0,150, 100);
	myWindow.setVisible(true);
  }
}
