package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.GameLogic;
import BoardLogic.State;

public class GameLogicTest {

	@Test
	public void testIsIndexBoundedForUnBoundedValues() {
		GameLogic gameLogic=new GameLogic();
		int i=7;
		int j=-1;
		assertFalse(gameLogic.isIndexBounded(i,j));
	}
	@Test
	public void testIsOnCorrectIndex() {
		int x=2;
		int y= 1;
		boolean row=true;
		boolean column=false;
		GameLogic gameLogic=new GameLogic();
        assertTrue(gameLogic.isOnCorrectIndex(x, y, row, column));
	}
//	@Test
//	public void testFlipBoard() {
//		String[][] board = {
//				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
//				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
//				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
//				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
//				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
//				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
//				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
//			};
//		String[][] newBoard= {
//				{"66WZ", "65WC", "64WE", "63WL", "62WE","61WM", "60WG"},
//				{"56WP", "55WP", "54WP", "53WP", "52WP", "51WP", "50WP"},
//				{"46NN", "45NN", "44NN", "43NN", "42NN", "41NN", "40NN"}, 
//				{"36NN", "35NN", "34NN", "33NN", "32NN", "31NN", "30NN"},
//				{"26NN", "25NN", "24NN", "23NN", "22NN", "21NN", "20NN"},
//				{"16BP", "15BP", "14BP", "13BP", "12BP", "11BP", "10BP"}, 
//				{"06BZ", "05BC", "04BE", "03BL", "02BE", "01BM", "00BG"}
//		};
//		State state=new State(board,"W","40NN","50WP");
//		GameLogic gameLogic=new GameLogic();
//		System.out.println(gameLogic.flipBoard(state).toString());
//		assertSame(newBoard,gameLogic.flipBoard(state));
//		
//
//	}
    
}
