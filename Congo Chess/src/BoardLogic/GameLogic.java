package BoardLogic;
//
// GameLogic.java 
// 
// This class contains all the necessary functions to play Congo, it communicates with the GUI 
// and database to determine possible moves and update GUI based on actions 
//

public class GameLogic extends State{
	State state;

	// Constructor used to start game logic upon creation of an object
	public GameLogic(State state) {
		//mainLogic(state);
	}
	
	//
	// Helper Methods 
	//
	
	// Checks if the indexes given fit within 
	public boolean isIndexBounded(int i, int j){
		if(i <= 6 && i >= 0) {
			if(j <= 6 && j >= 0) {
				return true;
			}
		}
		return false;
	}
	
	// Helper method to check if on proper even or odd index 
	public boolean isOnCorrectIndex(int x, int y, boolean row, boolean col) {
		
		// If row is even or odd
		if(row && x % 2 != 0) {
			return false;
		} else if (!row && x % 2 == 0) {
			return false;
		}
		
		// If col is even or odd
		if(col && y % 2 != 0) {
			return false;
		} else if (!col && y % 2 == 0) {
			return false;
		}
		
		return true;
	}
	
	//
	// Game Piece Logic 
	//
	
	// Displays all possible moves for the giraffe piece 
	public String[] allPossibleGiraffeMove(State state){
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
		String[][] board = state.getBoard();
		
		// Set to 15 for max amount of moves 
		String[] allPossibleMoves = new String[15];
		int count = 0; 
		
		// Check if even or odd to determine valid moves in outer matrix 
		boolean row = false;
		boolean col = false;
		if(i % 2 == 0 ) {
			row = true;
		}
		if (j % 2 == 0 ) {
			col = true;
		}
				
		
		// Goes through in a 5x5 2D matrix around piece to determine possible moves 
		for(int x = i - 2; x <= i + 2; x++) {
			for(int y = j - 2; y <= j + 2; y++) {
				// Checks bounds and if selected piece 
				if(isIndexBounded(x,y) && !((x == i) && (y == j))) {
					// Check edges (move and capture) 
					if( (x == i-2 || x == i+2) || (x > i-2 && (y == j-2 || y == j+2)) ) {
						// Checks if enemy 
						if(board[x][y].charAt(2) != state.getCurrentTurnColor().charAt(0)) {	
							// Checks for odd/even based on i and j
							if(isOnCorrectIndex(x,y,row,col)) {
								System.out.println("Adding(Outer) " + board[x][y] + " X: " + x + " Y: "+ y);
								allPossibleMoves[count] = board[x][y];
								count++;
							}
						}	
					}
					// Check inside (move only)
					else {
						if(board[x][y].charAt(2) == 'N') {
							System.out.println("Adding(Inner) " + board[x][y] + " X: " + x + " Y: "+ y);
							allPossibleMoves[count] = board[x][y];
							count++;
						}
					}
					
				}
			}
		}	

		return allPossibleMoves;
	} 

	// Displays all possible moves for the monkey piece 
	public String[] allPossibleMonkeyMove(State state){
		return null;
	}

	// Displays all possible moves for the elephant piece 
	public String[] allPossibleElephantMove(State state){
		int i = state.getPieceSelected().charAt(0);
		int j = state.getPieceSelected().charAt(1);
		
		String[][] board = state.getBoard();
		
		// Set to 8 for max amount of moves 
		String[] allPossibleMoves = new String[8];
		int count = 0; 
		
		// Forward 1 
		if(isIndexBounded(i-1,j)) {
			if(board[i-1][j].charAt(2) == 'N' || board[i-1][j].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-1][j];
				count++;
			}
		}
		
