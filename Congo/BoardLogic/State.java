package BoardLogic;
import java.util.Currency;

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

    public String[][] board;
    public String currentTurnColor;
    public String currentClick;
    public String pieceSelected;


    // Current Click: Current postiion of last click 
    // PieceSelected is either null or in format above for which piece is selected to move
    // State constructor, values pulled from database
    public State(String[][] board, String currentTurnColor, String currentClick, String pieceSelected){
        // TODO: Get values from database
        this.board = board;
        this.currentTurnColor = currentTurnColor;
        this.currentClick = currentClick;
        this.pieceSelected = pieceSelected;


    } 

    // Empty construcor for testing purposes 
    public State(){
       
    }

    // Update the state object and update in database
    public void updateState(State newState){
        //state = newState;
        
        //TODO: Update in database
    }

    //
    // Getters
    //
    public String[][] getBoard() {
        return board;
    }

    public String getCurrentTurnColor(){
        return currentTurnColor;
    }

    public String getCurrentClick(){
        return currentClick;
    }

    public String getPieceSelected(){
        return pieceSelected;
    }

    //
    // Setters
    //
    public void setBoard(String[][] newBoard) {
        board = newBoard;
    }

    public void setCurrentTurnColor(String newTurnColor){
        currentTurnColor = newTurnColor;
    }

    public void setCurrentClick(String newClick){
        currentClick = newClick;
    }

    public void setPieceSelected(String newPieceSelected){
        pieceSelected = newPieceSelected;
    }

    // Print in readable format
    public String toString(){
		String result = "Board: \n";
        for (int i = 0; i < 7; i++ ){
			result+= "[ ";
			for (int j = 0; j < 7; j++){
                result += board[i][j] + " ";
            }
            result += "]\n";
        }

        result += "\n currentTurnColor: " + currentTurnColor + " currentClick: " +  currentClick + " pieceSelected: " + pieceSelected + "\n";
        return result;
    }
}