package BoardLogic;

public class Lion extends Piece{

    public Lion(int row, int column, char color, char pieceType) {
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
		
		// Check 3x3 around piece
				for(int x = i - 1; x <= i + 1; x++) {
					for(int y = j - 1; y <= j + 1; y++) {
						if(isIndexBounded(x,y)){
							if(x > 3 && y > 1 && y < 5) {
								if(board[x][y] == null || board[x][y].getColor() != this.getColor()) {
									allPossibleMoves[count][0] = x;
									allPossibleMoves[count][1] = y;
//									System.out.print("Lion"+ allPossibleMoves[count][0]+","+allPossibleMoves[count][1]);
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
			if(board[counter][j] != null) {
				if(board[counter][j].getType() == 'L' && counter != i ) {
					allPossibleMoves[count][0] =counter;
					allPossibleMoves[count][1]=j;
//					System.out.print("Lion"+ allPossibleMoves[count][0]+","+allPossibleMoves[count][1]);
					count++;
				} else {
					pieceInbetween = true; 
				}
				
			}
			counter--;
		}
		// Diagonal Case
		if( (i == 4 && j == 2) || (i == 4 && j == 4) ) {
			if(board[2][4].getType() == 'L' || board[2][2].getType() == 'L') {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1] = j;
//				System.out.print("Lion"+ allPossibleMoves[count][0]+","+allPossibleMoves[count][1]);

				count++;
			}
		}
	
//		for(int k = 0; k < allPossibleMoves.length; k++) {
//			System.out.println(allPossibleMoves[k]);
//		}
		return allPossibleMoves;
    }
}