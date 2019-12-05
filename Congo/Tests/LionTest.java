package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class LionTest {
    
    @Test
	public void testLionCapture() {
		
		Lion lion = new Lion(5,3,'W','L');

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
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, p1, p2, p3, null, null},
				{null, null, p4, lion, p5, null, null},
				{null, null, p6, p7, p8, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

        expectedMoves[0][0]= 4; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 4; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 4; expectedMoves[2][1]= 4;
		expectedMoves[3][0]= 5; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 5; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 6; expectedMoves[5][1]= 2;
		expectedMoves[6][0]= 6; expectedMoves[6][1]= 3;
		expectedMoves[7][0]= 6; expectedMoves[7][1]= 4;

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
	public void testLionMove1() {
		
		Lion lion = new Lion(5,3,'W','L');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, lion, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

        expectedMoves[0][0]= 4; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 4; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 4; expectedMoves[2][1]= 4;
		expectedMoves[3][0]= 5; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 5; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 6; expectedMoves[5][1]= 2;
		expectedMoves[6][0]= 6; expectedMoves[6][1]= 3;
		expectedMoves[7][0]= 6; expectedMoves[7][1]= 4;

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
	public void testLionMove2() {
		
		Lion lion = new Lion(6,2,'W','L');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, lion, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 5; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 6; expectedMoves[2][1]= 3;

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
	public void testLionMove3() {
		
		Lion lion = new Lion(4,4,'W','L');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, lion, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 4; expectedMoves[0][1]= 3;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 5; expectedMoves[2][1]= 4;

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
	public void testLionSpecialCapture1() {
		
        Lion lion = new Lion(6,3,'W','L');
        Lion lion2 = new Lion(0,3,'B','L');

		Piece[][] board = {
				{null, null, null, lion2, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, lion, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 5; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 3;
        expectedMoves[2][0]= 5; expectedMoves[2][1]= 4;
        expectedMoves[3][0]= 6; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 6; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 0; expectedMoves[5][1]= 3;

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
	public void testLionSpecialCapture2() {
		
        Lion lion = new Lion(4,2,'W','L');
        Lion lion2 = new Lion(2,4,'B','L');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, lion2, null, null},
				{null, null, null, null, null, null, null},
				{null, null, lion, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},lion);

		int[][] movesReturned = lion.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		lion.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 4; expectedMoves[0][1]= 3;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 2;
        expectedMoves[2][0]= 5; expectedMoves[2][1]= 3;
        expectedMoves[3][0]= 2; expectedMoves[3][1]= 4;
		
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