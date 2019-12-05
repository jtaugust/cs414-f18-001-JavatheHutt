package BoardLogic;

//
// GameLogic.java 
// 
// This class contains all the necessary functions to play Congo, it communicates with the GUI 
// and database to determine possible moves and update GUI based on actions 
//

public class GameLogic extends State{
	State state;

	public GameLogic() {
		//mainLogic(state);
	}
	
	// Flips board to ensure same logic for both sides 
	public Piece[][] flipBoard(State state){
		Piece[][] board = state.getBoard();
		Piece[][] newBoard = new Piece[7][7];
		Piece[][] indexBoard = board;
		
		// Go through backwards and swap pieces by taking index of array copy and piece color and type from real board
		int countI = 0;
		int countJ = 0;
		for (int i = 6; i >= 0; i--) { 
            for (int j = 6; j >= 0; j--) {
				
				// Check if current piece is null
				if(board[i][j] != null){
					
					// Check if piece to be flipped is null
					if(indexBoard[countI][countJ] != null){
						Piece newPiece = board[i][j];
						newPiece.setRow(indexBoard[countI][countJ].getRow());
						newPiece.setColumn(indexBoard[countI][countJ].getColumn());
						
						newBoard[countI][countJ] = newPiece;
					}else{
						newBoard[countI][countJ] = null;
					}
					
					
				}else{
					newBoard[countI][countJ] = null;
				}
				countJ++;
            }
            countI++;
    		countJ = 0;
        } 
		state.setBoard(newBoard);

		return newBoard;
	}
		
// 	// Update the GUI with all the possible moves 
// 	public int[][] displayPossibleMoves(State state){
		
// 		int[][] moveArray = new int[20][2];
// 		int row = state.getPieceSelected().getRow();
// 		int col = state.getPieceSelected().getColumn();

// 		// Determine which piece to return moves for
// 		switch(state.getPieceSelected().getType()) {
// 			case 'G':
// 				Giraffe giraffe = new Giraffe(row,col,state.getCurrentTurnColor(),'G');
// 				return giraffe.legalMoves(state);
				
// 			case 'M':
// 				Monkey monkey = new Monkey(row,col,state.getCurrentTurnColor(),'M');
// 				return monkey.legalMoves(state);
					
// 			case 'E':
// 				Elephant elephant = new Elephant(row,col, state.getCurrentTurnColor(), 'E');
// 				return elephant.legalMoves(state);
													
// 			case 'L':
// 				Lion lion = new Lion(row,col, state.getCurrentTurnColor(),'L');
// 				return lion.legalMoves(state);
					
// 			case 'C':
// 				Crocodile crocodile = new Crocodile(row, col, state.getCurrentTurnColor(), 'C');
// 				return  crocodile.legalMoves(state);
				
// 			case 'Z':
// 				Zebra zebra = new Zebra(row,col,state.getCurrentTurnColor(),'Z');
// 				return zebra.legalMoves(state);
				
// 			case 'P':
// 				Pawn pawn = new Pawn(row, col, state.getCurrentTurnColor(), 'P');
// 				return pawn.legalMoves(state);
				
// 			case 'S':
// 				SuperPawn superPawn = new SuperPawn(row, col, state.getCurrentTurnColor(), 'S');
// 				return superPawn.legalMoves(state);
				
// 			case 'N':
// 					break;  
// 			default:
// 				System.out.println("Error: Board value "+ state.getPieceSelected().toString() + " not found");	  
// 		}
		
// 		return moveArray;

// 	}

// 	// Checks if move is possible based on piece
// 	public boolean isMovePossible(State state){
// 		int[] newPosition = state.getCurrentClick();
		
// //		System.out.println("State Value in movePossible: " + state.toString());
// 		int[][] possibleMoves;
// 		int row = state.getPieceSelected().getRow();
// 		int col = state.getPieceSelected().getColumn();

// 		switch(state.getPieceSelected().getType()) {
// 			case 'G':
// 				Giraffe giraffe = new Giraffe(row,col,state.getCurrentTurnColor(),'G');
// 				possibleMoves = giraffe.legalMoves(state);
// 				return giraffe.containsMove(possibleMoves, newPosition);
				
// 			case 'M':
// 				Monkey monkey = new Monkey(row,col,state.getCurrentTurnColor(),'M');
// 				possibleMoves = monkey.legalMoves(state);
// 				return monkey.containsMove(possibleMoves, newPosition);
					
// 			case 'E':
// 				Elephant elephant = new Elephant(row,col, state.getCurrentTurnColor(), 'E');
// 				possibleMoves = elephant.legalMoves(state);
// 				return elephant.containsMove(possibleMoves, newPosition);
								  					
// 			case 'L':
// 				Lion lion = new Lion(row,col, state.getCurrentTurnColor(),'L');
// 				possibleMoves = lion.legalMoves(state);
// 				return lion.containsMove(possibleMoves, newPosition);
					  
// 			case 'C':
// 				Crocodile crocodile = new Crocodile(row, col, state.getCurrentTurnColor(), 'C');
// 				possibleMoves = crocodile.legalMoves(state);
// 				return crocodile.containsMove(possibleMoves, newPosition);
			 	
// 			case 'Z':
// 				Zebra zebra = new Zebra(row,col,state.getCurrentTurnColor(),'Z');
// 				possibleMoves = zebra.legalMoves(state);
// 				return zebra.containsMove(possibleMoves, newPosition);
			  	
// 			case 'P':
// 				Pawn pawn = new Pawn(row, col, state.getCurrentTurnColor(), 'P');
// 				possibleMoves = pawn.legalMoves(state);
// 				return pawn.containsMove(possibleMoves, newPosition);

// 			case 'S':
// 				SuperPawn superPawn = new SuperPawn(row, col, state.getCurrentTurnColor(), 'S');
// 				possibleMoves = superPawn.legalMoves(state);
// 				return superPawn.containsMove(possibleMoves, newPosition);
// 			case 'N':
// 				// TODO: Error message or handle somehow
// 				System.out.println("Error: Empty tile passed into piece logic");
				  
// 			default:
// 				System.out.println("Error: Board value "+ state.getPieceSelected().toString() + " not found");
// 				System.exit(1);
// 		  }

// 		  return false;
		
// 	}

