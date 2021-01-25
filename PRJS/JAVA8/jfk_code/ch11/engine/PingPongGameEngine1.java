package engine;
/**
 * @author Yakov Fain  (www.smartdataprocessing.com)
 *
 * This is a code sample from  the book 
 * Java Programming for Kids, Parents and Grandparents.
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import screens.*;

public class PingPongGameEngine1  implements  
       MouseListener, MouseMotionListener, GameConstants1{
    	
 PingPongGreenTable1 table;
 public int kidRacket_Y = KID_RACKET_Y_START;
 // Constructor. Stores a reference to the table
 public PingPongGameEngine1(PingPongGreenTable1 greenTable){
   	table = greenTable;
 }
// Methods required by the MouseListener interface
 public void mousePressed(MouseEvent e) {
 // Get X and Y coordinates of the mouse pointer
 // and set it to the "white point" on the table 
	table.point.x = e.getX();
	table.point.y = e.getY();
//The method repaint internally calls the table's 
// method paintComponent() that refreshes the window  
	table.repaint(); 
 }
 public void mouseReleased(MouseEvent e) {};
 public void mouseEntered(MouseEvent e) {};
 public void mouseExited(MouseEvent e) {};
 public void mouseClicked(MouseEvent e) {};
	
// Methods required by the MouseMotionListener interface
 public void mouseDragged(MouseEvent e) {}

 public void mouseMoved(MouseEvent e) {
  int mouse_Y = e.getY();
  // If a mouse is above the kid's racket 
  // and the racket did not go over the table top
  // move it up, otherwise move it down    		
  if (mouse_Y < kidRacket_Y && kidRacket_Y > TABLE_TOP){
	kidRacket_Y -= RACKET_INCREMENT;
  }else if (kidRacket_Y < TABLE_BOTTOM) {
			kidRacket_Y += RACKET_INCREMENT;
  }
 // Set the new position of the racket table class 
    table.setKidRacket_Y(kidRacket_Y);
    table.repaint();		
  }
}
