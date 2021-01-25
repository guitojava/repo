package engine;
/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import screens.*;
/**
 * This class is a mouse and keyboard listener. 
 * It calculates ball and racket movements, 
 * changes their coordinates.
 */
public class PingPongGameEngine implements Runnable,            
       MouseMotionListener, KeyListener, GameConstants{
    	
  private PingPongGreenTable table;//reference to table
  private int kidRacket_Y = KID_RACKET_Y_START;
  private int computerRacket_Y=COMPUTER_RACKET_Y_START;
  private int kidScore; 
  private int computerScore;
  private int ballX;   // ball's X position
  private int ballY;   // ball's Y position
  private boolean movingLeft = true;
  private boolean ballServed = false;
   
  //Value in pixels of the vertical ball movement     
  private int verticalSlide;  

   // Constructor. Stores a reference to the table
  public PingPongGameEngine(
                        PingPongGreenTable greenTable){
   table = greenTable;
   Thread worker = new Thread(this);
   worker.start();
  }
 // Methods required by MouseMotionListener 
 // interface (some of them are empty, but must be 
 // included in the class anyway)

 public void mouseDragged(MouseEvent e) {
 }
 public void mouseMoved(MouseEvent e) {

  int mouse_Y = e.getY();

   // If a mouse is above the kid's racket 
   // and the racket did not go over the table top
   // move it up, otherwise move it down    		
   if (mouse_Y<kidRacket_Y && kidRacket_Y>TABLE_TOP){
	kidRacket_Y -= RACKET_INCREMENT;
   }else if (kidRacket_Y < TABLE_BOTTOM) {
	kidRacket_Y += RACKET_INCREMENT;
   }
		
   // Set the new position of the racket  on the table 
   table.setKidRacket_Y(kidRacket_Y);
 }
	
   // Methods required by KeyListener interface
 public void keyPressed(KeyEvent e){
   char key = e.getKeyChar();
   if ('n' == key || 'N' == key){ 
	startNewGame();
   } else if ('q' == key || 'Q' == key){
	endGame();
   } else if ('s' == key || 'S' == key){
	  kidServe();
   }
 }

 public void keyReleased(KeyEvent e){}
 
 public void keyTyped(KeyEvent e){}
 
 // Start a new Game   
 public void startNewGame(){
   computerScore=0;
   kidScore=0;
   table.setMessageText("Score Computer: 0  Kid: 0");
   kidServe();
 }

 // End the game
 public void endGame(){
   System.exit(0);
 }

//Method run() is required by Runnable interface  
public void run(){
 	
boolean canBounce=false; 
while (true) {
	
 if(ballServed){ // if ball is moving
		
  //Step 1. Is ball moving o the left?
  if ( movingLeft && ballX > BALL_MIN_X){
     
	canBounce = (ballY >= computerRacket_Y && 
		ballY < (computerRacket_Y + RACKET_LENGTH)?
									true: false);
	ballX-=BALL_INCREMENT;

	// Add up or down slide to any left/right ball  
	// movement 
	ballY-=verticalSlide; 

	table.setBallPosition(ballX,ballY);
	// Can bounce?		
	if (ballX <= COMPUTER_RACKET_X  && canBounce){
	  movingLeft=false;       	
	}
  }

  // Step 2. Is ball moving to the right?
  if ( !movingLeft && ballX <= BALL_MAX_X){
	 canBounce = (ballY >= kidRacket_Y && ballY < 
		  (kidRacket_Y + RACKET_LENGTH)?true:false);
 	    
   ballX+=BALL_INCREMENT;
   table.setBallPosition(ballX,ballY);
   // Can bounce?	   			
   if (ballX >= KID_RACKET_X && canBounce){		  	   movingLeft=true;
   }
  }
		
  // Step 3. Move computer's racket up or down
  //         to block the ball
  if (computerRacket_Y < ballY 
					&& computerRacket_Y < TABLE_BOTTOM){
	  computerRacket_Y +=RACKET_INCREMENT;
	}else if (computerRacket_Y > TABLE_TOP){
	  computerRacket_Y -=RACKET_INCREMENT;
	}
	table.setComputerRacket_Y(computerRacket_Y);

	// Step 4. Sleep a little 	
	try {
	  Thread.sleep(SLEEP_TIME);
	} catch (InterruptedException e) {
	  e.printStackTrace();
	}
	
	// Step 5. Update the score if the ball is in the 
	// green area but is not moving
	 if (isBallOnTheTable()){
	  if (ballX > BALL_MAX_X ){
		computerScore++;
		displayScore();
	  }else if (ballX < BALL_MIN_X){
		kidScore++;
		displayScore();
	  }
	  } 	
	 } // End if ballServed	
	} // End while
   }// End run()

//	 Serve from the current position of the kid's racket
   private void kidServe(){

	 ballServed = true;
	 ballX = KID_RACKET_X-1;
	 ballY=kidRacket_Y;

	 if (ballY > TABLE_HEIGHT/2){
	  verticalSlide=-1;
	 }else{
	  verticalSlide=1;
	 }
	
	 table.setBallPosition(ballX,ballY);
	 table.setKidRacket_Y(kidRacket_Y);
   }

   private void displayScore(){
	 ballServed = false;
 	
	 if (computerScore ==WINNING_SCORE){
	   table.setMessageText("Computer won! " +
					   computerScore +  ":" + kidScore);
	 }else if (kidScore ==WINNING_SCORE){
	   table.setMessageText("You won! "+ kidScore +
								   ":" + computerScore);
	 }else{
	   table.setMessageText("Computer: "+ computerScore
								+  " Kid: " + kidScore);
	 }
   }
 
   // check if ball did not cross the top or bottom 
   // borders of the table
   private boolean isBallOnTheTable(){
	 if (ballY >= BALL_MIN_Y && ballY <= BALL_MAX_Y){
	  return true;
	 }else {
	  return false;
	 }
   }
  }
