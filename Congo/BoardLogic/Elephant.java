package BoardLogic;
public class Elephant extends Piece{

    public Elephant(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
    }

    // Returns all the possible moves for pawn and super pawn
    @Override
    public int[][] legalMoves(State state){
    	Piece[][] board = state.getBoard();
        //  Set to 20 as its above pawn max available moves 
        int[][] allPossibleMoves = new int[20][2];
        int i = this.getRow();
        int j = this.getColumn();
		
		// Set to 10 for max amount of moves 
		int count = 0; 
	
		
		// Forward 1 
		if(isIndexBounded(i-1,j)) {
			if(board[i-1][j] == null || board[i-1][j].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i-1;
				allPossibleMoves[count][1]=j;
				count++;
			}
		}
		
		// Forward 2 
		if(isIndexBounded(i-2,j)) {
			if(board[i-2][j] == null || board[i-2][j].getColor()!= this.getColor()) {
				allPossibleMoves[count][0] =i-2;
				allPossibleMoves[count][1]=j;
				count++;
			}
		}
		
		// Left 1 
		if(isIndexBounded(i,j-1)) {
			if(board[i][j-1] == null || board[i][j-1].getColor() != this.getColor()) {
				allPossibleMoves[count][0] =i;
				allPossibleMoves[count][1]=j-1;
				count++;
			}
		}
		
		// Left 2
		if(isIndexBounded(i,j-2)) {
			if(board[i][j-2] == null || board[i][j-2].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1]=j-2;
				count++;
			}
		}
		
		// Right 1 
		if(isIndexBounded(i,j+1)) {
			if(board[i][j+1] == null || board[i][j+1].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1]=j+1;
				count++;
			}
		}
		
		// Right 2
		if(isIndexBounded(i,j+2)) {
			if(board[i][j+2]== null || board[i][j+2].getColor()!= this.getColor()) {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1]=j+2;
				count++;
			}
		}
		
		// Back 1 
		if(isIndexBounded(i+1,j)) {
			if(board[i+1][j] == null || board[i+1][j].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i+1;
				allPossibleMoves[count][1]=j;
				count++;
			}
		}
		
		// Back 2 
		if(isIndexBounded(i+2,j)) {
			if(board[i+2][j] == null || board[i+2][j].getColor() != this.getColor()) {
				allPossibleMoves[count][0] = i+2;
				allPossibleMoves[count][1]=j;
				count++;
			}
		}
		
		
		
		return allPossibleMoves;
	}
    }