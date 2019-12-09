package BoardLogic;

public class GameLogic extends State{
	State state;
	
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