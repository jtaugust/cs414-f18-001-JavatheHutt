package BoardLogic;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class CongoBoard extends JFrame implements MouseListener, MouseMotionListener {
	
	public static final Color lakeColor = new Color(79,175,255);
	public static final Color castleColor= new Color(140,140,140);
	public static final Color tileColor= new Color(170, 170, 170);
	public static final Color borderColor= new Color(50,50,50);
	 
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
//		getContentPane().setPreferredSize(boardSize);
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		layeredPane.setBackground(new Color(90,90,90));
		 
		 //Add a congo board to the Layered Pane 
		congoBoard = new JPanel();
		congoBoard.setBackground(new Color(90,90,90));
		//  JPanel bottomPanel = new JPanel();
		layeredPane.add(congoBoard, JLayeredPane.DEFAULT_LAYER);
		congoBoard.setLayout( new GridLayout(8, 8) );
//		congoBoard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		congoBoard.setPreferredSize( boardSize );
		congoBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		 
		for (int i = 0; i < 8; i++) {
			for (int j=0;j<8;j++) {
				 
				if(i==7) {
					JPanel square = new JPanel( new BorderLayout());
					JLabel l = new JLabel();
					// add label to panel
					square.add(l);
					square.setBackground(new Color(90,90,90));
					congoBoard.add(square);
				} else if(j==0) {
					JPanel square = new JPanel( new BorderLayout());
					JLabel l = new JLabel();  
					// add label to panel
					square.add(l);
					square.setBackground(new Color(90,90,90));
					congoBoard.add(square);
				} else if(i==3) {
					JPanel square = new JPanel(new BorderLayout()) {
						private static final long serialVersionUID = 1L;

						@Override
					    public void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        Graphics2D g2d = (Graphics2D) g;
					        GradientPaint gp = new GradientPaint(0, 0, getBackground(), 0, getHeight(), getBackground().darker());
					        g2d.setPaint(gp);
					        g2d.fillRect(0, 0, getWidth(), getHeight());
					    }
					};
					square.setBackground(lakeColor);
					if(j == 7)
						square.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, borderColor));
					if(j == 1)
						square.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, borderColor));
					congoBoard.add(square);
				} else {
					JPanel square = new JPanel(new BorderLayout()) {
						private static final long serialVersionUID = 1L;

						@Override
					    public void paintComponent(Graphics g) {
					        super.paintComponent(g);
					        Graphics2D g2d = (Graphics2D) g;
					        GradientPaint gp = new GradientPaint(0, 0, getBackground(), 0, getHeight(), getBackground().darker());
					        g2d.setPaint(gp);
					        g2d.fillRect(0, 0, getWidth(), getHeight());
					    }
					};
					square.setBackground( j==3 || j==4 || j==5 ? castleColor : tileColor );
					square.setBorder(BorderFactory.createMatteBorder(1,1,1,1, borderColor));
					congoBoard.add(square);
				}
	 
			}
		}
		
// Mapping pieces Names with Images
	    Hashtable pieceImages = new Hashtable(); 
	 
		String[] white_pieces= {"WG","WM","WE","WL","WE","WC","WZ"};
		String[] white_piece_images= {"./Images/whiteGiraffe.png","./Images/whiteMonkey.png","./Images/whiteElephant.png"
				,"./Images/whiteLion.png","./Images/whiteElephant.png","./Images/whiteCrocodile.png","./Images/whiteZebra.png"};
		String[] black_piece_images= {"./Images/blackGiraffe.png","./Images/blackMonkey.png","./Images/blackElephant.png"
				,"./Images/blackLion.png","./Images/blackElephant.png","./Images/blackCrocodile.png","./Images/blackZebra.png"};
		String[] black_pieces= {"BG","BM","BE","BL","BE","BC","BZ"};
		String[] letters= {"a","b","c","d","e","f","g"};
		 
		for(int i=0; i<white_pieces.length;i++) {
			pieceImages.put(white_pieces[i], white_piece_images[i]);
			pieceImages.put(black_pieces[i], black_piece_images[i]);
		}
		pieceImages.put("WP", "./Images/whitePawn.png");
		pieceImages.put("BP", "./Images/blackPawn.png");

//		Placing Pieces on the GUI of Board
		for(int i=0; i<7;i++) {
			for(int j=0; j<7; j++) {
				String str=board[i][j];
				String pieceName=str.substring(str.length()-2);
				if (!pieceName.contentEquals("NN")){
					String pieceImage=(String) pieceImages.get(pieceName);
					ImageIcon image = new ImageIcon(new ImageIcon(pieceImage).getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
					JLabel piece = new JLabel(image);
					piece.setName(pieceName);
					JPanel panel = (JPanel)congoBoard.getComponent(8*i+j+1);
					panel.setName(pieceName);
					panel.add(piece);  
				}
				
			}
		}

		//Row indices
		for(int i=1;i<8;i++) {
			JLabel piece = new JLabel(Integer.toString(i));
			indexList.add(Integer.toString(i));
			piece.setFont(new Font("Serif", Font.BOLD, 30));
			piece.setForeground(Color.white);
			JPanel panel = (JPanel)congoBoard.getComponent((i-1)*8);
			panel.setLayout(new GridLayout());
			panel.add(piece);  
			piece.setEnabled(false);
		}
		
		//Column indices
		for(int i=57;i<64;i++) {
			JLabel piece = new JLabel(letters[i-57]);
			indexList.add(letters[i-57]);
			piece.setFont(new Font("Serif", Font.BOLD, 30));
			piece.setForeground(Color.white);
			JPanel panel = (JPanel)congoBoard.getComponent(i);
			panel.setLayout(new GridLayout());
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
			congoPiece = (JLabel)c;
			System.out.println(congoPiece.getName());

			String currentClick;
			String pieceName=congoPiece.getName();
			char pieceColor=pieceName.charAt(0);
			String pieceSelected=pieceName+Integer.toString(row)+Integer.toString(col);
			congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			//  State state = new State(board,"W","40NN","50WP");
	 
			congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());
			//  layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);
			layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);

			isPieceClicked=true;
		} else if(!isPiecePresent(e)) {			
		
			isPieceClicked=false;
			
			if(congoPiece == null) 
				return;
	 
			congoPiece.setVisible(false);
			Component c =  congoBoard.findComponentAt(e.getX(), e.getY());			
	
			if (c instanceof JLabel && !isIndex(congoPiece)){
				Container parent = c.getParent();
				
				parent.remove(0);
				parent.add( congoPiece );
			} else if (!isIndex(congoPiece)){
				Container parent = (Container)c;
				parent.add( congoPiece );
			}  
			congoPiece.setVisible(true);
	//  	if (congoPiece == null) return;
	//  	congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			
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
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}
	
	 
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