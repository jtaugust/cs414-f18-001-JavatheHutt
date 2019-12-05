package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class ElephantTest {
    
    @Test
	public void testElephantCapture() {
		
		Elephant elephant = new Elephant(3,3,'W','E');

		Pawn p1 = new Pawn(1,3,'B','P');
		Pawn p2 = new Pawn(2,3,'B','P');
		Pawn p3 = new Pawn(3,1,'B','P');
		Pawn p4 = new Pawn(3,2,'B','P');
		Pawn p5 = new Pawn(3,4,'B','P');
		Pawn p6 = new Pawn(3,5,'B','P');
		Pawn p7 = new Pawn(4,3,'B','P');
		Pawn p8 = new Pawn(5,3,'B','P');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, p1, null, null, null},
				{null, null, null, p2, null, null, null},
				{null, p3, p4, elephant, p5, p6, null},
				{null, null, null, p7, null, null, null},
				{null, null, null, p8, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},elephant);

		int[][] movesReturned = elephant.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		elephant.formatMoveArray(expectedMoves);

        expectedMoves[0][0]= 2; expectedMoves[0][1]= 3;
		expectedMoves[1][0]= 1; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 3; expectedMoves[2][1]= 2;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 3; expectedMoves[5][1]= 5;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 3;
		expectedMoves[7][0]= 5; expectedMoves[7][1]= 3;

		// System.out.println("Moves Expected");
        // for(int k = 0; k < expectedMoves.length; k++) {
        //     System.out.println(expectedMoves[k][0] + " " + expectedMoves[k][1]);
		// }
		
		// System.out.println("Moves Returned");
        // for(int k = 0; k < movesReturned.length; k++) {
        //     System.out.println(movesReturned[k][0] + " " + movesReturned[k][1]);
		// }
		
		assertArrayEquals(movesReturned, expectedMoves);
	}

    @Test
	public void testElephantMove() {
		
		Elephant elephant = new Elephant(3,3,'W','E');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, elephant, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},elephant);

		int[][] movesReturned = elephant.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		elephant.formatMoveArray(expectedMoves);

        expectedMoves[0][0]= 2; expectedMoves[0][1]= 3;
		expectedMoves[1][0]= 1; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 3; expectedMoves[2][1]= 2;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 3; expectedMoves[5][1]= 5;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 3;
		expectedMoves[7][0]= 5; expectedMoves[7][1]= 3;

		// System.out.println("Moves Expected");
        // for(int k = 0; k < expectedMoves.length; k++) {
        //     System.out.println(expectedMoves[k][0] + " " + expectedMoves[k][1]);
		// }
		
		// System.out.println("Moves Returned");
        // for(int k = 0; k < movesReturned.length; k++) {
        //     System.out.println(movesReturned[k][0] + " " + movesReturned[k][1]);
		// }
		
		assertArrayEquals(movesReturned, expectedMoves);
	}
}