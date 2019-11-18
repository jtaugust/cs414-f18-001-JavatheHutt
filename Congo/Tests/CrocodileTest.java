package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class CrocodileTest {
    
    @Test
    public void testSimpleCrocodileMoves() {
		System.out.println("In Crocodile");
		Crocodile crocodile = new Crocodile(3,1,'W','C');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, crocodile, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',"40NN","31WC");

		int[][] movesReturned = crocodile.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 3; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 3; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 3; expectedMoves[2][1]= 3;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 4;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 5;
		expectedMoves[5][0]= 3; expectedMoves[5][1]= 6;
		expectedMoves[6][0]= 2; expectedMoves[6][1]= 0;
		expectedMoves[7][0]= 2; expectedMoves[7][1]= 1;
		expectedMoves[8][0]= 2; expectedMoves[8][1]= 2;
		expectedMoves[9][0]= 4; expectedMoves[9][1]= 0;
		expectedMoves[10][0]= 4; expectedMoves[10][1]= 1;
		expectedMoves[11][0]= 4; expectedMoves[11][1]= 2;

		System.out.println("Moves Expected");
        for(int k = 0; k < expectedMoves.length; k++) {
            System.out.println(expectedMoves[k][0] + " " + expectedMoves[k][1]);
		}
		
		System.out.println("Moves Returned");
        for(int k = 0; k < movesReturned.length; k++) {
            System.out.println(movesReturned[k][0] + " " + movesReturned[k][1]);
		}
		
		assertArrayEquals(movesReturned, expectedMoves);
    }
    
}
