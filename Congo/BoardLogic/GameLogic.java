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
	public GameLogic() {
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
	
	// Flips board to ensure same logic for both sides 
	// Flips board to ensure same logic for both sides 

	public String[][] flipBoard(State state){
		String[][] board = state.getBoard();
		String[][] newBoard = new String[7][7];
		String[][] indexBoard = board;
		// Go through backwards and swap pieces by taking index of array copy and piece color and type from real board
		int countI = 0;
		int countJ = 0;
		for (int i = 6; i >= 0; i--) { 
            for (int j = 6; j >= 0; j--) {
            	newBoard[countI][countJ] =  Character.toString(indexBoard[countI][countJ].charAt(0)) + 
					            			Character.toString(indexBoard[countI][countJ].charAt(1)) + 
					            			Character.toString(board[i][j].charAt(2)) + 
					            			Character.toString(board[i][j].charAt(3));
                countJ++;
            }
            countI++;
    		countJ = 0;
        } 
		state.setBoard(newBoard);
		return newBoard;
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
								allPossibleMoves[count] = board[x][y];
								count++;
							}
						}	
					}
					// Check inside (move only)
					else {
						if(board[x][y].charAt(2) == 'N') {
							allPossibleMoves[count] = board[x][y];
							count++;
						}
					}	
				}
			}
		}	

		return allPossibleMoves;
	} 

