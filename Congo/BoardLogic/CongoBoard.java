package BoardLogic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import BoardLogic.BoardHelper;

public class CongoBoard extends JFrame implements MouseListener, MouseMotionListener {
	
	public static final Color lakeColor = new Color(79,175,255);
	public static final Color castleColor= new Color(140,140,140);
	public static final Color tileColor= new Color(170, 170, 170);
	public static final Color borderColor= new Color(50,50,50);
	 
	private boolean lock = false;
	private String user1 = "", user2 = "";
	String turn = "W"; //starting turn is white
	String players[] = new String[] {user1, user2};
	boolean isPieceClicked;
	ArrayList<String> indexList = new ArrayList<>();
	int[][] currentPossibleMoves;
	ArrayList<String> possibleMoves=new ArrayList<>();
	Container currentParent;
	 
	String pieceSelected;
	JLayeredPane layeredPane;
	JPanel congoBoard;
	JLabel congoPiece;
	int xAdjustment;
	int yAdjustment;
	Piece piece;
	// Initializing State
	State state=new State();
	Piece[][] board= state.getBoard();
	String fromPos;
	String toPos;
//	GameLogic testMove = new GameLogic();

	public CongoBoard(){
		Dimension boardSize = new Dimension(600, 600);
		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		layeredPane.setBackground(new Color(50,50,50));
		 
		 //Add a congo board to the Layered Pane 
		congoBoard = new JPanel();
		congoBoard.setBackground(new Color(50,50,50));
		//  JPanel bottomPanel = new JPanel();
		
		
		buildBoard();
		fillBoard(board);
		
		JPanel endTurn = new JPanel();
	    endTurn.setBackground(new Color(90,90,90));
	    endTurn.setLayout(new GridBagLayout());
	    JLabel label = new JLabel("End turn");
	    label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    endTurn.add(label);
	    endTurn.addMouseListener(new MouseAdapter() {
	  		@Override
	  		public void mousePressed(final MouseEvent e) {
	  			endTurn.setBackground(new Color(120,120,120));
	  		}
	  		@Override
	  		public void mouseReleased(final MouseEvent e) {
	  			endTurn.setBackground(new Color(90,90,90));
	  			turn=BoardHelper.switchTurn(congoBoard, turn);
	  		}
		});
	    
		layeredPane.add(congoBoard, JLayeredPane.DEFAULT_LAYER);
		congoBoard.setLayout( new GridLayout(8, 8) );
		congoBoard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		congoBoard.setPreferredSize( boardSize );
		congoBoard.setBounds(0, 0, boardSize.width, boardSize.height); 
	}
	
