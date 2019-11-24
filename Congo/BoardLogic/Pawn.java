package BoardLogic;

public class Pawn extends Piece{
    public int possibleMovesIndex = 0;
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
                        System.out.println(allPossibleMoves[count][0] + " " + allPossibleMoves[count][1]);
                        count++;
                       
                    }
            }
        }
        // Check for beyond river moves 
        if(i <= 3) {
            System.out.println("In iff");
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
       
        // for(int k = 0; k < allPossibleMoves.length; k++) {
        //     System.out.println(allPossibleMoves[count][0] + " " + allPossibleMoves[count][1]);
        // }
        this.possibleMovesIndex = count;
        return allPossibleMoves;
    }	
    
    
}