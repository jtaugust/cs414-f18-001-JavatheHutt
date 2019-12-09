package BoardLogic;

public class SuperPawn extends Pawn{

    public SuperPawn(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
    }

    // Returns all the possible moves for pawn and super pawn
    @Override
    public int[][] legalMoves(State state) {
        Piece[][] board = state.getBoard();

        //  Set to 20 as its above pawn max available moves 
        int[][] allPossibleMoves = super.legalMoves(state);
        
        // System.out.println("PMS: " );
        int count = super.possibleMovesIndex; 

        int i = this.getRow();
        int j = this.getColumn();
       
        //Check bounds then check if null for move and enemy opponent for capture 
        // Check left side 
        if (isIndexBounded(i,j-1)) {

            if(board[i][j-1] == null){
                allPossibleMoves[count][0] = i;
                allPossibleMoves[count][1] = j - 1;
                count++;
            }else if (board[i][j-1].getColor() != state.getCurrentTurnColor()) {
                allPossibleMoves[count][0] = i;
                allPossibleMoves[count][1] = j - 1;
                count++;
            }
        }
        // Check right side 
        if (isIndexBounded(i,j+1) ) {
            if(board[i][j+1] == null) {
                allPossibleMoves[count][0] = i;
                allPossibleMoves[count][1] = j + 1;
                count++;
            }
            else if(board[i][j+1].getColor() != state.getCurrentTurnColor()) {
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
        if (isIndexBounded(i+1,j) && !containsMove(allPossibleMoves, new int[] {i+1,j})) {
            if(board[i+1][j] == null) {
                allPossibleMoves[count][0] = i + 1;
                allPossibleMoves[count][1] = j;
                count++;
                // Check second backwards 
                if (isIndexBounded(i+2,j) && !containsMove(allPossibleMoves, new int[] {i+2,j})) {
                    if(board[i+2][j] == null) {
                        allPossibleMoves[count][0] = i + 2;
                        allPossibleMoves[count][1] = j;
                        count++;
                    }
                }
            }
        }
        // Check first right diagonal  
        if (isIndexBounded(i+1,j+1)) {
            if(board[i+1][j+1] == null) {
                allPossibleMoves[count][0] = i + 1;
                allPossibleMoves[count][1] = j + 1;
                count++;
                // Check second further right diagonal  
                if (isIndexBounded(i+2,j+2)) {
                    if(board[i+2][j+2] == null) {
                        allPossibleMoves[count][0] = i + 2;
                        allPossibleMoves[count][1] = j + 2;
                        count++;
                    }
                }
            }
            
        }

        return allPossibleMoves;
    }	
    
    
}