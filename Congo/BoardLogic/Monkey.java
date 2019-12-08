package BoardLogic;

public class Monkey extends Piece{
	public Monkey(int row, int column, char color, char pieceType) {
	        super(row, column, color, pieceType);
	}

	public boolean monkeyCanJump(State state, int toX, int toY) {
		Piece[][] board = state.getBoard();
		// Check if out of bounds 
		if(toX < 0 || toX > 6 || toY < 0 || toY > 6) {
			return false;
		}
		// Check if empty space
		if(board[toX][toY] != null) {
			return false;
		}
		// If own piece return false
		int fromX =this.getRow();
		int fromY = this.getColumn();
		int cX = 0;
		int cY = 0;

		// Adjust cX and cY to be equal to piece inbetween 
		if(fromX < toX) {
			cX = toX - 1;
		}else if (fromX == toX){
			cX = toX;
		}
		else {
			cX = toX + 1;
		}
		if(fromY < toY) {
			cY = toY - 1;
		}else if (fromY == toY){
			cY = toY;
		}
		else {
			cY = toY + 1;
		}
		// Check if piece in between is own color 
		if(board[cX][cY]!=null && board[cX][cY].getColor() == this.getColor()) {
			return false;
		}

		return true;
	}

	 @Override
	public int[][] legalMoves(State state){
		Piece[][] board = state.getBoard();
		//  Set to 20 as its above pawn max available moves 
		int[][] allPossibleMoves = new int[20][2];
		formatMoveArray(allPossibleMoves);
		
		int i = this.getRow();
		int j = this.getColumn();
		
		// Set to 10 for max amount of moves 
		int count = 0; 

		// Check 3x3 around piece
		for(int x = i - 1; x <= i + 1; x++) {
			for(int y = j - 1; y <= j + 1; y++) {
				if(isIndexBounded(x,y)){
					if(board[x][y] == null ) {
						allPossibleMoves[count][0] = x;
						allPossibleMoves[count][1] = y;
						count++;
					}
				}
			}
		}
		
		// Check top left jump
		if(isIndexBounded(i-2,j-2)){
			System.out.println("I: " + i + " J: "+ j + " = " + board[i-2][j-2]);
			if(monkeyCanJump(state,i-2,j-2)) {
				allPossibleMoves[count][0] = i-2;
				allPossibleMoves[count][1] = j-2;
				count++;
			}
		}
		
		// Check top middle jump
		if(isIndexBounded(i-2,j)){
			if(monkeyCanJump(state,i-2,j)) {
				allPossibleMoves[count][0] = i-2;
				allPossibleMoves[count][1] = j;
				count++;
			}
		}
		
		// Check top right jump
		if(isIndexBounded(i-2,j+2)){
			if(monkeyCanJump(state,i-2,j+2)) {
				allPossibleMoves[count][0] = i-2;
				allPossibleMoves[count][1] = j+2;
				count++;
			}
		}	
		
		// Check left jump
		if(isIndexBounded(i,j-2)){
			if(monkeyCanJump(state,i,j-2)) {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1] = j-2;
				count++;
			}
		}
		
		// Check right jump
		if(isIndexBounded(i,j+2)){
			if(monkeyCanJump(state,i,j+2)) {
				allPossibleMoves[count][0] = i;
				allPossibleMoves[count][1] = j+2;
				count++;
			}
		}	
		
		// Check bottom left jump
		if(isIndexBounded(i+2,j-2)){
			if(monkeyCanJump(state,i+2,j-2)) {
				allPossibleMoves[count][0] =i+2;
				allPossibleMoves[count][1] = j-2;
				count++;
			}
		}	
		
		// Check bottom middle jump
		if(isIndexBounded(i+2,j)){
			if(monkeyCanJump(state,i+2,j)) {
				allPossibleMoves[count][0] = i+2;
				allPossibleMoves[count][1] = j;
				count++;
			}
		}
		
		// Check bottom left jump
		if(isIndexBounded(i+2,j+2)){
			if(monkeyCanJump(state,i+2,j+2)) {
				allPossibleMoves[count][0] = i+2;
				allPossibleMoves[count][1] = j+2;
				count++;
			}
		}
			
		if(this.capturesInATurn!=0) {
			int[][] filteredMoves= filterPossibleMoves(state,this.getRow(),this.getColumn(),allPossibleMoves);
//			for(i=0; i<filteredMoves.length;i++) {
//				System.out.println("FM"+filteredMoves[i][0]+" "+filteredMoves[i][1]);
//			}
			return filteredMoves;
		}
		return allPossibleMoves;
	}
	 
	 private int[][] filterPossibleMoves(State state, int fromRow, int fromCol, int[][] allPossibleMoves){
		 int[][] filteredMoves = new int[20][2];
		 int counter=0;
		 for(int i=0; i<allPossibleMoves.length; i++) {
			 boolean isFilteredMove=monkeyMoveFilter(state,fromRow,fromCol,allPossibleMoves[i][0], allPossibleMoves[i][1] );
			 if(isFilteredMove) {
				 filteredMoves[counter][0]= allPossibleMoves[i][0];
				 filteredMoves[counter][1]=allPossibleMoves[i][1];
				 System.out.println("FM"+filteredMoves[counter][0]+" "+filteredMoves[counter][1]);
				 counter+=1;
			 }
		 }
		 return filteredMoves;
	 }
	 
	 
	 protected boolean monkeyMoveFilter(State state, int fromRow, int fromCol, int toRow, int toCol) {
			Piece[][] board= state.getBoard();
			int jumpedRow=fromRow;
			int jumpedCol=fromCol;

			if (fromRow<toRow) {
				jumpedRow=fromRow+1;
			}
			else if(fromRow>toRow) {
				jumpedRow=fromRow-1;
			}
			if (fromCol<toCol) {
				jumpedCol=fromCol+1;
			}
			else if(fromCol>toCol){
				jumpedCol=fromCol-1;
			}
			if(isIndexBounded(jumpedRow, jumpedCol) && board[jumpedRow][jumpedCol]!=null) {
			    return true;
			}
			return false;
		}
	 
	 
	 public boolean isIndexBounded(int i, int j){
			if(i <= 6 && i >= 0) {
				if(j <= 6 && j >= 0) {
					return true;
				}
			}
			return false;
	    }
}