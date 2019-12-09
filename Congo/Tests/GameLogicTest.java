package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class GameLogicTest {
	
	GameLogic logic = new GameLogic();
 

	


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

        assertEquals(expectedVar, resultVar);
	}

	
}
    	
    
  
