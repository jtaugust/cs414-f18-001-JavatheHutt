package BoardLogic;

public class Pawn extends Piece{

    public Pawn(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
    }

    // Returns all the possible moves for pawn and super pawn
    @Override
    public int[][] legalMoves(State state) {
        
        Piece[][] board = state.getBoard();
        //  Set to 20 as its above pawn max available moves 
        int[][] allPossibleMoves = new int[20][2];
        int count = 0; 
        int i = this.getRow();
        int j = this.getColumn();
        // Go through three tiles in front of pawn 
        for (int x = (i - 1); x < i; x ++){
            for(int y = (j - 1); y <= j + 1; y++){
                if(isIndexBounded(x,y))
                    // Check if an empty space or enemy piece
                    if(board[x][y] == null || board[x][y].getColor() != state.getCurrentTurnColor()){
                        allPossibleMoves[count][0] = x;
                        allPossibleMoves[count][1] = y;
                        count++;
                    }
            }
        }
        // Check for beyond river moves 
        if(state.getPieceSelected().charAt(0) == 'P' && state.getPieceSelected().charAt(0) <= 3) {
            // Check first backwards 
            if (isIndexBounded(i+1,j)) {
                if(board[i-1][j-1] == null) {
                    allPossibleMoves[count][0] = i + 1;
                    allPossibleMoves[count][1] = j;
                    count++;
                }
            }
            // Check second backwards 
            if (isIndexBounded(i+2,j)) {
                if(board[i-1][j-1] == null) {
                    allPossibleMoves[count][0] = i + 2;
                    allPossibleMoves[count][1] = j;
                    count++;
                }
            }
        }
        if(state.getPieceSelected().charAt(3) == 'S') {
            //Check bounds then check if null for move and enemy opponent for capture 
            // Check left side 
            if (isIndexBounded(i,j-1)) {
                if(board[i][j-1] == null || board[i][j-1].getColor() != state.getCurrentTurnColor()) {
                    allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = j - 1;
                    count++;
                }
            }
            // Check right side 
            if (isIndexBounded(i,j+1) ) {
                if(board[i][j+1] == null || board[i][j+1].getColor() != state.getCurrentTurnColor()) {
                    allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = j + 1;
                    count++;
                }
            }
            // Check first left diagonal  
            if (isIndexBounded(i+1,j-1)) {
                if(board[i+1][j-1] == null) {
                    allPossibleMoves[count][0] = i + 1;
                    allPossibleMoves[count][1] = j - 1;
                    count++;
                    // Check second further left diagonal  
                    if (isIndexBounded(i+2,j-2)) {
                        if(board[i+2][j-2] == null) {
                            allPossibleMoves[count][0] = i + 2;
                            allPossibleMoves[count][1] = j - 2;
                            count++;
                        }
                    }
                }
            }
            // Check first backwards 
            if (isIndexBounded(i+1,j)) {
                if(board[i+1][j] == null) {
                    allPossibleMoves[count][0] = i + 1;
                    allPossibleMoves[count][1] = j;
                    count++;
                    // Check second backwards 
                    if (isIndexBounded(i+2,j)) {
                        if(board[i+2][j] == null) {
                            allPossibleMoves[count][0] = i + 2;
                            allPossibleMoves[count][1] = j;
                            count++;
                        }
                    }
                }
            }
            // Check first right orthogonal  
            if (isIndexBounded(i+1,j+1)) {
                if(board[i+1][j+1] == null) {
                    allPossibleMoves[count][0] = i + 1;
                    allPossibleMoves[count][1] = j + 1;
                    count++;
                    // Check second further right orthogonal  
                    if (isIndexBounded(i+2,j+2)) {
                        if(board[i+2][j+2] == null) {
                            allPossibleMoves[count][0] = i + 2;
                            allPossibleMoves[count][1] = j + 2;
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
    
    
}