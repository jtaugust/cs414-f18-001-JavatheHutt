package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.GameLogic;
import BoardLogic.State;

public class StateTest {

	@Test
	public void testGetBoard() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","03BL","66WZ");
		String[][] returnedBoard=state.getBoard();
		 for(int i=0; i<7; i++) {
		    	for(int j=0; j<7; j++) {
		    		assertTrue(returnedBoard[i][j].contentEquals(board[i][j]));
		    	}
		    }
	}
	@Test
	public void testGetCurrentTurnColor() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"B","03BL","66WZ");
    	String returnedTurnColor=state.getCurrentTurnColor();
    	String expectedCurrentTurnColor="B";
		assertTrue(returnedTurnColor.contentEquals(expectedCurrentTurnColor));
	}
	@Test
	public void testGetCurrentTurnColorWhenBlack() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","03BL","66WZ");
    	String returnedTurnColor=state.getCurrentTurnColor();
    	String expectedCurrentTurnColor="B";
		assertFalse(returnedTurnColor.contentEquals(expectedCurrentTurnColor));
	}
	@Test
	public void testGetCurrentClick() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","03BL","66WZ");
    	String returnedCurrentClick=state.getCurrentClick();
    	String expectedCurrentClick="03BL";
		assertTrue(returnedCurrentClick.contentEquals(expectedCurrentClick));
	}
	@Test
	public void testGetCurrentPieceSelected() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","03BL","66WZ");
    	String returnedCurrentClick=state.getPieceSelected();
    	String expectedPieceSelected="66WZ";
		assertTrue(returnedCurrentClick.contentEquals(expectedPieceSelected));
	}
	@Test
	public void testsetBoard() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		String[][] newBoard= {
				{"00WZ", "01WC", "02WE", "03WL", "04WE","05WM", "06WG"},
				{"10WP", "11WP", "12WP", "13WP", "14WP", "15WP", "16WP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"}, 
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50BP", "51BP", "52BP", "53BP", "54BP", "55BP", "56BP"}, 
				{"60BZ", "61BC", "62BE", "63BL", "64BE", "65BM", "66BG"}
		};
		State state=new State(board,"W","40NN","50WP");
		state.setBoard(newBoard);
		String[][] newReturnedBoard=state.getBoard();
	    for(int i=0; i<7; i++) {
	    	for(int j=0; j<7; j++) {
	    		assertTrue(newReturnedBoard[i][j].contentEquals(newBoard[i][j]));
	    	}
	    }
	}
	@Test
	public void testSetCurrentColor() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		State state=new State(board,"W","40NN","50WP");
        state.setCurrentTurnColor("B");
        String expectedCurrentTurnColor="B";
        String returnedTurnColor=state.getCurrentTurnColor();
		assertTrue(returnedTurnColor.contentEquals(expectedCurrentTurnColor));
	}
	@Test
	public void testSetCurrentClick() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		State state=new State(board,"W","40NN","50WP");
        state.setCurrentClick("43NN");
        String expectedCurrentClick="43NN";
        String returnedCurrentClick=state.getCurrentClick();
		assertTrue(returnedCurrentClick.contentEquals(expectedCurrentClick));
	}
	@Test
	public void testSetPieceSelected() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		State state=new State(board,"W","40NN","50WP");
        state.setPieceSelected("64WE");
        String expectedPieceSelected="64WE";
        String returnedPieceSelected=state.getPieceSelected();
		assertTrue(returnedPieceSelected.contentEquals(expectedPieceSelected));
	}

}
