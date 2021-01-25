/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 * 
 * A tic-tac-toe game on the 3x3 board
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JApplet implements 
ActionListener{
	
JButton squares[];     
JButton newGameButton;
JLabel score;
int emptySquaresLeft=9;

/** 
 * init method is the applet's constructor
 */ 
 public void init(){
	// Get the applet's content pane - 
	// all window components go there
	Container  appletContent = this.getContentPane();
	
	//Set the applet's layout manager, font and background color
	appletContent.setLayout(new BorderLayout());
	appletContent.setBackground(Color.CYAN);
	
	// Create the button New Game and register it 
	// with the action listener 
	newGameButton=new JButton("New Game");
	newGameButton.addActionListener(this);
	
	JPanel topPanel=new JPanel();
	topPanel.add(newGameButton);
	
	appletContent.add(topPanel,"North");
	
	JPanel centerPanel=new JPanel();
	centerPanel.setLayout(new GridLayout(3,3));
	appletContent.add(centerPanel,"Center");
	
	score=new JLabel("Your turn!");
	appletContent.add(score,"South");
	
    //	create an array to hold references to 9 buttons
	squares=new JButton[9];
	  
	// Instantiate the buttons, store the references 
	// to them in the array, register them with the 
	// listeners, paint them in orange and add to panel 
	for(int i=0;i<9;i++){
			squares[i]=new JButton();
			squares[i].addActionListener(this);
			squares[i].setBackground(Color.ORANGE);
			centerPanel.add(squares[i]);
	}
 }
 /**
 * This method will process all action events
 * @param ActionEvent object
 */
 public void actionPerformed(ActionEvent e) {

	JButton theButton = (JButton) e.getSource();
	// Is this a New Game button?
	if (theButton ==newGameButton){
		for(int i=0;i<9;i++){
			squares[i].setEnabled(true);
			squares[i].setText("");
			squares[i].setBackground(Color.ORANGE);
		}

	   emptySquaresLeft=9;
	   score.setText("Your turn!");
	   newGameButton.setEnabled(false);
	   
	   return;  // exit the method here
   }

   String winner = "";
	// Is this one of the squares?	 
     for ( int i=0; i<9; i++ ) {
      if (  theButton == squares[i] ) {
        squares[i].setText("X");
        
        winner = lookForWinner();

        if(!"".equals(winner)){
            endTheGame();
        } else {
            computerMove();
            winner = lookForWinner();
			if ( !"".equals(winner))
            {
              endTheGame();
            }
        }
       break;  
      }
    } // end loop
    
	if ( winner.equals("X") ) {
		score.setText("You won!");
	} else if (winner.equals("O")){
		score.setText("You lost!");
	} else if (winner.equals("T")){
	   score.setText("It's a tie!");
    }
  } // end actionPerformed 

/**
 *  This method is called after every move to see 
 *  if we have a winner. 
 *  It checks every row, column and diagonal to find out 
 *  three squares with the same label (other than blank)
 *  @return "X", "O", "T" for tie or "" for no winner     
 */
  String lookForWinner() {

    String theWinner = "";
    emptySquaresLeft--;
    
    if (emptySquaresLeft==0){
    	return "T";  // it's a tie
    }
   
    // Check the row 1 - array elements 0,1,2  
    if (!squares[0].getText().equals("") &&
          squares[0].getText().equals(squares[1].getText()) &&
          squares[0].getText().equals(squares[2].getText()) ) {
          	
		 theWinner = squares[0].getText();
         highlightWinner(0,1,2);
    // Check the row 2  - array elements 3,4,5
    } else if (!squares[3].getText().equals("")  && 
               squares[3].getText().equals(squares[4].getText()) &&
               squares[3].getText().equals(squares[5].getText())) {
               	
         theWinner = squares[3].getText();
         highlightWinner(3,4,5);
	 // Check the row 3 -  - array elements 6,7,8 
    } else if ( ! squares[6].getText().equals("") && 
                squares[6].getText().equals(squares[7].getText()) &&
                squares[6].getText().equals(squares[8].getText()) ) {
                	
         theWinner = squares[6].getText();
         highlightWinner(6,7,8);
	 // Check the column 1  - array elements 0,3,6
    } else if ( ! squares[0].getText().equals("") && 
                squares[0].getText().equals(squares[3].getText()) &&
                squares[0].getText().equals(squares[6].getText())) {
                	
         theWinner = squares[0].getText();
         highlightWinner(0,3,6);
	 // Check the column 2 - array elements 1,4,7
    } else if ( ! squares[1].getText().equals("") && 
                squares[1].getText().equals(squares[4].getText()) &&
                squares[1].getText().equals(squares[7].getText())) {
                	
         theWinner = squares[1].getText();
         highlightWinner(1,4,7);
    // Check the column 3 - array elements 2,5,8
    } else if (  ! squares[2].getText().equals("") && 
         squares[2].getText().equals(squares[5].getText()) &&
         squares[2].getText().equals(squares[8].getText()) ) {
         theWinner = squares[2].getText();
         highlightWinner(2,5,8);
	 // Check the first diagonal  - array elements 0,4,8 
    } else if ( ! squares[0].getText().equals("") && 
              squares[0].getText().equals(squares[4].getText()) &&
              squares[0].getText().equals(squares[8].getText())) {
         
         theWinner = squares[0].getText();
         highlightWinner(0,4,8);
	 // Check the second diagonal - array elements 2,4,6
    } else if ( ! squares[2].getText().equals("") && 
                squares[2].getText().equals(squares[4].getText()) &&
                squares[2].getText().equals(squares[6].getText()) ) {
                	
         theWinner = squares[2].getText();
         highlightWinner(2,4,6);
    }
    return theWinner;
}

/**
 * This method applies a set of rules to find 
 * the best computer's move. If a good move 
 * can't be found, it picks a random square. 
 */
 void computerMove() {

   int selectedSquare;
	// Computer first tries to find an empty
	// square next the two squares with "O" to win
     selectedSquare = findEmptySquare("O");

    // if can't find two "O", at least try to stop the 
    // opponent from making 3 in a row by placing 
    // "O" next to 2 "X".
    if ( selectedSquare == -1 )
        selectedSquare =  findEmptySquare("X");
        
    // if the selectedSquare is still -1, at least  
    // try to occupy the central square    
    if ( (selectedSquare == -1)&&(squares[4].getText().equals("")) )
       selectedSquare=4;
    
    // no luck with the central either...
    // just get a random square
    if ( selectedSquare == -1 )
       selectedSquare = getRandomSquare();

    squares[selectedSquare].setText("O");
 } 

/**
 * This method checks every row, column and diagonal
 * to see if there are two squares with the same label
 * and an empty square. 
 * @param   give X - for the user, and O for the computer 
 * @return  the number of the empty square to use,
 *          or the negative 1 could not find 2 square
 *          with the same label    
 */
 int findEmptySquare(String player) {
  	
   int weight[] = new int[9];

   for ( int i = 0; i < 9; i++ ) {
        if ( squares[i].getText().equals("O") )
            weight[i] = -1;
        else if ( squares[i].getText().equals("X") )
            weight[i] = 1;
        else
            weight[i] = 0;
   }

   int twoWeights = player.equals("O") ? -2 : 2;

    // See if row 1 has the same 2 squares and a blank
    if ( weight[0] + weight[1] + weight[2] == twoWeights ) {
        if ( weight[0] == 0 )
            return 0;
        else if ( weight[1] == 0 )
            return 1;
        else
            return 2;
    }
   // See if row 2 has the same 2 squares and a blank
    if ( weight[3] +weight[4] + weight[5] == twoWeights ) {
        if ( weight[3] == 0 )
            return 3;
        else if ( weight[4] == 0 )
            return 4;
        else
            return 5;
    }
   // See if row 3 has the same 2 squares and a blank
    if ( weight[6] + weight[7] +weight[8] == twoWeights ) {
        if ( weight[6] == 0 )
            return 6;
        else if ( weight[7] == 0 )
            return 7;
        else
            return 8;
    }
   // See if column 1 has the same 2 squares and a blank
    if ( weight[0] + weight[3] + weight[6] == twoWeights ) {
        if ( weight[0] == 0 )
            return 0;
        else if ( weight[3] == 0 )
            return 3;
        else
            return 6;
    }
   // See if column 2 has the same 2 squares and a blank
    if ( weight[1] +weight[4] + weight[7] == twoWeights ) {
        if ( weight[1] == 0 )
            return 1;
        else if ( weight[4] == 0 )
            return 4;
        else
            return 7;
    }
   // See if column 3 has the same 2 squares and a blank
    if ( weight[2] + weight[5] + weight[8] == twoWeights ) {
        if ( weight[2] == 0 )
            return 2;
        else if ( weight[5] == 0 )
            return 5;
        else
            return 8;
    }
   // See if diagonal 1 has the same 2 squares and a blank
    if ( weight[0] + weight[4] + weight[8] == twoWeights ) {
        if ( weight[0] == 0 )
            return 0;
        else if ( weight[4] == 0 )
            return 4;
        else
            return 8;
    }
   // See if diagonal has the same 2 squares and a blank
    if ( weight[2] + weight[4] + weight[6] == twoWeights ) {
        if ( weight[2] == 0 )
            return 2;
        else if ( weight[4] == 0 )
            return 4;
        else
            return 6;
    }
    // do not have the same two neighbors  
    return -1;
 } // end of findEmptySquare

/**
 * This method selects any empty square  
 * @return a randomly selected square number
 */
 int getRandomSquare() {
    boolean gotEmptySquare = false;
    int selectedSquare = -1;

    do {
	  selectedSquare = (int) (Math.random() * 9 );  
	  if (squares[selectedSquare].getText().equals("")) {
	   gotEmptySquare = true; // the looping will end 
      }
    } while (!gotEmptySquare );

    return selectedSquare;
 } // end getRandomSquare()
 
 /** 
  * This method highlights the winning line
  * @param first square to highlight
  * @param second square to highlight
  * @param third square to highlight
 */
  void highlightWinner(int win1, int win2, int win3) {
    	squares[win1].setBackground(Color.CYAN);
    	squares[win2].setBackground(Color.CYAN);
    	squares[win3].setBackground(Color.CYAN);
  }
/**
 * Disables squares and enable New Game button
 */
 void endTheGame(){
 	for(int i=0;i<9;i++){
	  squares[i].setEnabled(false);
	} 

	newGameButton.setEnabled(true);
  }
}



















































