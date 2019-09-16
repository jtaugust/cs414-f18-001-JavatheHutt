package congo;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;

public static final Color lightBlue = new Color(51,204,255);
 
public class CongoBoard extends JFrame implements MouseListener, MouseMotionListener {
  JLayeredPane layeredPane;
  JPanel congoBoard;
  JLabel congoPiece;
  int xAdjustment;
  int yAdjustment;
 
  public CongoBoard(){
  Dimension boardSize = new Dimension(600, 600);
 
  //  Use a Layered Pane for this this application
 layeredPane = new JLayeredPane();
  getContentPane().add(layeredPane);
  layeredPane.setPreferredSize(boardSize);
  layeredPane.addMouseListener(this);
  layeredPane.addMouseMotionListener(this);
 
  //Add a congo board to the Layered Pane 
 
  congoBoard = new JPanel();
  layeredPane.add(congoBoard, JLayeredPane.DEFAULT_LAYER);
  congoBoard.setLayout( new GridLayout(7, 7) );
  congoBoard.setPreferredSize( boardSize );
  congoBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
  for (int i = 0; i < 7; i++) {
	  for (int j=0;j<7;j++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  congoBoard.add( square );
		  if(i==3) {
			  square.setBackground(Color.blue);
		  }
		  else {
			  square.setBackground( j==2 || j==3 || j==4 ? Color.black : Color.white );
		  }
			  
	  }
  }
 
  //Add a few pieces to the board
 
  JLabel piece = new JLabel( new ImageIcon("D:/semester3/projects/king.png") );
  JPanel panel = (JPanel)congoBoard.getComponent(0);
  panel.add(piece);
  piece = new JLabel(new ImageIcon("D:/semester3/projects/king.png"));
  panel = (JPanel)congoBoard.getComponent(15);
  panel.add(piece);
  piece = new JLabel(new ImageIcon("D:/semester3/projects/king.png"));
  panel = (JPanel)congoBoard.getComponent(16);
  panel.add(piece);
  piece = new JLabel(new ImageIcon("D:/semester3/projects/king.png"));
  panel = (JPanel)congoBoard.getComponent(20);
  panel.add(piece);
 
  }
 
  public void mousePressed(MouseEvent e){
  congoPiece = null;
  Component c =  congoBoard.findComponentAt(e.getX(), e.getY());
 
  if (c instanceof JPanel) 
  return;
 
  Point parentLocation = c.getParent().getLocation();
  xAdjustment = parentLocation.x - e.getX();
  yAdjustment = parentLocation.y - e.getY();
  congoPiece = (JLabel)c;
  congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
  congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());
  layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);
  }
 
  //Move the congo piece around
  
  public void mouseDragged(MouseEvent me) {
  if (congoPiece == null) return;
 congoPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
 }
 
  //Drop the congo piece back onto the congo board
 
  public void mouseReleased(MouseEvent e) {
  if(congoPiece == null) return;
 
  congoPiece.setVisible(false);
  Component c =  congoBoard.findComponentAt(e.getX(), e.getY());
 
  if (c instanceof JLabel){
  Container parent = c.getParent();
  parent.remove(0);
  parent.add( congoPiece );
  }
  else {
  Container parent = (Container)c;
  parent.add( congoPiece );
  }
 
  congoPiece.setVisible(true);
  }
 
  public void mouseClicked(MouseEvent e) {
  
  }
  public void mouseMoved(MouseEvent e) {
 }
  public void mouseEntered(MouseEvent e){
  
  }
  public void mouseExited(MouseEvent e) {
  
  }
 
  public static void main(String[] args) {
  JFrame frame = new CongoBoard();
  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
  frame.pack();
  frame.setResizable(true);
  frame.setLocationRelativeTo( null );
  frame.setVisible(true);
 }
}
