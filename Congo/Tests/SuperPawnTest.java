package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class SuperPawnTest {
    
    @Test
    public void testSuperPawnMoves() {
    	// System.out.println("In Super");
		SuperPawn superPawn = new SuperPawn(2,1,'W','S');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, superPawn, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},superPawn);
		
		int[][] movesReturned = superPawn.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 1; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 1; expectedMoves[1][1]= 1;
		expectedMoves[2][0]= 1; expectedMoves[2][1]= 2;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 4; expectedMoves[4][1]= 1;

		expectedMoves[5][0]= 2; expectedMoves[5][1]= 0;
		expectedMoves[6][0]= 2; expectedMoves[6][1]= 2;
		expectedMoves[7][0]= 3; expectedMoves[7][1]= 0;
		expectedMoves[8][0]= 3; expectedMoves[8][1]= 2;
		expectedMoves[9][0]= 4; expectedMoves[9][1]= 3;

		// System.out.println("Moves Returned");
        // for(int k = 0; k < movesReturned.length; k++) {
        //     System.out.println(movesReturned[k][0] + " " + movesReturned[k][1]);
		// }
		
		// System.out.println("Moves Expected");
        // for(int k = 0; k < expectedMoves.length; k++) {
        //     System.out.println(expectedMoves[k][0] + " " + expectedMoves[k][1]);
        // }

		assertArrayEquals(movesReturned, expectedMoves);
	}

}
