package BoardLogic;

public class Crocodile extends Piece{

    public Crocodile(int row, int column, char color, char pieceType) {
        super(row, column, color, pieceType);
    }

    // Displays all possible moves for the crocodile piece
    @Override
	public int[][] legalMoves(State state){
        // System.out.print("IN croc");
        int i = this.getRow();
		int j = this.getColumn();
		
		Piece[][] board = state.getBoard();
		
		// Set to 15 for max amount of moves 
		int[][] allPossibleMoves = new int[20][2];
		formatMoveArray(allPossibleMoves);
		int count = 0; 
		
		// Check for river 
		if(i == 3) {
			
			// Check Left 
			int riverCounter = j - 1;
			while(riverCounter >= 0){
				if(board[i][riverCounter] == null){
					allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = riverCounter;
					count++;
				}else if (board[i][riverCounter].getColor() != state.getCurrentTurnColor()){
					allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = riverCounter;
					count++;
					break;
				}else{
					break;
				}
				riverCounter--;
			}

			// Check Right 
			riverCounter = j + 1;
			while(riverCounter <= 6){
				if(board[i][riverCounter] == null){
					allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = riverCounter;
					count++;
				}else if (board[i][riverCounter].getColor() != state.getCurrentTurnColor()){
                    allPossibleMoves[count][0] = i;
                    allPossibleMoves[count][1] = riverCounter;
					count++;
					break;
				}else{
					break;
				}
				riverCounter++;
			}
		}
		
		// Check 3x3 with piece in center 
		for(int x = i - 1; x <= i + 1; x++) {
			for(int y = j - 1; y <= j + 1; y++) {
				//System.out.println("Checking "  + " X: " + x + " Y: "+ y);
				if(isIndexBounded(x,y) && !containsMove(allPossibleMoves, new int[] {x,y})){
					if(board[x][y] == null || board[x][y].getColor() != state.getCurrentTurnColor()) {
						//System.out.println("Adding " + board[x][y] + " X: " + x + " Y: "+ y);
                        allPossibleMoves[count][0] = x;
                        allPossibleMoves[count][1] = y;
						count++;
					}
				}
			}
		}

		// If above or below the river 
		if(i > 3) {
			int riverCounter = i - 1;
			while( riverCounter != 3) {
				if((board[riverCounter][j] == null)){
					break;
				}else {
					if(!containsMove(allPossibleMoves, new int[] {riverCounter,j})){
                        allPossibleMoves[count][0] = riverCounter;
                        allPossibleMoves[count][1] = j;
						count++;
					}
						
				}
				riverCounter--;
			}

            allPossibleMoves[count][0] = 3;
            allPossibleMoves[count][1] = j;
			count++;
		}else if(i < 3){
			int riverCounter = i + 1;
			while( riverCounter != 3) {
				if((board[riverCounter][j] == null)){
					break;
				}
				else {
					if(!containsMove(allPossibleMoves, new int[] {riverCounter,j})){
                        allPossibleMoves[count][0] = riverCounter;
                        allPossibleMoves[count][1] = j;
						count++;
					}
				}
				riverCounter++;
			}
            allPossibleMoves[count][0] = 3;
            allPossibleMoves[count][1] = j;
		}
		
		
		
//		for(int k = 0; k < allPossibleMoves.length; k++) {
//			System.out.println(allPossibleMoves[k]);
//		}
		
		return allPossibleMoves;
	}
}