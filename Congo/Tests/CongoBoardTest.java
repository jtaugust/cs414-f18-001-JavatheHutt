package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

import BoardLogic.*;


public class CongoBoardTest {

    CongoBoard board;

	@Before
	public void testObjectCreation() {
        String user1 = "junit1";
        String user2 = "junit2";
        int gameID = 22;
        String currentUser = "admin";
        try {
            board = new CongoBoard(user1, user2, gameID, currentUser);
        } catch (Exception e) {
           assertTrue(false);
        }
      
        assertTrue(true);
       
    }

    @Test
    public void testFillBoard(){
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
        
        try{
            board.fillBoard(board1);
        }catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }

        assertTrue(true);

    }

    @Test 
    public void testBuildBoard(){
        try{
            board.buildBoard();
        }catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public void testStateDatabase(){
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
			{pawnW1, null, null, null, null, null, null},
			{null, pawnW2, pawnW3, pawnW4, pawnW5, pawnW6, pawnW7},
			{giraffeW, monkeyW, elephantW1, lionW, elephantW2, crocodileW, zebraW}
        };

        State state = new State(board1, 'W', new int[] {0,0}, pawnW1);
        board.stateToDatabase(board1);

		assertTrue(state.boardEquals(board.stateFromDatabase(22), board1));
    }

    @Test
    public void testIsIndex(){
        JLabel label = new JLabel();
        assertTrue(!board.isIndex(label));
    }

    @Test
    public void testRevertColors(){
        try{
            board.revertColors();
            
        }catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }

        assertTrue(true);
    }

    @Test
    public void testIsPieceMoveOnBoard(){
        Point point = new Point(2,2);
        assertTrue(!board.isPieceMovedOnBoard(point));
    }

}