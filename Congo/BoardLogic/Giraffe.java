package BoardLogic;

public class Giraffe extends Piece{

    public Giraffe(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
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
    
    // Returns all the possible moves for pawn and super pawn
    @Override
    public int[][] legalMoves(State state) {
        int i = this.getRow();
		int j = this.getColumn();
		
		Piece[][] board = state.getBoard();
		
		// Set to 15 for max amount of moves 
		int[][] allPossibleMoves = new int[20][2];
		formatMoveArray(allPossibleMoves);
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
                        if(board[x][y] != null) {	
                            // Checks if enemy 
                            if(board[x][y].getColor() != state.getCurrentTurnColor()) {	
                                // Checks for odd/even based on i and j
                                if(isOnCorrectIndex(x,y,row,col)) {
                                    allPossibleMoves[count][0] = x;
                                    allPossibleMoves[count][1] = y;
                                    count++;
                                }
                            }	
                        }
					}
					// Check inside (move only)
					else {
						if(board[x][y] == null) {
                            allPossibleMoves[count][0] = x;
                            allPossibleMoves[count][1] = y;
							count++;
						}
					}	
				}
			}
		}	

		return allPossibleMoves;
    }	
    
    
}