/*
 * created by leon
 */

package org.kidsjava.ch5; /**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {
	@Override
	public String toString() {
		return super.toString();
	}


	static 	JLabel label3 = new JLabel("Sum:");


	static JTextField result = new JTextField(10);
	static JFrame frame =new JFrame("My First Calculator");

	public static void main(String[] args) {
  // Create a panel
	JPanel windowContent= new JPanel();
		
  // Set a layout manager for this panel
	FlowLayout fl = new FlowLayout(); 
	windowContent.setLayout(fl);
  // Create controls in memory 
	final JLabel label1 = new JLabel("Number 1:");


	JTextField field1 = new JTextField(10);
	final JLabel label2 = new JLabel("Number 2:");
	JTextField field2 = new JTextField(10);

	JButton go = new JButton("Add");



		go.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{


				String l;



				long lon = 1+2 ;
				result.setText(""+ Long.parseLong(   ""+lon )   );
//				l = label3.setText(  Long.parseLong(  label1.getText() )
//
//						+  Long.parseLong(     label2.getText() )  );


				// display/center the jdialog when the button is pressed
				JDialog d = new JDialog(frame, "Hello", true);
				d.setLocationRelativeTo(frame);
				d.setVisible(true);
			}
		});
  // Add controls to the panel
	windowContent.add(label1); 
	windowContent.add(field1);
	windowContent.add(label2);
	windowContent.add(field2);
	windowContent.add(label3);
	windowContent.add(result);
	windowContent.add(go);



  frame.setContentPane(windowContent);
		
  // set the size and make the window visible
  frame.setSize(400,100);
  frame.setVisible(true);
 }






	static void displayJFrame()
	{
//		frame = new JFrame("Our JButton listener example");

		// create our jbutton
		JButton showDialogButton = new JButton("Click Me");

		// add the listener to the jbutton to handle the "pressed" event
		showDialogButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// display/center the jdialog when the button is pressed
				JDialog d = new JDialog(frame, "Hello", true);
				d.setLocationRelativeTo(frame);
				d.setVisible(true);
			}
		});

		// put the button on the frame
		frame.getContentPane().setLayout(new FlowLayout());
		frame.add(showDialogButton);

		// set up the jframe, then display it
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}






	
  


