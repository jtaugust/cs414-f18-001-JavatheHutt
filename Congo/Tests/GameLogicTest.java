package Tests;
import package.BoardLogic; 

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import BoardLogic.*;
import BoardLogic.State;
import BoardLogic.Pawn;

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
	@Test
	public void testFlipBoard() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
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
		GameLogic gameLogic=new GameLogic();
	    String[][] returnedState= gameLogic.flipBoard(state);
	    for(int i=0; i<7; i++) {
	    	for(int j=0; j<7; j++) {
	    		assertTrue(newBoard[i][j].contentEquals(returnedState[i][j]));
	    	}
	    }
	}
	@Test
	public void testSimpleGiraffeMoves() {
		String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		State state=new State(board,"W","40NN","60WG");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleGiraffeMove(state);
		String[] expectedMoves= new String[15];
		expectedMoves[0]="40NN";
		expectedMoves[1]="42NN";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));
	}
	@Test
	public void testCaptureGiraffeMoves() {
		String[][] board = {
				{"00NN", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10NN", "11NN", "1200", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40BG", "41NN", "42BP", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51BP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61NN", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
		State state=new State(board,"W","40NN","60WG");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleGiraffeMove(state);
		String[] expectedMoves= new String[15];
		expectedMoves[0]="40BG";
		expectedMoves[1]="42BP";
		expectedMoves[2]="61NN";
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));
	}
    @Test
    public void testGiraffeMovesWhenAttackIsPossible() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10NN", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40BP", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","40NN","60WG");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleGiraffeMove(state);
		String[] expectedMoves= new String[15];
		expectedMoves[0]="40BP";
		expectedMoves[1]="42NN";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));
    }
    @Test
    public void testmonkeyCannotMove() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","41NN","61WM");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleMonkeyMove(state);
		String[] expectedMoves= new String[20];
//		expectedMoves[0]="41NN";
//		expectedMoves[1]="43NN";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));
    }
    @Test
    public void testmonkeySuccessiveJumps() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11WP", "12NN", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31BP", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51BP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","41NN","61WM");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleMonkeyMove(state);
		String[] expectedMoves= new String[20];
		expectedMoves[0]="41NN";
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));

    }
    @Test
    public void testBlackElephantMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22WE", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62NN", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"B","22WE","02BE");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleElephantMove(state);
		String[] expectedMoves= new String[8];
