package Tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

import BoardLogic.*;

public class CrocodileTest {
    
    @Test
    public void testCrocodileRiverCapture1() {
		// System.out.println("In Crocodile");
		Crocodile crocodile = new Crocodile(3,1,'W','C');
		
		Pawn pawn1 = new Pawn(2, 0, 'B', 'P');
		Pawn pawn2 = new Pawn(2, 1, 'B', 'P');
		Pawn pawn3 = new Pawn(2, 2, 'B', 'P');
		Pawn pawn4 = new Pawn(3, 0, 'B', 'P');
		Pawn pawn5 = new Pawn(3, 2, 'B', 'P');
		Pawn pawn6 = new Pawn(4, 0, 'B', 'P');
		Pawn pawn7 = new Pawn(4, 1, 'B', 'P');
		Pawn pawn8 = new Pawn(4, 2, 'B', 'P');
		

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{pawn1, pawn2, pawn3, null, null, null, null},
				{pawn4, crocodile, pawn5, null, null, null, null},
				{pawn6, pawn7, pawn8, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},crocodile);

		int[][] movesReturned = crocodile.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves = crocodile.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 3; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 3; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 2; expectedMoves[2][1]= 0;
		expectedMoves[3][0]= 2; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 2; expectedMoves[4][1]= 2;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 0;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 1;
		expectedMoves[7][0]= 4; expectedMoves[7][1]= 2;

		// Sort arrays before comparison
		// Arrays.sort(expectedMoves, (a, b) -> Double.compare(a[0], b[0]));
		// Arrays.sort(movesReturned, (a, b) -> Double.compare(a[0], b[0]));
		
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
    public void testCrocodileRiverCapture2() {
		// System.out.println("In Crocodile");
		Crocodile crocodile = new Crocodile(3,2,'W','C');
		
		Pawn pawn1 = new Pawn(2, 0, 'B', 'P');
		Pawn pawn2 = new Pawn(2, 5, 'B', 'P');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{pawn1, null, crocodile, null, null, pawn2, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},crocodile);

		int[][] movesReturned = crocodile.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves = crocodile.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 3; expectedMoves[0][1]= 1;
		expectedMoves[1][0]= 3; expectedMoves[1][1]= 0;
		expectedMoves[2][0]= 3; expectedMoves[2][1]= 3;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 4;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 5;
		expectedMoves[5][0]= 2; expectedMoves[5][1]= 1;
		expectedMoves[6][0]= 2; expectedMoves[6][1]= 2;
		expectedMoves[7][0]= 2; expectedMoves[7][1]= 3;
		expectedMoves[8][0]= 4; expectedMoves[8][1]= 1;
		expectedMoves[9][0]= 4; expectedMoves[9][1]= 2;
		expectedMoves[10][0]= 4; expectedMoves[10][1]= 3;
		
		
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
    public void testCrocodileMovesRiverAbove() {
		// System.out.println("In Crocodile");
		Crocodile crocodile = new Crocodile(0,1,'W','C');
		Piece[][] board = {
				{null, crocodile, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},crocodile);

		int[][] movesReturned = crocodile.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves = crocodile.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 0; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 0; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 1; expectedMoves[2][1]= 0;
		expectedMoves[3][0]= 1; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 1; expectedMoves[4][1]= 2;
		expectedMoves[5][0]= 2; expectedMoves[5][1]= 1;
		expectedMoves[6][0]= 3; expectedMoves[6][1]= 1;

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
    public void testCrocodileMovesRiverBelow() {
		// System.out.println("In Crocodile");
		Crocodile crocodile = new Crocodile(6,1,'W','C');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, crocodile, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},crocodile);

		int[][] movesReturned = crocodile.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		expectedMoves = crocodile.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 5; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 1;
		expectedMoves[2][0]= 5; expectedMoves[2][1]= 2;
		expectedMoves[3][0]= 6; expectedMoves[3][1]= 0;
		expectedMoves[4][0]= 6; expectedMoves[4][1]= 2;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 1;
		expectedMoves[6][0]= 3; expectedMoves[6][1]= 1;
		
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