		// Forward 2 
		if(isIndexBounded(i-2,j)) {
			if(board[i-2][j].charAt(2) == 'N' || board[i-2][j].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-2][j];
				count++;
			}
		}
		
		// Left 1 
		if(isIndexBounded(i,j-1)) {
			if(board[i][j-1].charAt(2) == 'N' || board[i][j-1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i][j-1];
				count++;
			}
		}
		
		// Left 2
		if(isIndexBounded(i,j-2)) {
			if(board[i][j-2].charAt(2) == 'N' || board[i][j-2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i][j-2];
				count++;
			}
		}
		
		// Right 1 
		if(isIndexBounded(i,j+1)) {
			if(board[i][j+1].charAt(2) == 'N' || board[i][j+1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i][j+1];
				count++;
			}
		}
		
		// Right 2
		if(isIndexBounded(i,j+2)) {
			if(board[i][j+2].charAt(2) == 'N' || board[i][j+2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i][j+2];
				count++;
			}
		}
		
		// Back 1 
		if(isIndexBounded(i+1,j)) {
			if(board[i+1][j].charAt(2) == 'N' || board[i+1][j].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+1][j];
				count++;
			}
		}
		
		// Back 2 
		if(isIndexBounded(i+2,j)) {
			if(board[i+2][j].charAt(2) == 'N' || board[i+2][j].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+2][j];
				count++;
			}
		}
		
		
		
		return allPossibleMoves;
	}

	
	// Displays all possible moves for the lion piece 
	public String[] allPossibleLionMove(State state){
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
		String[][] board = state.getBoard();
		
		// Set to 15 for max amount of moves 
		String[] allPossibleMoves = new String[15];
		int count = 0; 
		
		// Check all around for standard move 
		for(int x = i -1; x < i + 1; x++) {
			for(int y = j-1; y < j+ 1; y++) {
				if(board[i][j].charAt(2) == 'N' ) {
					allPossibleMoves[count] = board[i][j];
					count++;
				}
			}
		}
		
		// Check for 'Flying General' conditions 
		if(i == 4) {
			
		}
		
		return null;
	}

	// 
	public String[] allPossibleCrocodileMove(State state){
		return null;
	}