//		expectedMoves[0]="41NN";
		expectedMoves[0]="22WE";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		
		assertTrue(Arrays.deepEquals(movesReturned,expectedMoves));
    }
    @Test
    public void testWhiteElephantCaptureMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02NN", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12NN", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42BE", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52BP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","42BE","62WE");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleElephantMove(state);
		String[] expectedMoves= new String[8];
		expectedMoves[0]="52BP";
		expectedMoves[1]="42BE";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testLionAttackMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02NN", "03BL", "04NN", "05NN", "06BZ"},
				{"10BP", "11BP", "12NN", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52BP", "53BP", "54BC", "55WP", "56WP"},
				{"60WG", "61WM", "62BE", "63WL", "64BE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","53BP","63WL");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleLionMove(state);
		String[] expectedMoves= new String[10];
		expectedMoves[0]="52BP";
		expectedMoves[1]="53BP";
		expectedMoves[2]="54BC";
		expectedMoves[3]="62BE";
		expectedMoves[4]="64BE";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testLionFlyingAttackMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02NN", "03BL", "04NN", "05NN", "06BZ"},
				{"10BP", "11BP", "12NN", "13NN", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52BP", "53WL", "54BC", "55WP", "56WP"},
				{"60WG", "61WM", "62BE", "63NN", "64BE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","43NN","53WL");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleLionMove(state);
		String[] expectedMoves= new String[10];
		expectedMoves[0]="42NN";
		expectedMoves[1]="43NN";
		expectedMoves[2]="44NN";
		expectedMoves[3]="52BP";
		expectedMoves[4]="54BC";
		expectedMoves[5]="62BE";
		expectedMoves[6]="63NN";
		expectedMoves[7]="64BE";
		expectedMoves[8]="03BL";

//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testSimpleCrocodileMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10NN", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40BP", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55NN", "56BP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state = new State(board,"W","55NN","65WC");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleCrocodileMove(state);
		String[] expectedMoves= new String[10];
		expectedMoves[0]="55NN";
		expectedMoves[1]="56BP";
		expectedMoves[2]="45NN";
		expectedMoves[3]="35NN";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testSimpleZebraMoves() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","45NN","66WZ");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossibleZebraMove(state);
		String[] expectedMoves= new String[8];
		expectedMoves[0]="45NN";
		assertTrue(Arrays.equals(movesReturned,expectedMoves)); 	
    }
    @Test
    public void testPossiblePawnMoves() {
		Pawn pawn = new Pawn(5,0,'W','P');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{pawn, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
    	State state = new State(board,'W',"40NN","50WP");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossiblePawnMove(state);
		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 4; expectedMoves[0][1]= 0;
		expectedMoves[0][0]= 4; expectedMoves[0][1]= 0;
		for(int i=0; i<movesReturned.length;i++) {
			System.out.println(movesReturned[i]);
		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testSuperPawnMoves() {
    	String[][] board = {
				{"00BG", "01WS", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50NN", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","11BP","01WS");
		GameLogic gameLogic=new GameLogic();
		String[] movesReturned=gameLogic.allPossiblePawnMove(state);
		String[] expectedMoves= new String[20];
		expectedMoves[0]="00BG";
		expectedMoves[1]="02BE";
//		expectedMoves[2]="56BP";
//		for(int i=0; i<movesReturned.length;i++) {
//			System.out.println(movesReturned[i]);
//		}
		assertTrue(Arrays.equals(movesReturned,expectedMoves));
    }
    @Test
    public void testContainsMoveWhenTrue() {
    	String[] possibleMoves= {"11BP","10BE","21BC"};
    	String newPosition="10BE";
		GameLogic gameLogic=new GameLogic();
        assertTrue(gameLogic.containsMove(possibleMoves, newPosition));   	
    }
    @Test
    public void testContainsMoveWhenFalse() {
    	String[] possibleMoves= {"11BP","10BE","21BC"};
    	String newPosition="30BE";
		GameLogic gameLogic=new GameLogic();
        assertFalse(gameLogic.containsMove(possibleMoves, newPosition));   	
    }
    @Test
    public void testIsMovePossibleWhenTrue() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","45NN","66WZ");
		GameLogic gameLogic=new GameLogic();
        assertTrue(gameLogic.isMovePossible(state));
    }
    @Test
    public void testIsMovePossibleWhenFalse() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","46NN","66WZ");
		GameLogic gameLogic=new GameLogic();
        assertFalse(gameLogic.isMovePossible(state));
    }
    @Test
    public void testMovePiece() {
    	String[][] board = {
				{"00BG", "01BM", "02BE", "03BL", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","45NN","66WZ");
		GameLogic gameLogic=new GameLogic();
        gameLogic.movePiece(state, "45NN");
        String[][] newBoard= state.getBoard();
//        for(int i=0; i<7; i++) {
//	    	for(int j=0; j<7; j++) {
//	    		assertTrue(newBoard[i][j].contentEquals(board[i][j]));
//	    	}
//	    }
    }
    @Test
    public void testIsGameOverWhenOnlyLionIsLeft() {
    	String[][] board = {
				{"00NN", "01NN", "02NN", "03BL", "04NN", "05NN", "06NN"},
				{"10NN", "11NN", "12NN", "13NN", "14NN", "15NN", "16NN"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53NN", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","03BL","63WL");
		GameLogic gameLogic=new GameLogic();
        assertTrue(gameLogic.isGameOver(state));
    }
    @Test
    public void testIsGameOverWhenOnlyOneLionIsLeft() {
    	String[][] board = {
    			{"00BG", "01BM", "02BE", "03NN", "04BE", "05BC", "06BZ"},
				{"10BP", "11BP", "12BP", "13BP", "14BP", "15BP", "16BP"},
				{"20NN", "21NN", "22NN", "23NN", "24NN", "25NN", "26NN"},
				{"30NN", "31NN", "32NN", "33NN", "34NN", "35NN", "36NN"},
				{"40NN", "41NN", "42NN", "43NN", "44NN", "45NN", "46NN"},
				{"50WP", "51WP", "52WP", "53WP", "54WP", "55WP", "56WP"},
				{"60WG", "61WM", "62WE", "63WL", "64WE", "65WC", "66WZ"}
			};
    	State state=new State(board,"W","43NN","63WL");
		GameLogic gameLogic=new GameLogic();
        assertTrue(gameLogic.isGameOver(state));
    }
    }
    	
    
  
