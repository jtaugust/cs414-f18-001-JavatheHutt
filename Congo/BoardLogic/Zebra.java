package BoardLogic;

public class Zebra extends Piece{
	public Zebra(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
    }
	public int[][] legalMoves(State state){
		Piece[][] board = state.getBoard();
		
        //  Set to 20 as its above pawn max available moves 
		int[][] allPossibleMoves = new int[20][2];
		formatMoveArray(allPossibleMoves);
		
        int i = this.getRow();
        int j = this.getColumn();
		
		// Set to 10 for max amount of moves 
		int count = 0; 
		
		// Top left 1
		if (isIndexBounded(i-1,j-2)) {
			if(board[i-1][j-2]== null || board[i-1][j-2].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i-1;
				allPossibleMoves[count][1]=j-2;
				count++;
			}
		} 
		
		// Top Left 2
		if (isIndexBounded(i-2,j-1)) {
			if(board[i-2][j-1]== null || board[i-2][j-1].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i-2;
				allPossibleMoves[count][1]=j-1;
				count++;
			}
			
		}

		// Top Right 1
		if (isIndexBounded(i-2,j+1)) {
			if(board[i-2][j+1]== null || board[i-2][j+1].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i-2;
				allPossibleMoves[count][1]=j+1;
				count++;
			}
			
		}
		
		// Top Right 2
		if (isIndexBounded(i-1,j+2)) {
			if(board[i-1][j+2] == null || board[i-1][j+2].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i-1;
				allPossibleMoves[count][1]=j+2;
				count++;
			}
			
		}
		
		// Bottom Right 1
		if (isIndexBounded(i+1,j+2)) {
			if(board[i+1][j+2] == null || board[i+1][j+2].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i+1;
				allPossibleMoves[count][1]=j+2;
				count++;
			}
			
		}
		
		// Bottom Right 2
		if (isIndexBounded(i+2,j+1)) {
			if(board[i+2][j+1] == null || board[i+2][j+1].getColor ()!= this.getColor()) {
				allPossibleMoves[count][0] = i+2;
				allPossibleMoves[count][1]=j+1;
				count++;
			}
			
		}
		
		// Bottom left 1
		if (isIndexBounded(i+2,j-1)) {
			if(board[i+2][j-1] == null || board[i+2][j-1].getColor()!= this.getColor()) {
				allPossibleMoves[count][0] =i+2;
				allPossibleMoves[count][1]=j-1;
				count++;
			}
			
		}
		
		// Bottom Left 2
		if (isIndexBounded(i+1,j-2)) {
			if(board[i+1][j-2] == null || board[i+1][j-2].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i+1;
				allPossibleMoves[count][1]=j-2;
				count++;
			}
			
		}
		
		
		return allPossibleMoves;
	}
}