//	public ArrayList<String> allPossibleCrocodileMove(State state){
//	ArrayList<String> legalMoves=new ArrayList<String>();
//	int row=Character.getNumericValue(state.currentClick.charAt(0));
//	int column=Character.getNumericValue(state.currentClick.charAt(1));
//	char colour=state.currentClick.charAt(2);
//    if(row==4) {
//    	for(int i=column; i<7; i++) {
//    		if(!illegalPosition(row,i)){
//    			String move= Integer.toString(row)+Integer.toString(i);
//    			legalMoves.add(move);
//    		}
//    	}
//    		for(int i=7-column; i>=0; i--) {
//        		if(!illegalPosition(row,i)){
//        			String move= Integer.toString(row)+Integer.toString(i);
//        			legalMoves.add(move);
//        		}
//    	}
//    }
//    
//	return null;
//}

	// Returns all the possible moves for the zebra piece, updates GUI 

	// Displays all possible moves for the zebra piece 
	public String[] allPossibleZebraMove(State state){	
		int i = state.getPieceSelected().charAt(0);
		int j = state.getPieceSelected().charAt(1);
		
		String[][] board = state.getBoard();
		
		// Set to 8 for max amount of moves 
		String[] allPossibleMoves = new String[8];
		int count = 0; 
		
		
		// Top left 1
		if (isIndexBounded(i-1,j-2)) {
			if(board[i-1][j-2].charAt(2) == 'N' || board[i-1][j-2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-1][j-2];
				count++;
			}
		}
		
		// Top Left 2
		if (isIndexBounded(i-2,j-1)) {
			if(board[i-2][j-1].charAt(2) == 'N' || board[i-2][j-1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-2][j-1];
				count++;
			}
			
		}
		
		// Top Right 1
		if (isIndexBounded(i-2,j+1)) {
			if(board[i-2][j+1].charAt(2) == 'N' || board[i-2][j+1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-2][j+1];
				count++;
			}
			
		}
		
		// Top Right 2
		if (isIndexBounded(i-1,j+2)) {
			if(board[i-1][j+2].charAt(2) == 'N' || board[i-1][j+2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i-1][j+2];
				count++;
			}
			
		}
		
		// Bottom Right 1
		if (isIndexBounded(i+1,j+2)) {
			if(board[i+1][j+2].charAt(2) == 'N' || board[i+1][j+2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+1][j+2];
				count++;
			}
			
		}
		
		// Bottom Right 2
		if (isIndexBounded(i+2,j+1)) {
			if(board[i+2][j+1].charAt(2) == 'N' || board[i+2][j+1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+2][j+1];
				count++;
			}
			
		}
		
		// Bottom left 1
		if (isIndexBounded(i+2,j-1)) {
			if(board[i+2][j-1].charAt(2) == 'N' || board[i+2][j-1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+2][j-1];
				count++;
			}
			
		}
		
		// Bottom Left 2
		if (isIndexBounded(i+1,j-2)) {
			if(board[i+1][j-2].charAt(2) == 'N' || board[i+1][j-2].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				allPossibleMoves[count] = board[i+1][j-2];
				count++;
			}
			
		}
		
		return allPossibleMoves;
	}
	
	
	//TODO: Logic that deals with river and capture/move mechanic 
	public String[] allPossiblePawnMove(State state){
		
		String[][] board = state.getBoard();
		
		//  Set to 10 as its above pawn max available moves 
		String[] allPossibleMoves = new String[10];
		int count = 0; 

		// Go through three tiles in front of pawn 
		int[] pieceLocation = {state.currentClick.charAt(0), state.currentClick.charAt(1)};
		
		
		for (int i = (pieceLocation[0] - 1) ; i < pieceLocation[0] + 3; i ++){
			for(int j = (pieceLocation[1] - 1); j < pieceLocation[1]; j++){
				
				// Check if an empty space or enemy piece
				if(board[i][j].charAt(2) == 'N' || board[i][j].charAt(2) != state.getCurrentTurnColor().charAt(0)){
					allPossibleMoves[count] = board[i][j];
					count++;
				}
			}
		}
		
		// Check for beyond river moves 
		if(state.getPieceSelected().charAt(0) == 'P' && state.getPieceSelected().charAt(0) <= 3) {
			
			int i = state.getPieceSelected().charAt(0);
			int j = state.getPieceSelected().charAt(1);
			
			// Check first backwards 
			if (isIndexBounded(i+1,j)) {
				if(state.board[i-1][j-1].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+1][j];
					count++;
				}
			}

			// Check second backwards 
			if (isIndexBounded(i+2,j)) {
				if(state.board[i-1][j-1].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+2][j];
					count++;
				}
			}
		}
		
		
		
		// TODO: These do not capture and do not deal with river 
		if(state.getPieceSelected().charAt(3) == 'S') {
			
			int i = state.getPieceSelected().charAt(0);
			int j = state.getPieceSelected().charAt(1);
			
			//Check bounds then check if null for move and enemy opponent for capture 
			
			// Check left side 
			if (isIndexBounded(i,j-1)) {
				if(board[i][j-1].charAt(3) == 'N' || board[i][j-1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
					allPossibleMoves[count] = board[i-1][j-1];
					count++;
				}
			}
			
			// Check right side 
			if (isIndexBounded(i,j+1) || board[i][j+1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
				if(board[i-1][j+1].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i][j+1];
					count++;
				}
			}
			
			// Check first left orthogonal  
			if (isIndexBounded(i+1,j-1)) {
				if(state.board[i-1][j-1].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i-1][j-1];
					count++;
				}
			}
			
			// Check second further left orthogonal  
			if (isIndexBounded(i+2,j-2)) {
				if(state.board[i+2][j-2].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+2][j-2];
					count++;
				}
			}
			
			
			// Check first backwards 
			if (isIndexBounded(i+1,j)) {
				if(state.board[i+1][j].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+1][j];
					count++;
				}
			}

			// Check second backwards 
			if (isIndexBounded(i+2,j)) {
				if(state.board[i+2][j].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+2][j];
					count++;
				}
			}
			
			
			// Check first right orthogonal  
			if (isIndexBounded(i+1,j+1)) {
				if(state.board[i+1][j+1].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+1][j+1];
					count++;
				}
			}
			
			// Check second further right orthogonal  
			if (isIndexBounded(i+2,j+2)) {
				if(state.board[i+2][j+2].charAt(3) == 'N') {
					allPossibleMoves[count] = board[i+2][j+2];
					count++;
				}
			}
			
			
		}


		
		return allPossibleMoves;
	}	

	// Update the GUI with all the possible moves 
	public void displayPossibleMoves(State state){

		String[] moveArray;
		switch(state.getPieceSelected().charAt(0)) {
			case 'G':
				moveArray = allPossibleGiraffeMove(state);
				break;
			case 'M':
				moveArray = allPossibleMonkeyMove(state);
				break;		
			case 'E':
				moveArray = allPossibleElephantMove(state);
				break;				  					
			case 'L':
				moveArray = allPossibleLionMove(state);
				break;	  
			case 'C':
				moveArray = allPossibleCrocodileMove(state);
					break;
			case 'Z':
				moveArray = allPossibleZebraMove(state);
				break;
			case 'P':
			case 'S':
				moveArray = allPossiblePawnMove(state);
			case 'N':
					break;  
			default:
				System.out.println("Error: Board value "+ state.getPieceSelected() + " not found");	  
		}
		// TODO: Send signal to GUI with possible moves to display 
		// updateGUI()

		// Update state in object form and database 
		// TODO: Do we need to update state here, should possible moves be inside state object?
		// state.updateState(state);
	}

	// Checks if new move is in list of all possible moves
	
	public boolean containsMove(String[] possibleMoves, String newPosition) {
		for (int i = 0; i < possibleMoves.length; i++) {
			if (possibleMoves[i] == newPosition){
				return true;
			}
		}
		return false;
	}	

	// Checks if move is possible based on piece
	public boolean isMovePossible(State state){
		String newPosition = state.getCurrentClick();
		
		String[] possibleMoves;
		switch(state.getPieceSelected().charAt(3)) {
			case 'G':
				possibleMoves = allPossibleGiraffeMove(state);
				return containsMove(possibleMoves, newPosition);
				
			case 'M':
				possibleMoves = allPossibleMonkeyMove(state);
				return containsMove(possibleMoves, newPosition);
					
			case 'E':
				possibleMoves = allPossibleElephantMove(state);
				return containsMove(possibleMoves, newPosition);
								  					
			case 'L':
				possibleMoves = allPossibleLionMove(state);
				return containsMove(possibleMoves, newPosition);
					  
			case 'C':
				possibleMoves = allPossibleCrocodileMove(state);
				return containsMove(possibleMoves, newPosition);
			 	
			case 'Z':
				possibleMoves = allPossibleZebraMove(state);
				return containsMove(possibleMoves, newPosition);
			  	
			case 'P':
				possibleMoves = allPossiblePawnMove(state);
				return containsMove(possibleMoves, newPosition);

			case 'N':
				// TODO: Error message or handle somehow
				  
			default:
				System.out.println("Error: Board value "+ state.getPieceSelected().charAt(3) + " not found");
				System.exit(1);
		  }

		  return false;
		
	}
	
	// Moves any piece given a new position 

	// Move piece to destination and update GUI 
	public void movePiece(State state, String newPosition){

		// Board that will take over state after move G
		String[][] newBoard = state.getBoard();

		// Erase old piece location
		String[] piecePosition = {Character.toString(state.getPieceSelected().charAt(0)), Character.toString(state.getPieceSelected().charAt(1))};
		int[] pieceCoordinates = {Integer.parseInt(piecePosition[0]), Integer.parseInt(piecePosition[1])};
		String deleteOld = Integer.toString(pieceCoordinates[0]) + Integer.toString(pieceCoordinates[1]) + "NN";
		newBoard[(int)pieceCoordinates[0]][(int)pieceCoordinates[1]] = deleteOld;

		// Move piece to new position 
		String replaceNew = Character.toString(state.getCurrentClick().charAt(0)) + Character.toString(state.getCurrentClick().charAt(1)) + Character.toString(state.getPieceSelected().charAt(2)) + Character.toString(state.getPieceSelected().charAt(3)); 
		int[] newPositionCoordinates = {Integer.parseInt(Character.toString(newPosition.charAt(0))), Integer.parseInt(Character.toString(newPosition.charAt(1))) };
		newBoard[newPositionCoordinates[0]][newPositionCoordinates[1]] = replaceNew;

		//TODO: Update GUI with new gameboard
		//updateGUI()

		//Update state
		state.setBoard(newBoard);
		//state.updateState(state);
	}
	
	
	// Takes in the current state 
	// TODO: ensure this works when no piece is selected yet (selecting upon click)
	public void mainLogic(State state) {
	
		// If a piece is selected 
		if (state.getPieceSelected() != null ) {
			
			// If selecting your own piece, display the possible moves to the GUI
			if (state.getCurrentClick().charAt(3) == state.currentTurnColor.charAt(0)){
				// This method would highlight the possible moves of the player on the game board 
				displayPossibleMoves(state);
		
				//Only one function call per turn 
				return; 
			}

			// If current clicked  space has no piece or is attempting to attack other plaayer, 
			String currentSpace = Character.toString(state.getCurrentClick().charAt(3));
			String selectedColor = Character.toString(state.getCurrentClick().charAt(2));
			if( (currentSpace == "N" ) || (selectedColor != state.currentTurnColor) ) {
			
				// If move is possible, move the piece to desired position (the click event)
				if (isMovePossible(state)) {
					movePiece(state, state.getCurrentClick());
					
					// Only one call function call per turn 
					return;
				}
			}	
		}	

	}

	// Main method for current testing (Remove in production) 
	public static void main (String[] args){
		
		// Example state values 
		String[][] board = {
								{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"}, 
								{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
								{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"}, 
								{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"}, 
								{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"}, 
								{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"}, 
								{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
							};
		
		String[][] board2 = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"}, 
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"}, 
				{"30NN", "31NN", "32WG", "33NN", "34NN", "35NN", "36NN"}, 
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"}, 
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"}, 
				{"60NN", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		
		// TODO: Change to receive from database
		State state = new State(board2,"W","11BP","32WG");    
		
		// Starts all the logic
		GameLogic game = new GameLogic(state);
		game.mainLogic(state);
		
		game.allPossibleGiraffeMove(state);

		System.out.println("Done");
		System.out.print(state.toString());
	}
}
