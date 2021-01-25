/*
 * created by leon
 */

package org.kidsjava.ch6; /**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CalculatorEngine_page102 implements ActionListener {
 
 Calculator_page81 parent; // a reference to the Calculator
	
 // Constructor stores the reference to the 
 // Calculator window in  the member variable parent
 CalculatorEngine_page102(Calculator_page81 parent){
   this.parent = parent;
 }

 public void actionPerformed(ActionEvent e){
   // Get the source of this action
   JButton clickedButton =  (JButton) e.getSource();
   	
   // Get the existing text from the Calculator�s
   // display field
   String dispFieldText = parent.displayField.getText();

   // Get the button's label 
   String clickedButtonLabel = clickedButton.getText();
   	
   parent.displayField.setText(dispFieldText + 
								   clickedButtonLabel);
 }
}
