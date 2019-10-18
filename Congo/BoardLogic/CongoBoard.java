package BoardLogic;
import java.awt.*;
import java.awt.Color;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class CongoBoard extends JFrame implements MouseListener, MouseMotionListener {
	  public static final Color lakeColor = new Color(51,153,255);
	  public static final Color castleColor= new Color(153,153,153);
	  public static final Color tileColor= new Color(204,204,204);
	  public static final Color borderColor= new Color(255,255,0);
	 
	  private boolean lock = false;
	  private String user1 = "", user2 = "";
	  String turn = "White"; //starting turn is white
	  String players[] = new String[] {user1, user2};
	  boolean isPieceClicked;
	  ArrayList<String> indexList = new ArrayList<>();


	  String[][] board = {
	{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
	{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
	{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
	{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
	{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
	{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
	{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
	};
	 

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
	//  JPanel bottomPanel = new JPanel();
	  layeredPane.add(congoBoard, JLayeredPane.DEFAULT_LAYER);
	  congoBoard.setLayout( new GridLayout(8, 8) );
	  congoBoard.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

	  congoBoard.setPreferredSize( boardSize );
	  congoBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	 
	  for (int i = 0; i < 8; i++) {
	 for (int j=0;j<8;j++) {
	 JPanel square = new JPanel( new BorderLayout() );
	 congoBoard.add( square );
	 if(i==7) {
	 JLabel l = new JLabel();
	     // add label to panel
	 square.add(l);
	 congoBoard.add( square );

	 }
	 else if(j==0) {
	 JLabel l = new JLabel();  
	     // add label to panel
	 square.add(l);
	 }
	 else if(i==3) {
	 square.setBackground(lakeColor);
	 }
	 else {
	 square.setBackground( j==3 || j==4 || j==5 ? castleColor : tileColor );
	 square.setBorder(BorderFactory.createLineBorder(borderColor));
	 }
	 
	 }
	  }
	 
	 
	  String[] white_pieces= {"WG","WM","WE","WL","WE","WC","WZ"};
	  String[] black_pieces= {"BG","BM","BE","BL","BE","BC","BZ"};
	  String[] letters= {"a","b","c","d","e","f","g"};
	 
	  for(int i=1; i<8;i++) {
	 JLabel piece = new JLabel(black_pieces[i-1]);
	 piece.setFont(new Font("Serif", Font.BOLD, 30));
	 JPanel panel = (JPanel)congoBoard.getComponent(i);
	 panel.setName(black_pieces[i-1]);
	 panel.add(piece);  
	  }
	 
	  for(int i=49;i<56;i++) {
	 JLabel piece = new JLabel(white_pieces[i-49]);
	 piece.setFont(new Font("Serif", Font.BOLD, 30));
	 JPanel panel = (JPanel)congoBoard.getComponent(i);
	 panel.setName(white_pieces[i-49]);
	 panel.add(piece);  
	  }
	 
	  for(int i=9;i<16;i++) {
	 JLabel piece = new JLabel("BP");
	 piece.setFont(new Font("Serif", Font.BOLD, 30));
	 JPanel panel = (JPanel)congoBoard.getComponent(i);
	 panel.setName("BP");
	 panel.add(piece);  
	  }
	  for(int i=41;i<48;i++) {
	 JLabel piece = new JLabel("WP");
	 piece.setFont(new Font("Serif", Font.BOLD, 30));
	 JPanel panel = (JPanel)congoBoard.getComponent(i);
	 panel.setName("WP");
	 panel.add(piece);  
	  }
	 
	  for(int i=1;i<8;i++) {
	 JLabel piece = new JLabel(Integer.toString(i));
	 indexList.add(Integer.toString(i));
	 piece.setFont(new Font("Serif", Font.BOLD, 60));
	 JPanel panel = (JPanel)congoBoard.getComponent((i-1)*8);
	 panel.add(piece);  
	 piece.setEnabled(false);
	  }
	  for(int i=57;i<64;i++) {
	 JLabel piece = new JLabel(letters[i-57]);
	 indexList.add(letters[i-57]);
	 piece.setFont(new Font("Serif", Font.BOLD, 60));
	 JPanel panel = (JPanel)congoBoard.getComponent(i);
	 panel.add(piece);  
	 piece.setEnabled(false);

	  }
	 
	 
	  }
	 
	  //Move the congo piece around
	 
	  public void mouseDragged(MouseEvent me) {

	 }
	 
	  //Drop the congo piece back onto the congo board
	 
	  public void mouseReleased(MouseEvent e) {

	  }
	 
	  public void mouseClicked(MouseEvent e) {

	  }
	 
	  public void mousePressed(MouseEvent e){
	 if(!isPieceClicked ) {
	 congoPiece = null;
	 Component c =  congoBoard.findComponentAt(e.getX(), e.getY());

	 if (c instanceof JPanel)
	 return;


	 Point parentLocation = c.getParent().getLocation();
	 xAdjustment = parentLocation.x - e.getX();
	 yAdjustment = parentLocation.y - e.getY();
	 
	 
	 int row=findRow(parentLocation.y);
	 int col=findColumn(parentLocation.x);
	 System.out.println(row+","+col);
	//  System.out.println(congoPiece.getName());
	 congoPiece = (JLabel)c;
	 System.out.println(congoPiece.getText());
	 String pieceName=congoPiece.getText();
	 char pieceColor=pieceName.charAt(0);
	 String pieceSelected=pieceName+Integer.toString(row)+Integer.toString(col);
	 congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	//  State state = new State(board,"W","40NN","50WP");
	 
	 congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());
	//  layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);
	 layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);
	 isPieceClicked=true;
	   }
	 else if(!isPiecePresent(e)){
	 isPieceClicked=false;
	 if(congoPiece == null) return;
	 
	 congoPiece.setVisible(false);
	 Component c =  congoBoard.findComponentAt(e.getX(), e.getY());

	 if (c instanceof JLabel && !isIndex(congoPiece)){
	 Container parent = c.getParent();
	 parent.remove(0);
	 parent.add( congoPiece );
	 }
	 else if (!isIndex(congoPiece)){
	 Container parent = (Container)c;
	 parent.add( congoPiece );
	 }  

	 congoPiece.setVisible(true);
	//  if (congoPiece == null) return;
	//  congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	 }
	 
	  }
	  public boolean isIndex(JLabel c) {
	 if(indexList.contains(c.getText()))
	 return true;
	 return false;
	 
	  }
	 
	  // Checks whether a piece is already present in the square
	  public boolean isPiecePresent(MouseEvent e) {
	 Component c =  congoBoard.findComponentAt(e.getX(), e.getY());

	 if (c instanceof JLabel)
	 return true;
	 
	return false;
	  }
	  public void mouseMoved(MouseEvent e) {
	 }
	  public void mouseEntered(MouseEvent e){
	 
	  }
	  public void mouseExited(MouseEvent e) {
	 
	  }
	 
	  private int findRow(int parentLocationY) {
	 return parentLocationY/70;
	  }
	 

	  private int findColumn(int parentLocationX) {
	 return parentLocationX/70;
	  }
	 
	  public static JLayeredPane createBoard(String user1, String user2){
	//generate a fresh board
	CongoBoard board = new CongoBoard();
	setUsers(user1, user2, board);
	return board.layeredPane;
	}
	 
	private static void setUsers(String user1, String user2, CongoBoard board) {
	  board.user1 = user1;
	  board.user2 = user2;


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