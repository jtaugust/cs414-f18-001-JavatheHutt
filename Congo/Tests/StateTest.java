package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class StateTest {

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
			{zebraW, crocodileW, elephantW2, lionW, elephantW1, monkeyW, giraffeW},
			{pawnW7, pawnW6, pawnW5, pawnW4, pawnW3, pawnW2, pawnW1},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnB7, pawnB6, pawnB5, pawnB4, pawnB3, pawnB2, pawnB1},
			{zebraB, crocodileB, elephantB2, lionB, elephantB1, monkeyB, giraffeB}
		};

		State state = new State(board1,'W',new int[]{0,0},null);
		assertTrue(state.boardEquals(state.flipBoard(state), board2));


	}

	@Test
	public void testBoardEquals1() {
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
			{giraffeB, monkeyB, elephantB1, lionB, elephantB2, crocodileB, zebraB},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnW1, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
		};
		
		State state=new State(board1,'W',new int[]{0,0},null);
		assertTrue(state.boardEquals(board1,board2));


	}

	@Test
	public void testBoardEquals2() {
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
			{pawnW1, null, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
		};

		Piece[][] board2 = {
			{giraffeB, monkeyB, elephantB1, lionB, elephantB2, crocodileB, zebraB},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnW1, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
		};
		
		State state=new State(board1,'W',new int[]{0,0},null);
		assertTrue(!state.boardEquals(board1,board2));


	}

	@Test
	public void testGetBoard() {
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
			{giraffeB, monkeyB, elephantB1, lionB, elephantB2, crocodileB, zebraB},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnW1, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
		};
		
		State state=new State(board1,'W',new int[]{0,0},null);
		assertTrue(state.boardEquals(state.getBoard(), board2));


	}

	@Test
	public void testSetBoard() {
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
			{zebraW, crocodileW, elephantW2, lionW, elephantW1, monkeyW, giraffeW},
			{pawnW7, pawnW6, pawnW5, pawnW4, pawnW3, pawnW2, pawnW1},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawnB1, pawnB2, pawnB3, pawnB4, pawnB5, pawnB6, pawnB7},
			{zebraB, crocodileB, elephantB2, lionB, elephantB1, monkeyB, giraffeB}
		};

		State state=new State(board1,'W',new int[]{0,0},null);
		state.setBoard(board2);
		assertTrue(state.boardEquals(state.getBoard(), board2));


	}

	@Test
	public void testGetCurrentTurnColor() {
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{0,0},null);
		
    	char returnedTurnColor=state.getCurrentTurnColor();
    	char expectedCurrentTurnColor = 'W';
		assertEquals(expectedCurrentTurnColor, returnedTurnColor);
	}

	@Test
	public void testSetCurrentColor() {
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
    	State state=new State(board,'W',new int[]{0,0},null);
        state.setCurrentTurnColor('B');
        char expectedCurrentTurnColor = 'B';
        char returnedTurnColor=state.getCurrentTurnColor();
		assertEquals(expectedCurrentTurnColor, returnedTurnColor);
	}

	@Test
	public void testGetCurrentClick() {
		Pawn pawn1 = new Pawn(5,0,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawn1, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{1,2},pawn1);
		
    	int[] returnedClick = state.getCurrentClick();
    	int[] expectedClick = new int[] {1,2};
		assertArrayEquals(expectedClick, returnedClick);
	}

	@Test
	public void testSetCurrentClick() {
		Pawn pawn1 = new Pawn(5,0,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{pawn1, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{0,0},pawn1);
		
		int[] expectedClick = new int[] {1,2};
		state.setCurrentClick(expectedClick);
		assertEquals(state.getCurrentClick(), expectedClick);
	}

	@Test
	public void testToString() {
		Pawn pawn1 = new Pawn(5,0,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
      		{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
      		{pawn1, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{0,0},pawn1);
		
		String returnedString = state.toString();
		
		String expectedString = 
			"Board: " + "\n" + 
			"[ NN NN NN NN NN NN NN ]" + "\n" + 
			"[ NN NN NN NN NN NN NN ]" + "\n" +  
			"[ NN NN NN NN NN NN NN ]" + "\n" + 
			"[ NN NN NN NN NN NN NN ]" + "\n" + 
			"[ NN NN NN NN NN NN NN ]" + "\n" + 
			"[ 50WP NN NN NN NN NN NN ]" + "\n" + 
			"[ NN NN NN NN NN NN NN ]" + "\n" + "\n" +  

			" currentTurnColor: W currentClick: 0 0 pieceSelected: 50WP" + "\n";
		
		assertEquals(expectedString, returnedString);
	}
	
	@Test
	public void testMovePieceToSuperPawn() {
		Pawn pawn = new Pawn(1,2,'W','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, pawn, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{0,0},pawn);
		
    	state.movePiece("12", "02",0);
		assertEquals(state.getBoard()[0][2].getType(),'S');
	}
	
	@Test
	public void testMonkeyCapture() {
		Monkey monkey = new Monkey(5,2,'W','M');
		Pawn blackPawn=new Pawn(4,2,'B','P');
		Piece[][] board = {
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null},
			{null, null, blackPawn, null, null, null, null},
			{null, null, monkey, null, null, null, null},
			{null, null, null, null, null, null, null}
		};
		State state=new State(board,'W',new int[]{0,0},monkey);
		
		state.movePiece("52", "32",0);
		assertEquals(state.getBoard()[2][2],null);
	}
	

}
