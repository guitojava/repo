/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CalculatorEngine 
                           implements ActionListener {
	
 Calculator parent;  //a reference to Calculator window 
 char selectedAction = ' ';  // +, -, /, or *
	
 double currentResult =0;  
		
// Constructor stores the reference to the Calculator 
// window in the member variable parent
 CalculatorEngine(Calculator parent){
   this.parent = parent;
 }
 
 public void actionPerformed(ActionEvent e){
   	
   // Get the source of this action
   JButton clickedButton =  (JButton) e.getSource();
   String dispFieldText=parent.displayField.getText();

   double displayValue=0;

   //Get the number from  the text field 
   // if it’s not empty
   if (!"".equals(dispFieldText)){
     displayValue= Double.parseDouble(dispFieldText); 
   }
   Object src = e.getSource();

   // For each action button memorize  selected 
   // action +, -, /, or *, store the current value
   // in the currentResult, and  clean up the display
   //  field for entering the next number
   if (src == parent.buttonPlus){
	  selectedAction = '+';
	  currentResult=displayValue;
	  parent.displayField.setText("");
	 } else if (src == parent.buttonMinus){
	  selectedAction = '-';
	  currentResult=displayValue;
	  parent.displayField.setText("");
	  }else if (src == parent.buttonDivide){
	  selectedAction = '/';
	  currentResult=displayValue; 
	  parent.displayField.setText("");
	  } else if (src == parent.buttonMultiply){
	  selectedAction = '*'; 
	  currentResult=displayValue;
	  parent.displayField.setText("");
	  } else if (src == parent.buttonEqual){
	  // Perform the calculations based on selectedAction
	  // update the value of the variable currentResult 
	  // and display the result
	   if (selectedAction=='+'){
		 currentResult+=displayValue;
		// Convert the result to String by concatenating
		// to an empty string and display it 
		 parent.displayField.setText(""+currentResult);
	   }else if (selectedAction=='-'){ 
		 currentResult -=displayValue;
		   parent.displayField.setText(""+currentResult);
	   }else if (selectedAction=='/'){
		 currentResult /=displayValue; 
		   parent.displayField.setText(""+currentResult);
		 }else if (selectedAction=='*'){
		 currentResult*=displayValue; 
		   parent.displayField.setText(""+currentResult);
		 }
	  } else{
	  // For all numeric buttons append the button's
	  // label to the text field 
		String clickedButtonLabel=
							   clickedButton.getText();
	  parent.displayField.setText(dispFieldText + 
									clickedButtonLabel);
	  }
	 }
  }
