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
}