package BoardLogic;

public class State {

    //Board Syntax: Xposition Yposition Color Animal 
    // W: White B: Black 
    // L: Lion 
    // Z: Zebra 
    // E: Elephant 
    // G: Giraffe 
    // M: Monkey 
    // C: Crocidile 
    // P: Pawn 
    // S: Super pawn 
    // N: Null (Empty space)
    // Exs: [14WL] [16NN] [45BC]

    public Piece[][] board = new Piece[7][7];
    public char currentTurnColor;
    public int[] currentClick;
    public Piece pieceSelected;

    // Initializes the board
    public State() {
    	this.board[0][0]=new Giraffe(0,0,'B','G');
    	this.board[0][1]=new Monkey(0,1,'B','M');
    	this.board[0][2]=new Elephant(0,2,'B','E');
    	this.board[0][3]=new Lion(0,3,'B','L');
    	this.board[0][4]=new Elephant(0,4,'B','E'); 
    	this.board[0][5]=new Crocodile(0,5,'B','C');
    	this.board[0][6]=new Zebra(0,6,'B','Z');
    	
        for(int i=0; i<7; i++) {
        		this.board[1][i]=new Pawn(1,i,'B','P');
        }
        
        this.board[6][0]=new Giraffe(6,0,'W','G');
    	this.board[6][1]=new Monkey(6,1,'W','M');
    	this.board[6][2]=new Elephant(6,2,'W','E');
    	this.board[6][3]=new Lion(6,3,'W','L');
    	this.board[6][4]=new Elephant(6,4,'W','E'); 
    	this.board[6][5]=new Crocodile(6,5,'W','C');
    	this.board[6][6]=new Zebra(6,6,'W','Z');
    	
        for(int i=0; i<7; i++) {
        		this.board[5][i]=new Pawn(5,i,'W','P');
        
            }
        for(int i=2; i<5; i++) {
        	for(int j=0; j<7;j++) {
        		this.board[i][j]=null;
        	}
        }
    }
    // Current Click: Current postiion of last click 
    // PieceSelected is either null or in format above for which piece is selected to move
    // State constructor, values pulled from database
    public State(Piece[][] board, char currentTurnColor, int[] currentClick, Piece pieceSelected){
        // TODO: Get values from database
        this.board = board;
        this.currentTurnColor = currentTurnColor;
        this.currentClick = currentClick;
        this.pieceSelected = pieceSelected;


    }   

    //
    // Getters
    //
    public Piece[][] getBoard() {
        return this.board;
    }

    public char getCurrentTurnColor(){
        return this.currentTurnColor;
    }

    public int[] getCurrentClick(){
        return this.currentClick;
    }

    public Piece getPieceSelected(){
        return this.pieceSelected;
    }

    //
    // Setters
    //
    public void setBoard(Piece[][] newBoard) {
        this.board = newBoard;
    }

    public void setCurrentTurnColor(char newTurnColor){
        currentTurnColor = newTurnColor;
    }

    public void setCurrentClick(int[] newClick){
        currentClick = newClick;
    }

    public void setPieceSelected(Piece newPieceSelected){
        pieceSelected = newPieceSelected;
    }

    // Print in readable format
    public String toString(){
		String result = "Board: \n";
        for (int i = 0; i < 7; i++ ){
			result+= "[ ";
			for (int j = 0; j < 7; j++){
				if (board[i][j]!=null){
                result += board[i][j].toString() + " ";
				}
				else {
	                result += "NN ";
				}
            }
            result += "]\n";
        }

        result += "\n currentTurnColor: " + currentTurnColor + " currentClick: " +  currentClick + " pieceSelected: " + pieceSelected.toString() + "\n";
        return result;
    }
    
    public void movePiece(String fromPos, String toPos){
//        System.out.println("From Pos and ToPid"+fromPos+","+toPos);
        Piece pieceSelected=board[Character.getNumericValue(fromPos.charAt(0))][Character.getNumericValue(fromPos.charAt(1))];
        pieceSelected.setRow(Character.getNumericValue(toPos.charAt(0)));
        pieceSelected.setColumn(Character.getNumericValue(Character.getNumericValue(toPos.charAt(1))));
		this.board[Character.getNumericValue(fromPos.charAt(0))][Character.getNumericValue(fromPos.charAt(1))]=null;
		
		this.board[Character.getNumericValue(toPos.charAt(0))][Character.getNumericValue(toPos.charAt(1))]=pieceSelected;
		if(pieceSelected.getType()=='M') {
			monkeyMoveHandler(fromPos,toPos);
		}
	}

    public Piece[][] flipBoard(State state){
		Piece[][] board = state.getBoard();
		Piece[][] newBoard = new Piece[7][7];

		// Go through backwards and swap pieces by taking index of array copy and piece color and type from real board
		int countI = 0;
		int countJ = 0;
		for (int i = 6; i >= 0; i--) { 
            for (int j = 6; j >= 0; j--) {

                Piece newPiece = board[i][j];
                if(newPiece != null) {
                    newPiece.setRow(countI);
                    newPiece.setColumn(countJ);
                    newBoard[countI][countJ] = newPiece;
                }
                countJ++;
            }
            countI++;
            countJ = 0;

        } 
		state.setBoard(newBoard);
		return newBoard;
	}
    
    // Check if two boards are equal
    public boolean boardEquals(Piece[][] board1, Piece[][] board2){
        // Return False if not equal
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                if(board1[i][j] == null){
                    // If both spots not null
                    if(board2[i][j] != null){
                        return false;
                    }
                } else {
                    // Check Color
                    if(board1[i][j].getColor() != board2[i][j].getColor()){
                        return false;
                    }

                    // Check piece type
                    if(board1[i][j].getType() != board2[i][j].getType()){
                        return false;
                    }
                }
            }
        }
        
        // Boards are equal
        return true;
    }
	
    // removes the piece jumped over
    public void monkeyMoveHandler(String fromPos, String toPos) {
		int fromRow=Character.getNumericValue(fromPos.charAt(0));
		int fromCol=Character.getNumericValue(fromPos.charAt(1));
		int toRow=Character.getNumericValue(toPos.charAt(0));
		int toCol=Character.getNumericValue(toPos.charAt(1));
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
		if(board[jumpedRow][jumpedCol]!=null) {
		System.out.print("JumpedPiece:"+board[jumpedRow][jumpedCol].getType());
		}
		board[jumpedRow][jumpedCol]=null;

	}
}