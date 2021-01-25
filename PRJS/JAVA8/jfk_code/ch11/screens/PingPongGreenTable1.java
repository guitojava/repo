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
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import engine.PingPongGameEngine1;
/**
*  This class paints a green ping pong table
*  and displays coordinates of the point
*  where the user clicked the mouse  
*/
public class PingPongGreenTable1 extends JPanel              
                            implements GameConstants1{	
    JLabel label;
    public Point point = new Point(0,0); 
	
    public int ComputerRacket_X =15;
    private int kidRacket_Y =KID_RACKET_Y_START;
	
    Dimension preferredSize = new 
              Dimension(TABLE_WIDTH,TABLE_HEIGHT);
    
    // This method sets the size of the frame.
    // It's called by JVM
    public Dimension getPreferredSize() {
    	return preferredSize;
    }
//	Constructor. Creates a listener for mouse events 
	 PingPongGreenTable1(){
 
	   PingPongGameEngine1 gameEngine =
						 new PingPongGameEngine1(this);
	 // Listen to mouse clicks to show its coordinates 
	   addMouseListener(gameEngine);
	 // Listen to mouse movements to move the rackets
	   addMouseMotionListener(gameEngine);
	 }

	// Add a panel with a JLabel to the frame
	 void addPaneltoFrame(Container container) {
	   container.setLayout(new BoxLayout(container,
									   BoxLayout.Y_AXIS));
	 container.add(this);
	 label = new JLabel("Click to see coordinates");
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
		 g.fillRect(KID_RACKET_X_START,kidRacket_Y,5,30); 
		 g.setColor(Color.blue);

		 // paint the left racket
	   g.fillRect(ComputerRacket_X,100,5,30); 
    	
		 g.setColor(Color.red);
		 g.fillOval(25,110,10,10); //paint the ball
    	
		 g.setColor(Color.white);
		 g.drawRect(10,10,300,200);
		 g.drawLine(160,10,160,210);
//		Display a point as a small 2x2 pixels rectangle 
			 if (point != null) {
			  label.setText("Coordinates (x,y): " + 
						   point.x + ", " + point.y);
				g.fillRect(point.x, point.y, 2, 2);
			 }
		 }
    
	  // Set the current position of the kid's racket
		 public void setKidRacket_Y(int xCoordinate){
			 this.kidRacket_Y = xCoordinate;
		 }
    
	  // Return the current position of the kid's racket
		 public int getKidRacket_Y(int xCoordinate){
		  return kidRacket_Y;
		 }

		 public static void main(String[] args) {
		 // Create an instance of the frame
		 JFrame f = new JFrame("Ping Pong Green Table");
		 // Ensure that the window can be closed 
		 // by pressing a little cross in the corner
		 f.setDefaultCloseOperation(
					   WindowConstants.EXIT_ON_CLOSE);
        
		 PingPongGreenTable1 table = 
								 new PingPongGreenTable1();
		 table.addPaneltoFrame(f.getContentPane());
		   // Set the size and make the frame visible
		 f.pack();
		 f.setVisible(true);
		 }
	 }    
