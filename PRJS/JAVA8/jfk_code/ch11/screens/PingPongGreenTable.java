package screens;
/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import engine.PingPongGameEngine;
/**
*  This class paints the green ping pong table,
*  ball, rackets and displays the score
*/
public class PingPongGreenTable extends JPanel 
                            implements GameConstants{	
  private JLabel label;
	
  private int computerRacket_Y = 
                            COMPUTER_RACKET_Y_START;
  private int kidRacket_Y = KID_RACKET_Y_START;
  private int ballX = BALL_START_X;
  private int ballY = BALL_START_Y; 
	
  Dimension preferredSize = new 
                  Dimension(TABLE_WIDTH,TABLE_HEIGHT);
    
  // This method sets the size of the frame.
  // It's called by JVM
  public Dimension getPreferredSize() {
    return preferredSize;
  }
    
  // Constructor. Creates a listener for mouse events 
  PingPongGreenTable(){
 
    PingPongGameEngine gameEngine = 
                        new PingPongGameEngine(this);
    // Listen to mouse movements to move the rackets
    addMouseMotionListener(gameEngine);
    //Listen to the keyboard events 
    addKeyListener(gameEngine);
  }
  // Add a panel with a JLabel to the frame
  void addPaneltoFrame(Container container) {
	 container.setLayout(new BoxLayout(container,
									   BoxLayout.Y_AXIS));
	 container.add(this);
	 label = new JLabel(
	   "Press N for a new game, S to serve or Q to quit");
	 container.add(label);
  }

  // repaint the window. This method is called by JVM 
  // when it needs to refresh the screen or when a 
  // method repaint() is called from PingPointGameEngine 
  public void paintComponent(Graphics g) {
    	
	 super.paintComponent(g);  
	 g.setColor(Color.GREEN);
	 // paint the table green
	 g.fillRect(0,0,TABLE_WIDTH,TABLE_HEIGHT); 
    	
	 g.setColor(Color.yellow);
	 // paint the right racket
	 g.fillRect(KID_RACKET_X, kidRacket_Y,
							 RACKET_WIDTH, RACKET_LENGTH); 
	 g.setColor(Color.blue);
	 // paint the left racket
	 g.fillRect(COMPUTER_RACKET_X, computerRacket_Y,
							  RACKET_WIDTH,RACKET_LENGTH); 
	 // paint the ball
	 g.setColor(Color.red);
	 g.fillOval(ballX,ballY,10,10);     	
	 //draw the white lines
	 g.setColor(Color.white);
	 g.drawRect(10,10,300,200);
	 g.drawLine(160,10,160,210);
	 // Set the focus to the table, so the  key
	 // listenerwill send commands to  the table
	 requestFocus();
  }
    
  // Set the current position of the kid's racket
   public void setKidRacket_Y(int yCoordinate){
	 this.kidRacket_Y = yCoordinate;
	 repaint();
   }
// Return current posiition of the kid's racket
 public int getKidRacket_Y(){
	return kidRacket_Y;
 }

// Set the current position of the computer's racket
 public void setComputerRacket_Y(int yCoordinate){
	this.computerRacket_Y = yCoordinate;
	repaint();
 }
	
 // Set the game's message
 public void setMessageText(String text){
	label.setText(text);
	repaint();
 }
    
 // Set the game's message
 public void setBallPosition(int xPos, int yPos){
	ballX=xPos;
	ballY=yPos;
	repaint();
 }

 public static void main(String[] args) {
 
 // Create an instance of the frame
	JFrame f = new JFrame("Ping Pong Green Table");

 // Ensure that the window can be closed 
 // by pressing a little cross in the corner
	f.setDefaultCloseOperation(
						  WindowConstants.EXIT_ON_CLOSE);
	PingPongGreenTable table = new PingPongGreenTable();
	table.addPaneltoFrame(f.getContentPane());
 
 // Set the frame's size and make it visible
	f.setBounds(0,0,TABLE_WIDTH+5, TABLE_HEIGHT+40);
	f.setVisible(true);
 }
}