	public void fillBoard(Piece[][] board) {
		
		// Mapping pieces Names with Images
			    Hashtable<String, String> pieceImages = new Hashtable<String, String>(); 
			 
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

//				Placing Pieces on the GUI of Board
				for(int i=0; i<7;i++) {
					for(int j=0; j<7; j++) {
						Piece piece=board[i][j];
						if (piece!=null){
							String pieceName=""+piece.getColor()+piece.getType();
							String pieceImage=(String) pieceImages.get(pieceName);
							ImageIcon image = new ImageIcon(new ImageIcon(pieceImage).getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
							JLabel pieceGUI = new JLabel(image);
							pieceGUI.setName(pieceName);
							JPanel panel = (JPanel)congoBoard.getComponent(8*i+j+1);
//							panel.setName(pieceName);
							panel.add(pieceGUI);  
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
	public void buildBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j=0;j<8;j++) {
			
				if(i==7) {
					JPanel square = new JPanel( new BorderLayout());
//					JLabel l = new JLabel();
					// add label to panel
//					square.add(l);
					square.setBackground(new Color(50,50,50));
					congoBoard.add(square);
				} else if(j==0) {
					JPanel square = new JPanel( new BorderLayout());
//					JLabel l = new JLabel();  
					// add label to panel
//					square.add(l);
					square.setBackground(new Color(50,50,50));
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
	}
	  //Move the congo piece around
	 
	public void mouseDragged(MouseEvent me) {} 
	public void mouseReleased(MouseEvent e) {}  
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	  
	public void mousePressed(MouseEvent e){
		Component c =  congoBoard.findComponentAt(e.getX(), e.getY());

		// First click (piece select)
		if(!isPieceClicked) {
			congoPiece = null;
			if (c instanceof JPanel ||c.getName()==null) {
				System.out.println("select a piece");

				return;
			}
		    currentParent = c.getParent();
			Point parentLocation = c.getParent().getLocation();
			xAdjustment = parentLocation.x - e.getX();
			yAdjustment = parentLocation.y - e.getY();
	 
	 
			int row=BoardHelper.findRow(parentLocation.y);
			int col=BoardHelper.findColumn(parentLocation.x);
			
			fromPos=""+Integer.toString(row)+Integer.toString(col-1);
			piece=board[row][col-1];
			congoPiece = (JLabel)c;
			String pieceName=congoPiece.getName();
			char pieceColor=pieceName.charAt(0);
						
			if(pieceColor == 'W' && turn == "B") {
				return;
			}
			if(pieceColor == 'B' && turn == "W") {
				return;
			}
			
			Piece pieceSelectedBoard=board[row][col-1];
			pieceSelected = Integer.toString(row)+Integer.toString(col-1)+pieceName;
			congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			
			// get possible moves based on piece clicked.
			state.setPieceSelected(pieceSelectedBoard);
            System.out.println(state.toString());
			currentPossibleMoves = piece.legalMoves(state);
			for(int i=0; i<currentPossibleMoves.length;i++) {
				possibleMoves.add(""+Integer.toString(currentPossibleMoves[i][0])+Integer.toString(currentPossibleMoves[i][1]));
			}
			for (String possibleMove:possibleMoves) {
				congoBoard.getComponent(BoardHelper.convertIndex(possibleMove)).setBackground(Color.white);
			}
	 
			congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());
			layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);

			isPieceClicked=true;
			
		// Second click (place select)
		} else {
			System.out.println("In second click");		
			isPieceClicked=false;
			
//			if(congoPiece == null || c.getWidth() != 70) {
//				System.out.println("congoPiece is null or <70");
//				return;
//			}
	 
			congoPiece.setVisible(false);			
	
			if (c instanceof JLabel && !isIndex(congoPiece)){
//				System.out.println("FIRST IF");
				Container parent = c.getParent();
//				System.out.println("It is JLabel");
				Point parentLocation = parent.getLocation();
				if(isPieceMovedOnBoard(parentLocation)) {
//				parent.remove(0);
				parent.add(congoPiece);
//				System.out.println(state.toString());
				postMoveHandler();
				}
				else {
					currentParent.add(congoPiece);
				}
				possibleMoves.clear();
				
			}else if (!isIndex(congoPiece)){
				System.out.println("SECOND IF");
				Container parent = (Container)c;
				Point parentLocation = parent.getLocation();
				if(isPieceMovedOnBoard(parentLocation)) {
				parent.add(congoPiece);
				postMoveHandler();
				}
				else {
					currentParent.add(congoPiece);
				}
				possibleMoves.clear();
			}
			congoPiece.setVisible(true);
//			revertColors();
		} 
	}

    public boolean isLegalMove(ArrayList<String> possibelMoves,String futureStatePosition) {
    	if(possibleMoves.contains(futureStatePosition))
    	{
    		return true;
    	}
    	return false;
    }
	
	public boolean isIndex(JLabel c) {
		if(indexList.contains(c.getText())) {
			return true;
		}
		return false;
	}
	 
	// Checks whether a piece is already present in the square
	public boolean isPiecePresent(MouseEvent e) {
		Component c =  congoBoard.findComponentAt(e.getX(), e.getY());
		System.out.println("In isPiecePresent");
		if (c instanceof JLabel) {
			System.out.println("in if" + c);
			return true;
		}
		System.out.println("Past if");
	 
		return false;
	}
	
	
	 
//	private int findRow(int parentLocationY) {
//		return parentLocationY/70;
//	}
//	 
	
	
	 
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
	
	private void revertColors() {
		
		int index = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j=0;j<8;j++) {
				if(i==7) {
					congoBoard.getComponent(index).setBackground(new Color(50,50,50));
				} else if(j==0) {
					congoBoard.getComponent(index).setBackground(new Color(50,50,50));
				} else if(i==3) {
					congoBoard.getComponent(index).setBackground(lakeColor);
				} else {
					congoBoard.getComponent(index).setBackground( j==3 || j==4 || j==5 ? castleColor : tileColor );
				}
				index += 1;
	 
			}
		}
		
		
	}

 
	public boolean isPieceMovedOnBoard(Point parentLocation) {
		int row=BoardHelper.findRow(parentLocation.y);
		int col=BoardHelper.findColumn(parentLocation.x);
		toPos=Integer.toString(row)+Integer.toString(col-1);
		System.out.println("Future Move:"+toPos);

		System.out.println("PossibleMoves:"+possibleMoves);
		if(isLegalMove(possibleMoves,toPos)) {
		state.movePiece(fromPos,toPos);
		return true;
	}
		return false;
	}
	
	public void postMoveHandler() {
		turn=BoardHelper.switchTurn(congoBoard, turn);
		congoBoard.removeAll();
		congoBoard.repaint();
		buildBoard();
		state.flipBoard(state);
		board=state.getBoard();
		fillBoard(board);
	}
	
	
}