	// // Move piece to destination and update GUI 
	// public void movePiece(State state, Piece newPosition){

	// 	// Board that will take over state after move G
	// 	Piece[][] newBoard = state.getBoard();
		
	// 	// Set old piece position to null
	// 	int oldX = state.getPieceSelected().getRow();
	// 	int oldY = state.getPieceSelected().getColumn();

	// 	newBoard[oldX][oldY] = null;
		
	// 	// Place new piece
	// 	int newX = newPosition.getRow();
	// 	int newY = newPosition.getColumn();

	// 	newBoard[newX][newY] = newPosition;

	// 	//Update state
	// 	state.setBoard(newBoard);
	// }
	
	
	// Checks for the three end game conditions
	public char isGameOver(State state){
		Piece[][] board = state.getBoard();

		char returnVar = 'A';

		// White Lion and at least one piece against black lion (White Wins)
		int lionCounter = 0;
		int pieceCounter = 0;
		char lionColor = '\0';

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(board[i][j] != null ){
					if(board[i][j].getType() == 'L' ) {
						lionCounter++;
					}
					if( board[i][j].getType() != 'L' && board[i][j].getType() != 'N') { 
						if(board[i][j].getColor() == 'B') {
							returnVar = 'N';
						}
						pieceCounter++;
					}
				}
				if(lionCounter == 2 && pieceCounter >= 1 && returnVar != 'N') {
					return 'W';
				}
			}
		}

		// Black Lion and at least one piece against white lion (Black Wins)
		lionCounter = 0;
		pieceCounter = 0;
		lionColor = '\0';
		returnVar = 'A';

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(board[i][j] != null ){
					if(board[i][j].getType() == 'L' ) {
						lionCounter++;
					}
					if( board[i][j].getType() != 'L' && board[i][j].getType() != 'N') { 
						if(board[i][j].getColor() == 'W') {
							returnVar = 'N';
						}
						pieceCounter++;
					}
				}
				if(lionCounter == 2 && pieceCounter >= 1 && returnVar != 'N') {
					return 'B';
				}
			}
		}

		// Lion captured 
		lionCounter = 0;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(board[i][j] != null ){
					if( board[i][j].getType() == 'L') {
						lionCounter++;
						lionColor = board[i][j].getColor();
					}
				}
			}
		}
		if(lionCounter == 1) {
			return lionColor;
		}

		// Draw condition. Only two lions left 
		lionCounter = 0;
		returnVar = 'A';

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(board[i][j] != null) {
					if(board[i][j].getType() == 'L') {
						lionCounter++;
					}else {
						returnVar = 'N';
					}
				}
				
			}
		} 
		if(lionCounter == 2 && returnVar != 'N') {
			return 'D';
		}
		
		return 'N';
	}


}