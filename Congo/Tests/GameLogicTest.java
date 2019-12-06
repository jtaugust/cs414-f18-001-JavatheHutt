package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class GameLogicTest {
	
	GameLogic logic = new GameLogic();

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
 

	

	// @Test
    // public void testDisplayPossibleMoves() {
	// 	Pawn pawn = new Pawn(5,1,'W','P');
	// 	Piece[][] board = {
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null}
	// 	};
	// 	State state = new State(board,'W',new int[] {4,1},pawn);
	// 	int[][] expectedMoves = new int[20][2];
	// 	expectedMoves[0][0] = 4; expectedMoves[0][1] = 0; 
	// 	expectedMoves[1][0] = 4; expectedMoves[1][1] = 1; 
	// 	expectedMoves[2][0] = 4; expectedMoves[2][1] = 2; 

	// 	assertArrayEquals(expectedMoves, pawn.legalMoves(state));
	// }

	//
	// Test isMovePossible method 
	//
    // @Test
    // public void testIsMovePossibleWhenTrue() {
	// 	Pawn pawn = new Pawn(5,1,'W','P');
	// 	Piece[][] board = {
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null}
	// 	};
	// 	State state = new State(board,'W',new int[] {4,1},pawn);
    //     assertTrue(logicTest.isMovePossible(state));
    // }
    // @Test
    // public void testIsMovePossibleWhenFalse() {
	// 	Pawn pawn = new Pawn(5,1,'W','P');
	// 	Piece[][] board = {
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null}
	// 	};
	// 	State state = new State(board,'W',new int[] {6,1},pawn);
    //     assertTrue(logicTest.isMovePossible(state));
	// }
	
	//
	// Test movePiece method 
	//
    // @Test
    // public void testMovePiece() {
    // 	Pawn pawn1 = new Pawn(5,1,'W','P');
	// 	Piece[][] board1 = {
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn1, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null}
	// 	};
	// 	State state = new State(board1,'W',new int[] {4,1},pawn1);
    //     logicTest.movePiece(state, pawn1);

	// 	Pawn pawn2 = new Pawn(4,1,'W','P');
	// 	Piece[][] board2 = {
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, pawn2, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null},
	// 		{null, null, null, null, null, null, null}
	// 	};

	// 	assertTrue(logicTest.boardEquals(board1,board2));

	// }
	
	//
	// Test isGameOver method 
	//

    @Test
    public void testIsGameOverWhenBlackWins1() {
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
		State state = new State(board1,'B',new int[] {4,1},pawn1);

		char expectedVar = 'B';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	@Test
    public void testIsGameOverWhenBlackWins2() {
		Lion lion1 = new Lion(0,2,'B','L');
		Pawn pawn1 = new Pawn(1,3,'B','P');
		Lion lion2 = new Lion(6,2,'W','L');

		Piece[][] board1 = {
			{null, null, lion1, null, null, null, null},
			{null, null, null, pawn1, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, lion2, null, null, null, null}
		};
		State state = new State(board1,'B',new int[] {4,1},pawn1);

		char expectedVar = 'B';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	@Test
    public void testIsGameOverWhenWhiteWins1() {
		Pawn pawn1 = new Pawn(1,3,'B','P');
		Lion lion2 = new Lion(6,2,'W','L');
		Pawn pawn2 = new Pawn(5,1,'W','P');

		Piece[][] board1 = {
			{null, null, null, null, null, null, null},
			{null, null, null, pawn1, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn2, null, null, null, null, null},
			{null, null, lion2, null, null, null, null}
		};
		State state = new State(board1,'B',new int[] {4,1},pawn1);

		char expectedVar = 'W';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	@Test
    public void testIsGameOverWhenWhiteWins2() {
		Lion lion1 = new Lion(0,2,'B','L');
		Pawn pawn1 = new Pawn(1,3,'B','P');
		Lion lion2 = new Lion(6,2,'W','L');
		Pawn pawn2 = new Pawn(5,1,'W','P');

		Piece[][] board1 = {
			{null, null, lion1, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn2, null, null, null, null, null},
			{null, null, lion2, null, null, null, null}
		};
		State state = new State(board1,'B',new int[] {4,1},pawn1);

		char expectedVar = 'W';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	@Test
    public void testIsGameOverWhenDraw() {
		Lion lion1 = new Lion(0,2,'B','L');
		Lion lion2 = new Lion(6,2,'W','L');

		Piece[][] board1 = {
			{null, null, lion1, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, lion2, null, null, null, null}
		};
		State state = new State(board1,'B',new int[] {4,1},lion1);

		char expectedVar = 'D';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	@Test
    public void testIsGameOverWhenNull() {
		Lion lion1 = new Lion(0,2,'B','L');
		Pawn pawn1 = new Pawn(1,1,'B','P');
		Lion lion2 = new Lion(6,2,'W','L');
		Pawn pawn2 = new Pawn(5,1,'W','P');

		Piece[][] board1 = {
			{null, null, lion1, null, null, null, null},
			{null, pawn1, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, pawn2, null, null, null, null, null},
			{null, null, lion2, null, null, null, null}
		};
		State state = new State(board1,'B',new int[] {4,1},lion1);

		char expectedVar = 'N';
		char resultVar = logic.isGameOver(state);
		// System.out.println("Expected Var: " + expectedVar + " Result Var: " + resultVar);

        assertEquals(expectedVar, resultVar);
	}

	
}
    	
    
  
