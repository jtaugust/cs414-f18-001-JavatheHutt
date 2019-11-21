package Tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

import BoardLogic.*;

public class GameLogicTest {
	
	GameLogic logicTest;
// 	@Test
// 	public void testIsIndexBoundedForUnBoundedValues() {
// 		GameLogic gameLogic=new GameLogic();
// 		int i=7;
// 		int j=-1;
// 		assertFalse(gameLogic.isIndexBounded(i,j));
// 	}
// 	@Test
// 	public void testIsOnCorrectIndex() {
// 		int x=2;
// 		int y= 1;
// 		boolean row=true;
// 		boolean column=false;
// 		GameLogic gameLogic=new GameLogic();
//         assertTrue(gameLogic.isOnCorrectIndex(x, y, row, column));
// 	}
//     @Test
//     public void testContainsMoveWhenTrue() {
//     	String[] possibleMoves= {"11BP","10BE","21BC"};
//     	String newPosition="10BE";
// 		GameLogic gameLogic=new GameLogic();
//         assertTrue(gameLogic.containsMove(possibleMoves, newPosition));   	
//     }
//     @Test
//     public void testContainsMoveWhenFalse() {
//     	String[] possibleMoves= {"11BP","10BE","21BC"};
//     	String newPosition="30BE";
// 		GameLogic gameLogic=new GameLogic();
//         assertFalse(gameLogic.containsMove(possibleMoves, newPosition));   	
//     }


