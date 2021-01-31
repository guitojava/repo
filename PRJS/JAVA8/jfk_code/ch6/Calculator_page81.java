/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculator_page81 {
    // Declaration of all calculator's components.
	JPanel windowContent;
	JTextField displayField;
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton buttonPoint;
	JButton buttonEqual;
	JPanel p1;

      // Constructor  creates the components in memory
	// and adds the to the frame using combination of 
	// Borderlayout and Gridlayout
	Calculator_page81(){
	  windowContent= new JPanel();
		
	// Set the layout manager for this panel
	  BorderLayout bl = new BorderLayout(); 
	  windowContent.setLayout(bl);

     // Create the display field and place it in the   
     // North area of the window
	  displayField = new JTextField(30);
	  windowContent.add("North",displayField);
     
     // Create buttons using constructor of the  
     // class JButton that takes the label of the 
     // button as a parameter 
	  button0=new JButton("0");
	  button1=new JButton("1");
	  button2=new JButton("2");
	  button3=new JButton("3");
	  button4=new JButton("4");
	  button5=new JButton("5");
	  button6=new JButton("6");
		  button7=new JButton("7");
		  button8=new JButton("8");
		  button9=new JButton("9");
		  buttonPoint = new JButton(".");
		  buttonEqual=new JButton("=");
								
		 // Create the panel with the GridLayout 
		 // that will contain 12 buttons - 10 numeric  
		 // ones, and  buttons with the point and the 
		 // equal sign   
		  p1 = new JPanel();
		  GridLayout gl =new GridLayout(4,3); 
		  p1.setLayout(gl);
		 //  Add window controls to the panel p1
		  p1.add(button1);
		  p1.add(button2);
		  p1.add(button3);
		  p1.add(button4);
		  p1.add(button5);
		  p1.add(button6);
		  p1.add(button7);
		  p1.add(button8);
		  p1.add(button9);
		  p1.add(button0);
		  p1.add(buttonPoint);
		  p1.add(buttonEqual);

		 // Add the panel p1 to the center area 
		 // of the window		  
			windowContent.add("Center",p1);
		 //Create the frame and set its content pane	               
			JFrame frame = new JFrame("Calculator");
		  frame.setContentPane(windowContent);
		 // set the size of the window to be big enough	  
		 // to accomodate all controls
			frame.pack(); 
		 // Finally, display the window
		  frame.setVisible(true);
		  }

		  public static void main(String[] args) {
			  Calculator_page81 calc = new Calculator_page81();
		  }
	  }
