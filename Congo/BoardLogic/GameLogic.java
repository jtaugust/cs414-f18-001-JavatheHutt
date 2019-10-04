package BoardLogic;
//
// GameLogic.java 
// 
// This class contains all the necessary functions to play Congo, it communicates with the GUI 
// and database to determine possible moves and update GUI baed on actions 
//

public class GameLogic extends State{
	State state;

	// Constructor used to start game logic upon creation of an object
	public GameLogic(State state) {
		mainLogic(state);
	}

	//
	// Game Piece Logic 
	//
	public String[] allPossibleGiraffeMove(State state){
		return null;
	} 

	public String[] allPossibleMonkeyMove(State state){
		return null;
	}

	public String[] allPossibleElephantMove(State state){
		return null;
	}

	public String[] allPossibleLionMove(State state){
		return null;
	}

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

	public String[] allPossibleZebraMove(State state){
		return null;
	}
	
	//TODO: Logic that changes pawn to super pawn(possibly in moves)
	public String[] allPossiblePawnMove(State state){
		String[][] board = state.getBoard();
		
		//  Set to 10 as its above pawn max avaiable moves 
		String[] allPossibleMoves = new String[10];
		int count = 0; 
		if(state.currentClick.charAt(3) == 'S'){
			
			int i = 0;
			int j = 0;
			while (true){
				
				// Check if an empty space or enemy piece
				if(board[i][j].charAt(2) == 'N' || board[i][j] == enemyColor){
					allPossibleMoves[count] = board[i][j];
				}
				
				
			}


		} else {
			// Go through three tiles in front of pawn 
			int[] pieceLocaiton = {state.currentClick.charAt(0), state.currentClick.charAt(1)};
			
			// 

			for (int i = (pieceLocaiton[0] - 1) ; i < pieceLocaiton[0] + 3; i ++){
				for(int j = pieceLocation[1] - 1; j < pieceLocation[1]; j++){
					
					// Check if an empty space or enemy piece
					if(board[i][j].charAt(2) == 'N' || board[i][j] == enemyColor){
						allPossibleMoves[count] = board[i][j];
					}
				}
			}


		}
		return allPossibleMoves;
	}	



	// Update the GUI with all the possible moves 
	public void displayPossibleMoves(State state){
		String[][] moveArray;
		switch(state.getPieceSelected()) {
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
				System.out.println("Error: Board value "+ piece + " not found");	  
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
		
		//String[] possibleMoves;
		// TODO: Remove later, just for testing
		String[] possibleMoves = {"40NN"};
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
				//possibleMoves = allPossiblePawnMove(state);
				return containsMove(possibleMoves, newPosition);

			case 'N':
				// TODO: Error message or handle somehow
				  
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
		
		// TODO: Change to recieve from database
		State state = new State(board,"W","40NN","50WP");    
		
		// Starts all the logic
		GameLogic game = new GameLogic(state);

		System.out.println("Done");
		System.out.print(state.toString());
	}
}