	@Test
	public void testFlipBoard() {
	
		// White Pieces 
		Pawn pawnW1 = new Pawn(5,0,'W','P');
		Pawn pawnW2 = new Pawn(5,1,'W','P');
		Pawn pawnW3 = new Pawn(5,2,'W','P');
		Pawn pawnW4 = new Pawn(5,3,'W','P');
		Pawn pawnW5 = new Pawn(5,4,'W','P');
		Pawn pawnW6 = new Pawn(5,5,'W','P');
		Pawn pawnW7 = new Pawn(5,6,'W','P');

		Giraffe giraffeW = new Giraffe(6, 0, 'W', 'G');
		Monkey monkeyW = new Monkey(6,1,'W','M');
		Elephant elephantW1 = new Elephant(6, 2, 'W', 'E');
		Lion lionW = new Lion(6, 3, 'W', 'L');
		Elephant elephantW2 = new Elephant(6, 4, 'W', 'E');
		Crocodile crocodileW = new Crocodile(6, 5, 'W', 'C');
		Zebra zebraW = new Zebra(6, 6, 'W', 'Z');

		// Black Pieces 
		Pawn pawnB1 = new Pawn(1,0,'B','P');
		Pawn pawnB2 = new Pawn(1,1,'B','P');
		Pawn pawnB3 = new Pawn(1,2,'B','P');
		Pawn pawnB4 = new Pawn(1,3,'B','P');
		Pawn pawnB5 = new Pawn(1,4,'B','P');
		Pawn pawnB6 = new Pawn(1,5,'B','P');
		Pawn pawnB7 = new Pawn(1,6,'B','P');

		Giraffe giraffeB = new Giraffe(6, 0, 'B', 'G');
		Monkey monkeyB = new Monkey(6,1,'B','M');
		Elephant elephantB1 = new Elephant(6, 2, 'B', 'E');
		Lion lionB = new Lion(6, 3, 'B', 'L');
		Elephant elephantB2 = new Elephant(6, 4, 'B', 'E');
		Crocodile crocodileB = new Crocodile(6, 5, 'B', 'C');
		Zebra zebraB = new Zebra(6, 6, 'B', 'Z');
		

		Piece[][] board1 = {
			{giraffeB, monkeyB, elephantB1, lionB, elephantB2, crocodileB, zebraB},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnW1, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
		};

		Piece[][] board2 = {
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW},
			{pawnW1, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{giraffeB, monkeyB, elephantB1, lionB, elephantB2, crocodileB, zebraB},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7}
		};

		
		assertTrue(logicTest.boardEquals(board1,board2));
	}

	@Test
    public void testDisplayPossibleMoves() {
		Pawn pawn = new Pawn(5,1,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state = new State(board,'W',new int[] {4,1},pawn);
		int[][] expectedMoves = new int[20][2];
		expectedMoves[0][0] = 4; expectedMoves[0][1] = 0; 
		expectedMoves[1][0] = 4; expectedMoves[1][1] = 1; 
		expectedMoves[2][0] = 4; expectedMoves[2][1] = 2; 

		assertArrayEquals(expectedMoves, pawn.legalMoves(state));
	}

	//
	// Test isMovePossible method 
	//
    @Test
    public void testIsMovePossibleWhenTrue() {
		Pawn pawn = new Pawn(5,1,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state = new State(board,'W',new int[] {4,1},pawn);
        assertTrue(logicTest.isMovePossible(state));
    }
    @Test
    public void testIsMovePossibleWhenFalse() {
		Pawn pawn = new Pawn(5,1,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state = new State(board,'W',new int[] {6,1},pawn);
        assertTrue(logicTest.isMovePossible(state));
	}
	
	//
	// Test movePiece method 
	//
    @Test
    public void testMovePiece() {
    	Pawn pawn1 = new Pawn(5,1,'W','P');
		Piece[][] board1 = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn1, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state = new State(board1,'W',new int[] {4,1},pawn1);
        logicTest.movePiece(state, pawn1);

		Pawn pawn2 = new Pawn(4,1,'W','P');
		Piece[][] board2 = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn2, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};

		assertTrue(logicTest.boardEquals(board1,board2));

	}
	
	//
	// Test isGameOver method 
	//
    @Test
    public void testIsGameOverWhenTrue1() {
		Lion lion1 = new Lion(0,2,'B','L');
		Pawn pawn1 = new Pawn(1,3,'B','P');
		Pawn pawn2 = new Pawn(5,1,'W','P');
		Piece[][] board1 = {
			{null, null, lion1, null, null, null, null},
			{null, null, null, pawn1, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn2, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state = new State(board1,'W',new int[] {4,1},pawn1);
		System.out.println("IS OVER: "+ state.toString());
        assertTrue(logicTest.isGameOver(state));
	}

	// @Test
    // public void testIsGameOverWhenTrue2() {
	// 	Lion lion1 = new Lion(0,2,'B','L');
	// 	Lion lion2 = new Lion(6,2,'W','L');
	// 	Pawn pawn2 = new Pawn(5,1,'W','P');
	// 	Piece[][] board1 = {
	// 		{null, null, lion1, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn2, null, null, null, null, null},
	// 		{null, null, lion2, null, null, null, null}
	// 	};
	// 	State state = new State(board1,'W',new int[] {4,1},pawn2);
    //     assertTrue(logicTest.isGameOver(state));
	// }

	// @Test
    // public void testIsGameOverWhenFalse1() {
	// 	Lion lion1 = new Lion(0,2,'B','L');
	// 	Lion lion2 = new Lion(6,2,'W','L');
	// 	Pawn pawn1 = new Pawn(1,3,'B','P');
	// 	Pawn pawn2 = new Pawn(5,1,'W','P');
	// 	Piece[][] board1 = {
	// 		{null, null, lion1, null, null, null, null},
	// 		{null, null, null, pawn1, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn2, null, null, null, null, null},
	// 		{null, null, lion2, null, null, null, null}
	// 	};
	// 	State state = new State(board1,'W',new int[] {4,1},pawn1);
    //     assertFalse(logicTest.isGameOver(state));
	// }

	// @Test
    // public void testIsGameOverWhenDraw() {
	// 	Lion lion1 = new Lion(0,2,'B','L');
	// 	Lion lion2 = new Lion(6,2,'W','L');

	// 	Piece[][] board1 = {
	// 		{null, null, lion1, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, lion2, null, null, null, null}
	// 	};
	// 	State state = new State(board1,'W',new int[] {4,1},lion1);
    //     assertTrue(logicTest.isGameOver(state));
	// }
	
}
    	
    
  
