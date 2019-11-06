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
	String turn = "W"; //starting turn is white
	String players[] = new String[] {user1, user2};
	boolean isPieceClicked;
	ArrayList<String> indexList = new ArrayList<>();
	String currentPossibleMoves[];
	ArrayList<String> possibleMoves=new ArrayList<>();
	Container currentParent;
	
	String[][] board = {
		{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
		{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
		{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
		{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
		{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
		{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
		{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
	};
	 
	String pieceSelected;
	JLayeredPane layeredPane;
	JPanel congoBoard;
	JLabel congoPiece;
	int xAdjustment;
	int yAdjustment;
	
	// Initializing State
	State state=new State(board, "W", "", "");
	GameLogic testMove = new GameLogic();


	 
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
	  			switchTurn(congoBoard);
	  		}
		});
	    
		layeredPane.add(congoBoard, JLayeredPane.DEFAULT_LAYER);
		congoBoard.setLayout( new GridLayout(8, 8) );
		congoBoard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		congoBoard.setPreferredSize( boardSize );
		congoBoard.setBounds(0, 0, boardSize.width, boardSize.height); 
	}
	
	public void fillBoard(String[][] board) {
		
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
						String str=board[i][j];
						String pieceName=str.substring(str.length()-2);
						if (!pieceName.contentEquals("NN")){
							String pieceImage=(String) pieceImages.get(pieceName);
							ImageIcon image = new ImageIcon(new ImageIcon(pieceImage).getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
							JLabel piece = new JLabel(image);
							piece.setName(pieceName);
							JPanel panel = (JPanel)congoBoard.getComponent(8*i+j+1);
//							panel.setName(pieceName);
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
	 
	public void mouseDragged(MouseEvent me) {
	
	  
	}
	 
	  //Drop the congo piece back onto the congo board
	 
	  
	public void mouseReleased(MouseEvent e) {
	
	  
	}
	 
	  
	public void mouseClicked(MouseEvent e) {
	
	  
	}
	 
	  
	public void mousePressed(MouseEvent e){
		Component c =  congoBoard.findComponentAt(e.getX(), e.getY());
		// First click (piece select)
		if(!isPieceClicked) {
	
			congoPiece = null;
			System.out.println("pieceName clicked"+c.getName());
			if (c instanceof JPanel ||c.getName()==null) {
				System.out.println("It is JPanel");
				return;
			}
		    currentParent = c.getParent();
			Point parentLocation = c.getParent().getLocation();
			xAdjustment = parentLocation.x - e.getX();
			yAdjustment = parentLocation.y - e.getY();
	 
	 
			int row=findRow(parentLocation.y);
			int col=findColumn(parentLocation.x);
			congoPiece = (JLabel)c;
			String pieceName=congoPiece.getName();
			char pieceColor=pieceName.charAt(0);
			
			System.out.println("Piece color: " + pieceColor);
			
			if(pieceColor == 'W' && turn == "B") {
				return;
			}
			if(pieceColor == 'B' && turn == "W") {
				return;
			}
			
			pieceSelected = Integer.toString(row)+Integer.toString(col-1)+pieceName;
			System.out.println("Piece Selected:"+pieceSelected);
			congoPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			
			// get possible moves based on piece clicked.

			System.out.println("Selected: " + pieceSelected);
			board=state.getBoard();
			state.setPieceSelected(pieceSelected);
			state.setCurrentTurnColor(testMove.getCurrentTurnColor());
            System.out.println(state.toString());
			currentPossibleMoves = testMove.displayPossibleMoves(state);
			for(int i = 0; i < currentPossibleMoves.length; i++) {
				if(currentPossibleMoves[i] != null) {
					System.out.println(currentPossibleMoves[i].substring(0, 2));
					possibleMoves.add(currentPossibleMoves[i].substring(0, 2));
					congoBoard.getComponent(convertIndex(currentPossibleMoves[i].substring(0, 2))).setBackground(Color.white);
				}
			}
	 
			congoPiece.setSize(congoPiece.getWidth(), congoPiece.getHeight());
			//  layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);
			layeredPane.add(congoPiece, JLayeredPane.DRAG_LAYER);

			isPieceClicked=true;
			
		// Second click (place select)
		} else {
			System.out.println("In second click");
			
//			if (c.getWidth() != 70) {
//				System.out.println("Width is less");
//				return;
//			}
			
			
			
			isPieceClicked=false;
			
			if(congoPiece == null) {
				System.out.println("null");
				return;
			}

			congoPiece.setVisible(false);
			
			if (c instanceof JLabel && !isIndex(congoPiece)){
				System.out.println("FIRST IF");
				Container parent = c.getParent();
				//My Code
				System.out.println("It is JLabel");
				Point parentLocation = parent.getLocation();
				int row=findRow(parentLocation.y);
				int col=findColumn(parentLocation.x);
				JLabel futurePiece=(JLabel)c;
				String futurePosition=Integer.toString(row)+Integer.toString(col-1);
				String futurePieceName;
				if(futurePiece.getName()==null) {
					 futurePieceName="NN";
				}
				else {
					 futurePieceName=futurePiece.getName();
				}
				String futureStatePosition=futurePosition+futurePieceName;

				System.out.println("Future Move:"+futurePosition);
				
				if(possibleMoves.contains(futurePosition)) {
				//My Code
				parent.remove(0);
				parent.add(congoPiece);
				testMove.movePiece(state,futureStatePosition);
				System.out.println(state.toString());
				switchTurn(congoBoard);
				congoBoard.removeAll();
				congoBoard.repaint();
				buildBoard();
				testMove.flipBoard(state);
				fillBoard(state.getBoard());
				}
				else {
					currentParent.add(congoPiece);
				}
				possibleMoves.clear();
//				parent.remove(0);
//				parent.add(congoPiece);
			} else if (!isIndex(congoPiece)){
				System.out.println("SECOND IF");
				Container parent = (Container)c;
				//My Code
				Point parentLocation = parent.getLocation();
				int row=findRow(parentLocation.y);
				int col=findColumn(parentLocation.x);
				String futurePosition=Integer.toString(row)+Integer.toString(col-1);
				String futureStatePosition=futurePosition+"NN";
				System.out.println("Future Move:"+futurePosition);
				System.out.println(possibleMoves);
				if(possibleMoves.contains(futurePosition)) {
				//My Code
				parent.add(congoPiece);
				testMove.movePiece(state,futureStatePosition);
				System.out.println(state.toString());
				switchTurn(congoBoard);
				congoBoard.removeAll();
				congoBoard.repaint();
				buildBoard();
				testMove.flipBoard(state);
				fillBoard(state.getBoard());

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
	
	public void switchTurn(JPanel congoBoard) {
		
		System.out.println("Turn before change: " + turn);

		if(turn == "W") {
			turn = "B";
			testMove.setCurrentTurnColor("B");
			System.out.println(testMove.getCurrentTurnColor());

		}
		else if(turn == "B") {
			turn = "W";
			testMove.setCurrentTurnColor("W");
			System.out.println(testMove.getCurrentTurnColor());
		}
//		congoBoard.removeAll();
//		congoBoard.revalidate();
//		congoBoard.repaint();
//		buildBoard();
//		fillBoard();
		System.out.println("Turn after change: " + turn);
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
	
	private static int convertIndex(String position) {
		int index = 0;
			
		switch(position) {
	
			case "00": index = 1;
				break;
			case "01": index = 2;
				break;
			case "02": index = 3;
				break;
			case "03": index = 4;
				break;
			case "04": index = 5;
				break;
			case "05": index = 6;
				break;
			case "06": index = 7;
				break;
			case "10": index = 9;
				break;
			case "11": index = 10;
				break;
			case "12": index = 11;
				break;
			case "13": index = 12;
				break;
			case "14": index = 13;
				break;
			case "15": index = 14;
				break;
			case "16": index = 15;
				break;
			case "20": index = 17;
				break;
			case "21": index = 18;
				break;
			case "22": index = 19;
				break;
			case "23": index = 20;
				break;
			case "24": index = 21;
				break;
			case "25": index = 22;
				break;
			case "26": index = 23;
				break;
			case "30": index = 25;
				break;
			case "31": index = 26;
				break;
			case "32": index = 27;
				break;
			case "33": index = 28;
				break;
			case "34": index = 29;
				break;
			case "35": index = 30;
				break;
			case "36": index = 31;
				break;
			case "40": index = 33;
				break;
			case "41": index = 34;
				break;
			case "42": index = 35;
				break;
			case "43": index = 36;
				break;
			case "44": index = 37;
				break;
			case "45": index = 38;
				break;
			case "46": index = 39;
				break;
			case "50": index = 41;
				break;
			case "51": index = 42;
				break;
			case "52": index = 43;
				break;
			case "53": index = 44;
				break;
			case "54": index = 45;
				break;
			case "55": index = 46;
				break;
			case "56": index = 47;
				break;
			case "60": index = 49;
				break;
			case "61": index = 50;
				break;
			case "62": index = 51;
				break;
			case "63": index = 52;
				break;
			case "64": index = 53;
				break;
			case "65": index = 54;
				break;
			case "66": index = 55;
				break;
			default:
		}
		
		return index;
	}
 
}