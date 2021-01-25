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

public class NoThreadsSample extends JFrame
                            implements ActionListener{
  // Constructor
  NoThreadsSample(){
  // Create a frame with a button and a text field
	GridLayout gl =new GridLayout(2,1); 
	this.getContentPane().setLayout(gl);
	JButton myButton = new JButton("Kill Time");
	myButton.addActionListener(this);
	this.getContentPane().add(myButton);    
	this.getContentPane().add(new JTextField());
  }
  // Process button clicks
  public void actionPerformed(ActionEvent e){
  // Just  kill some time to show
  // that window controls are locked
    	for (int i=0; i<30000;i++){
    	  this.setTitle("i="+i);
    	}
    }
	
  public static void main(String[] args) {
    // Create an instance of the frame
    NoThreadsSample myWindow = new NoThreadsSample();
    // Ensure that the window can be closed 
    // by pressing a little cross in its corner
    myWindow.setDefaultCloseOperation(
                      WindowConstants.EXIT_ON_CLOSE);
		
    // Set the frame's size – top left corner
    // coordinates, width and height       
    myWindow.setBounds(0,0,150, 100);
    //Make the window visible
    myWindow.setVisible(true);
  }
}
