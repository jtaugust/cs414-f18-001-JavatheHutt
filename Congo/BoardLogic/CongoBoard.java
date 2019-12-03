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
import Server.serverGamesHelpers;

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
	JPanel mainPanel;
	JLayeredPane layeredPane;
	JPanel congoBoard;
	JLabel congoPiece;
	int xAdjustment;
	int yAdjustment;
	Piece piece;
	State state;
	Piece[][] board;
	String fromPos;
	String toPos;
	int gameID;

	public CongoBoard(String user1, String user2, int gameID){
		
		//set users
		this.user1 = user1;
		this.user2 = user2;
		this.gameID = gameID;
		
		//read game state from database
		serverGamesHelpers database = new serverGamesHelpers();
		String[][] gameState = new String[7][7];
		try {
			gameState = database.readGameState(gameID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("User 1: " + user1 + " and User 2: " + user2 + " and game ID: " + gameID);
		
		//Initialize state and board
		state = new State();
		state.setCurrentTurnColor('W');
		
		Piece[][] currentBoard = new Piece[7][7];
				
		for(int i = 0; i < gameState.length; i++) {
			for(int j = 0; j < gameState[i].length; j++) {
				if(gameState[i][j] != null) {
					switch(gameState[i][j]) {
						case "WP": currentBoard[i][j] = new Pawn(i,j,'W','P'); 
							break; 
						case "WG": currentBoard[i][j] = new Giraffe(i,j,'W','G'); 
							break; 
						case "WM": currentBoard[i][j] = new Monkey(i,j,'W','M'); 
							break; 
						case "WE": currentBoard[i][j] = new Elephant(i,j,'W','E'); 
							break; 
						case "WL": currentBoard[i][j] = new Lion(i,j,'W','L'); 
							break; 
						case "WC": currentBoard[i][j] = new Crocodile(i,j,'W','C'); 
							break; 
						case "WZ": currentBoard[i][j] = new Zebra(i,j,'W','Z'); 
							break; 
						case "BP": currentBoard[i][j] = new Pawn(i,j,'B','P');     
							break;                                                 
						case "BG": currentBoard[i][j] = new Giraffe(i,j,'B','G');  
							break;                                                 
						case "BM": currentBoard[i][j] = new Monkey(i,j,'B','M');   
							break;                                                 
						case "BE": currentBoard[i][j] = new Elephant(i,j,'B','E'); 
							break;                                                 
						case "BL": currentBoard[i][j] = new Lion(i,j,'B','L');     
							break;                                                 
						case "BC": currentBoard[i][j] = new Crocodile(i,j,'B','C');
							break;                                                 
						case "BZ": currentBoard[i][j] = new Zebra(i,j,'B','Z');    
							break; 
						default: 
					}
				} else {
					System.out.print("NN");
				}
			}
			System.out.println("");
		}
		
		state.setBoard(currentBoard);
		board = state.getBoard();
		

		//build GUI from board -----------------------------------------------------------

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
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
		
		JPanel endTurnPanel = new JPanel();
		endTurnPanel.setLayout(new GridBagLayout());
		endTurnPanel.add(endTurn);
		endTurnPanel.setBackground(new Color(50,50,50));
		
		mainPanel.add(layeredPane, BorderLayout.CENTER);
		mainPanel.add(endTurnPanel, BorderLayout.PAGE_END);
	}
	
	//fill board GUI with pieces from board array
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

		//Placing Pieces on the GUI of Board
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

	//build default board GUI
	public void buildBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j=0;j<8;j++) {
			
				if(i==7) {
					JPanel square = new JPanel( new BorderLayout());
					square.setBackground(new Color(50,50,50));
					congoBoard.add(square);
				} else if(j==0) {
					JPanel square = new JPanel( new BorderLayout());
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
	 
	public void mouseDragged(MouseEvent me) {} 
	public void mouseReleased(MouseEvent e) {}  
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mousePressed(MouseEvent e){

		System.out.println("IN MOUSE EVENT -------------------");

		Component c =  congoBoard.findComponentAt(e.getX(), e.getY());

		if(!isPieceClicked) { // First click (piece select)

			System.out.println("IN FIRST CLICK --------------------------------------");
			System.out.println("Component name clicked: " + c.getName());

			congoPiece = null;

			//check if place clicked is not actually a piece, return if so.
			if (c instanceof JPanel || c.getName() == null) {
				return;
			}

			//calculate row and column of the click
		    currentParent = c.getParent();
			Point parentLocation = c.getParent().getLocation();
			xAdjustment = parentLocation.x - e.getX();
			yAdjustment = parentLocation.y - e.getY();
			int row=BoardHelper.findRow(parentLocation.y);
			int col=BoardHelper.findColumn(parentLocation.x);

			//get piece and from position
			fromPos=""+Integer.toString(row)+Integer.toString(col-1);
			piece=board[row][col-1];

			//get name and color of piece clicked
			congoPiece = (JLabel)c;
			String pieceName=congoPiece.getName();
			char pieceColor=pieceName.charAt(0);
			
			//check if piece color matches current turn, return if not 
			if(pieceColor == 'W' && turn == "B") {
				return;
			} else if(pieceColor == 'B' && turn == "W") {
				return;
			}
			
			//get piece selected
			Piece pieceSelectedBoard = board[row][col-1];
			pieceSelected = Integer.toString(row)+Integer.toString(col-1)+pieceName;

			//move piece slightly to show it was selected
			congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			state.setPieceSelected(pieceSelectedBoard);
			
			//Print state
			System.out.println("State: piece selected row and col: " + state.pieceSelected.getRow() +  state.pieceSelected.getColumn() + " - piece color: " + state.pieceSelected.getColor() + " - current turn color: " + state.getCurrentTurnColor());
			
			//get possible moves based on piece clicked.
			currentPossibleMoves = piece.legalMoves(state);

			for(int i = 0; i < currentPossibleMoves.length; i++) {
				possibleMoves.add(""+Integer.toString(currentPossibleMoves[i][0])+Integer.toString(currentPossibleMoves[i][1]));
				//check for default value
				if(!possibleMoves.get(i).equals("-1-1")){
					congoBoard.getComponent(BoardHelper.convertIndex(possibleMoves.get(i))).setBackground(Color.white);

				}
			}
			
			congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());

			//show piece selected in the top layer
			layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);

			//set that a piece was clicked
			isPieceClicked=true;
			
		} else { // Second click (place select)

			System.out.println("IN SECOND CLICK --------------------------------------");	
			
			//reset piece clicked
			isPieceClicked=false;
			revertColors();
			congoPiece.setVisible(false);	

			Container parent = null;

			if (c instanceof JLabel && !isIndex(congoPiece)){ //if tile clicked is another piece
				System.out.println("IN FIRST IF --------------------------------------");	
				parent = c.getParent();
			} else if (!isIndex(congoPiece)){ //if tile clicked is the same tile as piece clicked
				System.out.println("IN ELSE IF --------------------------------------");	
				parent = (Container)c;
			}

			Point parentLocation = parent.getLocation();

			if(isPieceMovedOnBoard(parentLocation)) {
				parent.add(congoPiece);
				postMoveHandler();
			} else {
				currentParent.add(congoPiece);
			}

			possibleMoves.clear();
			congoPiece.setVisible(true);
		} 
	}

    public boolean isLegalMove(ArrayList<String> possibelMoves,String futureStatePosition) {
    	if(possibleMoves.contains(futureStatePosition)) {
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
		if (c instanceof JLabel) {
			return true;
		}
	 
		return false;
	}
	
	public static JPanel createBoard(String user1, String user2, int gameID){
		//generate a fresh board
		CongoBoard board = new CongoBoard(user1, user2, gameID);
		return board.mainPanel;
	}
	 
//	private static void setUsers(String user1, String user2, CongoBoard board) {
//		board.user1 = user1;
//		board.user2 = user2;
//	}
	
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
		turn = BoardHelper.switchTurn(congoBoard, turn);
		if(turn == "W") {
			state.setCurrentTurnColor('W');
		} else {
			state.setCurrentTurnColor('B');
		}
		congoBoard.removeAll();
		congoBoard.repaint();
		buildBoard();
		state.flipBoard(state);
		board = state.getBoard();
		fillBoard(board);
	}
	
	
}
