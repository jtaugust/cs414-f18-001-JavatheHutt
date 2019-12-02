package BoardLogic;

// 
// Abstract Piece Class for all Congo Pieces
//
public abstract class Piece{
    
    // Piece Attributes
    private int row, column;
    private char color, pieceType;

    Piece(int row, int column, char color, char pieceType){
        this.row = row;
        this.column = column;
        this.color = color;
        this.pieceType = pieceType;
    }

    // Returns a list of coordinates for the legal moves at a given 
    abstract int[][] legalMoves(State state);

    //
    // Getters 
    //

    // Returns the row of the piece
    public int getRow(){
        return this.row;
    }

    // Returns the row of the piece
    public int getColumn(){
        return this.column;
    }

    // Returns the type of piece this is
    public char getColor(){
        return this.color;
    }

    // Returns the type of piece this is
    public char getType(){
        return this.pieceType;
    }

    //
    // Setters 
    //

    // Sets a new row for the piece
    public void setRow(int newRow){
        this.row = newRow;
    }

    // Sets a new column for the piece
    public void setColumn(int newColumn){
        this.column = newColumn;
    }

    // RSets a new color for the piece
    public void setColor(char newColor){
        this.color = newColor;
    }

    // Sets a new type for the piece
    public void setType(char newType){
        this.pieceType = newType;
    }

    // Return four character string to represent piece
    public String toString(){
        String result = Integer.toString(this.row) + Integer.toString(this.column) + this.color + this.pieceType;
        return result;
    }

    //
    // Helper Methods
    //

    // Checks if the indexes given fit within 
	public boolean isIndexBounded(int i, int j){
		if(i <= 6 && i >= 0) {
			if(j <= 6 && j >= 0) {
				return true;
			}
		}
		return false;
    }
    
    // Checks if new move is in list of all possible moves
	public boolean containsMove(int[][] possibleMoves, int[] newPosition) {
		for (int i = 0; i < possibleMoves.length; i++) {
			if (possibleMoves[i][0] == newPosition[0] && possibleMoves[i][1] == newPosition[1]){
				return true;
			}
		}
		return false;
    }	
    
    // Set legalMoves array to -1 to ensure (0,0) is a valid move
    public int[][] formatMoveArray(int[][] legalMoves){
        for(int i = 0; i < legalMoves.length; i++){
            legalMoves[i][0] = -1; legalMoves[i][1] = -1;
        }

        return legalMoves;
    }
}