//	 Monkey Helper Method 

	public boolean monkeyCanJump(State state, int toX, int toY) {
		String[][] board = state.getBoard();
		// Check if out of bounds 
		if(toX < 0 || toX > 6 || toY < 0 || toY > 6) {
			return false;
		}
		// Check if empty space
		if(board[toX][toY].charAt(3) != 'N') {
			return false;
		}
		// If own piece return false
		int fromX = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int fromY = Character.getNumericValue(state.getPieceSelected().charAt(1));
		int cX = 0;
		int cY = 0;
		//System.out.println("X: "+fromX+" Y: "+fromY);
		// Adjust cX and cY to be equal to piece inbetween 
		if(fromX < toX) {
			cX = toX - 1;
		}else if (fromX == toX){
			cX = toX;
		}
		else {
			cX = toX + 1;
		}
		if(fromY < toY) {
			cY = toY - 1;
		}else if (fromY == toY){
			cY = toY;
		}
		else {
			cY = toY + 1;
		}
		// Check if piece in between is own color 
		if(board[cX][cY].charAt(2) == state.getCurrentTurnColor().charAt(0)) {
			 return false;
		}
		//System.out.println("True");
		return true;
	}
	
	// TODO: Need a visited array of jumps added
	// Displays all possible moves for the monkey piece 
	public String[] allPossibleMonkeyMove(State state){
		
		// Define current position of monkey piece 
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
		String[][] board = state.getBoard();
		
		// TODO: set to proper length 
		String[] allPossibleMoves = new String[20];
		int count = 0; 
		
		// Check 3x3 around piece
		for(int x = i - 1; x <= i + 1; x++) {
			for(int y = j - 1; y <= j + 1; y++) {
				if(isIndexBounded(x,y)){
					if(board[x][y].charAt(2) == 'N' ) {
						allPossibleMoves[count] = board[x][y];
						count++;
					}
				}
			}
		}
		
		// Check top left jump
		if(isIndexBounded(i-2,j-2)){
			if(monkeyCanJump(state,i-2,j-2)) {
				allPossibleMoves[count] = board[i-2][j-2];
				count++;
			}
		}
		
		// Check top middle jump
		if(isIndexBounded(i-2,j)){
			if(monkeyCanJump(state,i-2,j)) {
				allPossibleMoves[count] = board[i-2][j];
				count++;
			}
		}
		
		// Check top right jump
		if(isIndexBounded(i-2,j+2)){
			if(monkeyCanJump(state,i-2,j+2)) {
				allPossibleMoves[count] = board[i-2][j+2];
				count++;
			}
		}	
		
		// Check left jump
		if(isIndexBounded(i,j-2)){
			if(monkeyCanJump(state,i,j-2)) {
				allPossibleMoves[count] = board[i][j-2];
				count++;
			}
		}
		
		// Check right jump
		if(isIndexBounded(i,j+2)){
			if(monkeyCanJump(state,i,j+2)) {
				allPossibleMoves[count] = board[i][j+2];
				count++;
			}
		}	
		
		// Check bottom left jump
		if(isIndexBounded(i+2,j-2)){
			if(monkeyCanJump(state,i+2,j-2)) {
				allPossibleMoves[count] = board[i+2][j-2];
				count++;
			}
		}	
		
		// Check bottom middle jump
		if(isIndexBounded(i+2,j)){
			if(monkeyCanJump(state,i+2,j)) {
				allPossibleMoves[count] = board[i+2][j];
				count++;
			}
		}
		
		// Check bottom left jump
		if(isIndexBounded(i+2,j+2)){
			if(monkeyCanJump(state,i+2,j+2)) {
				allPossibleMoves[count] = board[i+2][j+2];
				count++;
			}
		}
			
		return allPossibleMoves;
	}

	// Displays all possible moves for the elephant piece 
	public String[] allPossibleElephantMove(State state){
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
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
		
		// Set to 10 for max amount of moves 
		String[] allPossibleMoves = new String[10];
		int count = 0; 
		
		// Check 3x3 around piece
				for(int x = i - 1; x <= i + 1; x++) {
					for(int y = j - 1; y <= j + 1; y++) {
						if(isIndexBounded(x,y)){
							if(x > 3 && y > 1 && y < 5) {
								if(board[x][y].charAt(2) == 'N' || board[x][y].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
									allPossibleMoves[count] = board[x][y];
									count++;
								}
							}
						}
					}
				}
		
		// Check for 'Flying General' conditions 
		boolean pieceInbetween = false;
		int counter = i - 1;
		while(!pieceInbetween && counter >= 0) {
			if(board[counter][j].charAt(2) != 'N') {
				if(board[counter][j].charAt(3) == 'L' && counter != i ) {
					allPossibleMoves[count] = board[counter][j];
					count++;
				} else {
					pieceInbetween = true; 
				}
				
			}
			counter--;
		}
		// Diagonal Case
		if( (i == 4 && j == 2) || (i == 4 && j == 4) ) {
			if(board[2][4].charAt(3) == 'L' || board[2][2].charAt(3) == 'L') {
				allPossibleMoves[count] = board[i][j];
				count++;
			}
		}
	
//		for(int k = 0; k < allPossibleMoves.length; k++) {
//			System.out.println(allPossibleMoves[k]);
//		}
		
		return allPossibleMoves;
	}

	// Displays all possible moves for the crocodile piece
	public String[] allPossibleCrocodileMove(State state){
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
		String[][] board = state.getBoard();
		
		// Set to 15 for max amount of moves 
		String[] allPossibleMoves = new String[20];
		int count = 0; 
		
		// Check for river 
		if(i == 3) {
			
			// Check Left 
			int riverCounter = j - 1;
			while(riverCounter >= 0){
				if(board[i][riverCounter].charAt(2) == 'N'){
					allPossibleMoves[count] = board[i][riverCounter];
					count++;
				}else if (board[i][riverCounter].charAt(2) != state.getCurrentTurnColor().charAt(0)){
					allPossibleMoves[count] = board[i][riverCounter];
					count++;
					break;
				}else{
					break;
				}
				riverCounter--;
			}

			// Check Right 
			riverCounter = j + 1;
			while(riverCounter <= 6){
				if(board[i][riverCounter].charAt(2) == 'N'){
					allPossibleMoves[count] = board[i][riverCounter];
					count++;
				}else if (board[i][riverCounter].charAt(2) != state.getCurrentTurnColor().charAt(0)){
					allPossibleMoves[count] = board[i][riverCounter];
					count++;
					break;
				}else{
					break;
				}
				riverCounter++;
			}
		}
		
		// Check 3x3 with piece in center 
		for(int x = i - 1; x <= i + 1; x++) {
			for(int y = j - 1; y <= j + 1; y++) {
				//System.out.println("Checking "  + " X: " + x + " Y: "+ y);
				if(isIndexBounded(x,y)){
					if(board[x][y].charAt(2) == 'N' || board[x][y].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
						//System.out.println("Adding " + board[x][y] + " X: " + x + " Y: "+ y);
						allPossibleMoves[count] = board[x][y];
						count++;
					}
				}
			}
		}

		// If above or below the river 
		if(i > 3) {
			int riverCounter = i - 1;
			while( riverCounter != 3) {
				if((board[riverCounter][j].charAt(2) != 'N')){
					break;
				}else {
					if(!containsMove(allPossibleMoves, board[riverCounter][j])){
						allPossibleMoves[count] = board[riverCounter][j];
						count++;
					}
						
				}
				riverCounter--;
			}
			allPossibleMoves[count] = board[3][j];
			count++;
		}else if(i < 3){
			int riverCounter = i + 1;
			while( riverCounter != 3) {
				if((board[riverCounter][j].charAt(2) != 'N')){
					break;
				}
				else {
					if(!containsMove(allPossibleMoves, board[riverCounter][j])){
						allPossibleMoves[count] = board[riverCounter][j];
						count++;
					}
				}
				riverCounter++;
			}
			allPossibleMoves[count] = board[3][j];
		}
		
		
		
//		for(int k = 0; k < allPossibleMoves.length; k++) {
//			System.out.println(allPossibleMoves[k]);
//		}
		
		return allPossibleMoves;
	}

	// Displays all possible moves for the zebra piece 
	public String[] allPossibleZebraMove(State state){	
		int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
		int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
		
		String[][] board = state.getBoard();
		
		// Set to 8 for max amount of moves 
		String[] allPossibleMoves = new String[8];
		int count = 0; 
		
//		System.out.println("State in Z: "+ state.toString());
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
		
//		for(int k = 0; k < allPossibleMoves.length;k++) {
//			System.out.println("Possible Zebra: " + allPossibleMoves[k]);
//		}
		
		return allPossibleMoves;
	}
	
	//TODO: Logic that deals with river and capture/move mechanic (when moving backwards cant jump or capture)
	// Displays the possible moves for the pawn and super pawn
		public String[] allPossiblePawnMove(State state){
			String[][] board = state.getBoard();
			//  Set to 10 as its above pawn max available moves 
			String[] allPossibleMoves = new String[20];
			int count = 0; 
			int i = Character.getNumericValue(state.getPieceSelected().charAt(0));
			int j = Character.getNumericValue(state.getPieceSelected().charAt(1));
			// Go through three tiles in front of pawn 
			for (int x = (i - 1); x < i; x ++){
				for(int y = (j - 1); y <= j + 1; y++){
					if(isIndexBounded(x,y))
						// Check if an empty space or enemy piece
						if(board[x][y].charAt(2) == 'N' || board[x][y].charAt(2) != state.getCurrentTurnColor().charAt(0)){
							allPossibleMoves[count] = board[x][y];
							count++;
						}
				}
			}
			// Check for beyond river moves 
			if(state.getPieceSelected().charAt(0) == 'P' && state.getPieceSelected().charAt(0) <= 3) {
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
			if(state.getPieceSelected().charAt(3) == 'S') {
				//Check bounds then check if null for move and enemy opponent for capture 
				// Check left side 
				if (isIndexBounded(i,j-1)) {
					if(board[i][j-1].charAt(3) == 'N' || board[i][j-1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
						allPossibleMoves[count] = board[i][j-1];
						count++;
					}
				}
				// Check right side 
				if (isIndexBounded(i,j+1) ) {
					if(board[i][j+1].charAt(3) == 'N' || board[i][j+1].charAt(2) != state.getCurrentTurnColor().charAt(0)) {
						allPossibleMoves[count] = board[i][j+1];
						count++;
					}
				}
				// Check first left diagonal  
				if (isIndexBounded(i+1,j-1)) {
					if(state.board[i+1][j-1].charAt(3) == 'N') {
						allPossibleMoves[count] = board[i+1][j-1];
						count++;
						// Check second further left diagonal  
						if (isIndexBounded(i+2,j-2)) {
							if(state.board[i+2][j-2].charAt(3) == 'N') {
								allPossibleMoves[count] = board[i+2][j-2];
								count++;
							}
						}
					}
				}
				// Check first backwards 
				if (isIndexBounded(i+1,j)) {
					if(state.board[i+1][j].charAt(3) == 'N') {
						allPossibleMoves[count] = board[i+1][j];
						count++;
						// Check second backwards 
						if (isIndexBounded(i+2,j)) {
							if(state.board[i+2][j].charAt(3) == 'N') {
								allPossibleMoves[count] = board[i+2][j];
								count++;
							}
						}
					}
				}
				// Check first right orthogonal  
				if (isIndexBounded(i+1,j+1)) {
					if(state.board[i+1][j+1].charAt(3) == 'N') {
						allPossibleMoves[count] = board[i+1][j+1];
						count++;
						// Check second further right orthogonal  
						if (isIndexBounded(i+2,j+2)) {
							if(state.board[i+2][j+2].charAt(3) == 'N') {
								allPossibleMoves[count] = board[i+2][j+2];
								count++;
							}
						}
					}
				}
			}
//			for(int k = 0; k < allPossibleMoves.length; k++) {
//				System.out.println(allPossibleMoves[k]);
//			}
			return allPossibleMoves;
		}	
		
		//TODO: Update GUI with after found moves 
	// Update the GUI with all the possible moves 
		public String[] displayPossibleMoves(State state){
			
			String[] moveArray = null;
//			System.out.println("State in displayPossibleMoves: " + state.toString());
			switch(state.getPieceSelected().charAt(3)) {
				case 'G':
					moveArray = allPossibleGiraffeMove(state);
					return moveArray;
				case 'M':
					moveArray = allPossibleMonkeyMove(state);
					return moveArray;
				case 'E':
					moveArray = allPossibleElephantMove(state);
					return moveArray;
				case 'L':
					moveArray = allPossibleLionMove(state);
					return moveArray;
				case 'C':
					moveArray = allPossibleCrocodileMove(state);
					return moveArray;
				case 'Z':
					moveArray = allPossibleZebraMove(state);
					return moveArray;
				case 'P':
				case 'S':
					moveArray = allPossiblePawnMove(state);
					return moveArray;
				case 'N':
						break;  
				default:
					System.out.println("Error: Board value "+ state.getPieceSelected() + " not found");	  
			}
			
			return moveArray;
		
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
		
//		System.out.println("State Value in movePossible: " + state.toString());
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
			case 'S':
				possibleMoves = allPossiblePawnMove(state);
				return containsMove(possibleMoves, newPosition);
			case 'N':
				// TODO: Error message or handle somehow
				System.out.println("Error: Empty tile passed into piece logic");
				  
			default:
				System.out.println("Error: Board value "+ state.getPieceSelected().charAt(3) + " not found");
				System.exit(1);
		  }

		  return false;
		
	}
	
	// Move piece to destination and update GUI 
	public void movePiece(State state, String newPosition){

		// Board that will take over state after move G
		String[][] newBoard = state.getBoard();

		// Erase old piece location
		System.out.println("GmaeLOgic"+state.getPieceSelected());
//		System.out.println("Error line before:"+Character.toString(state.getPieceSelected().charAt(0))+Character.toString(state.getPieceSelected().charAt(1)));
		String[] piecePosition = {Character.toString(state.getPieceSelected().charAt(0)), Character.toString(state.getPieceSelected().charAt(1))};
		int[] pieceCoordinates = {Integer.parseInt(piecePosition[0]), Integer.parseInt(piecePosition[1])};
		String deleteOld = Integer.toString(pieceCoordinates[0]) + Integer.toString(pieceCoordinates[1]) + "NN";
		newBoard[(int)pieceCoordinates[0]][(int)pieceCoordinates[1]] = deleteOld;

		// Move piece to new position 
//		String replaceNew = Character.toString(state.getCurrentClick().charAt(0)) + Character.toString(state.getCurrentClick().charAt(1)) + Character.toString(state.getPieceSelected().charAt(2)) + Character.toString(state.getPieceSelected().charAt(3)); 
		String replaceNew = Character.toString(newPosition.charAt(0)) + Character.toString(newPosition.charAt(1)) + Character.toString(state.getPieceSelected().charAt(2)) + Character.toString(state.getPieceSelected().charAt(3)); 
		int[] newPositionCoordinates = {Integer.parseInt(Character.toString(newPosition.charAt(0))), Integer.parseInt(Character.toString(newPosition.charAt(1))) };
		newBoard[newPositionCoordinates[0]][newPositionCoordinates[1]] = replaceNew;

		//TODO: Update GUI with new gameboard
		//updateGUI()

		//Update state
		state.setBoard(newBoard);
		//state.updateState(state);
	}
	
	
	// Checks for the three end game conditions
		public boolean isGameOver(State state){
			String[][] board = state.getBoard();
			boolean returnVar = false;
			// Lion against lion and at least one piece
			int lionCounter = 0;
			int pieceCounter = 0;
			char lionColor = '\0';
			for(int i = 0; i < 7; i++) {
				for(int j = 0; j < 7; j++) {
					if(board[i][j].charAt(3) == 'L' ) {
						if(lionColor != '\0') {
							lionColor = board[i][j].charAt(2);
						}
						lionCounter++;
					}
					if( board[i][j].charAt(3) != 'L' && board[i][j].charAt(3) != 'N') { 
						if(board[i][j].charAt(2) == lionColor) {
							returnVar = false;
						}
						pieceCounter++;
					}
					if(lionCounter == 2 && pieceCounter >= 1) {
						return true;
					}
				}
			}
			// Lion captured 
			lionCounter = 0;
			for(int i = 0; i < 7; i++) {
				for(int j = 0; j < 7; j++) {
					if( board[i][j].charAt(3) == 'L') {
						lionCounter++;
					}
				}
			}
			if(lionCounter == 1) {

				return true;
			}
			// Draw condition. Only two lions left 
			lionCounter = 0;
			for(int i = 0; i < 7; i++) {
				for(int j = 0; j < 7; j++) {
					// If any other piece but lion, return false
					if(board[i][j].charAt(3) == 'N') {
						if(board[i][j].charAt(3) == 'L') {
							lionCounter++;
						}
					}else {
						returnVar = false;
					}
					if(lionCounter == 2) {
						return true;
					}
				}
			} 
			return returnVar;
		}
	//
	// Main Logic 
	// 
	
	// TODO: Due to structure change, a lot of this logic may be deleted and placed into CongoBoard.java
	// Gets called for every click on the board, checks validity of moves and displays possible moves 
	public void mainLogic(State state) {
		
//		System.out.println("In logic");
		
		// If end turn button is clicked
		if (state.getCurrentClick() == "ENDT") {
			if(isGameOver(state)) {
				// TODO: Display some end game message and update database history 
			}
			
			// If game is not over, end turn and flip board
			state.setBoard(flipBoard(state));
			
			// TODO: update DB with turn and current state 
			
			
			// Only one function call per click 
			return;
		}
		
		
		// If piece clicked 
		if (state.getPieceSelected().charAt(2) != 'N' ) {
			
			// If own piece 
			if (state.getPieceSelected().charAt(2) == state.getCurrentTurnColor().charAt(0)) {
				// Display possible moves 
				displayPossibleMoves(state);
				
				//Only one function call per click 
				return; 
			}
		
		// If enemy piece or empty space 	
		if (state.getPieceSelected().charAt(2) != 'N' )
			
			if(isMovePossible(state)) {
				movePiece(state, state.getCurrentClick());
				
				//Only one function call per click 
				return; 
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
								{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
								{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"}, 
								{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"}, 
								{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"}, 
								{"50WP", "51WP", "52NN", "53WL", "54NN", "55NN", "56NN"}, 
								{"60WG", "61WM", "62WE", "63NN", "64NN", "65WC", "66NN"}
							};
		
		// TODO: Change to receive from database
		State state = new State(board,"W","45NN","66WZ");    
		
		// Starts all the logic
		GameLogic game = new GameLogic();
		game.mainLogic(state);
		game.flipBoard(state);
		
//		System.out.println("Done");
//		System.out.print(state.toString());
	}
}