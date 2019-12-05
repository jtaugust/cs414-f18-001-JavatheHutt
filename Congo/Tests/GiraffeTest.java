package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class GiraffeTest {
    
    @Test
	public void testGiraffeMovesJump1() {
		// System.out.println("In giraffe");
		Giraffe giraffe = new Giraffe(3,3,'W','G');

		Pawn p1 = new Pawn(1,1,'B','P');
		Pawn p2 = new Pawn(1,3,'B','P');
		Pawn p3 = new Pawn(1,5,'B','P');
		Pawn p4 = new Pawn(3,1,'B','P');
		Pawn p5 = new Pawn(3,5,'B','P');
		Pawn p6 = new Pawn(5,1,'B','P');
		Pawn p7 = new Pawn(5,3,'B','P');
		Pawn p8 = new Pawn(5,5,'B','P');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, p1, null, p2, null, p3, null},
				{null, null, null, null, null, null, null},
				{null, p4, null, giraffe, null, p5, null},
				{null, null, null, null, null, null, null},
				{null, p6, null, p7, null, p8, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},giraffe);

		int[][] movesReturned = giraffe.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		giraffe.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 1; expectedMoves[0][1]= 1;
		expectedMoves[1][0]= 1; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 1; expectedMoves[2][1]= 5;
		expectedMoves[3][0]= 2; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 2; expectedMoves[4][1]= 3;
		expectedMoves[5][0]= 2; expectedMoves[5][1]= 4;
		expectedMoves[6][0]= 3; expectedMoves[6][1]= 1;
		expectedMoves[7][0]= 3; expectedMoves[7][1]= 2;
		expectedMoves[8][0]= 3; expectedMoves[8][1]= 4;
		expectedMoves[9][0]= 3; expectedMoves[9][1]= 5;
		expectedMoves[10][0]= 4; expectedMoves[10][1]= 2;
		expectedMoves[11][0]= 4; expectedMoves[11][1]= 3;
		expectedMoves[12][0]= 4; expectedMoves[12][1]= 4;
		expectedMoves[13][0]= 5; expectedMoves[13][1]= 1;
		expectedMoves[14][0]= 5; expectedMoves[14][1]= 3;
		expectedMoves[15][0]= 5; expectedMoves[15][1]= 5;

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
	public void testGiraffeMovesJump2() {
		// System.out.println("In giraffe");
		Giraffe giraffe = new Giraffe(3,3,'W','G');

		Pawn p1 = new Pawn(1,1,'B','P');
		Pawn p2 = new Pawn(1,3,'B','P');
		Pawn p3 = new Pawn(1,5,'B','P');
		Pawn p4 = new Pawn(3,1,'B','P');
		Pawn p5 = new Pawn(3,5,'B','P');
		Pawn p6 = new Pawn(5,1,'B','P');
		Pawn p7 = new Pawn(5,3,'B','P');
		Pawn p8 = new Pawn(5,5,'B','P');

		Giraffe g1 = new Giraffe(2,2,'W','G');
		Giraffe g2 = new Giraffe(2,3,'W','G');
		Giraffe g3 = new Giraffe(2,4,'W','G');
		Giraffe g4 = new Giraffe(3,2,'W','G');
		Giraffe g5 = new Giraffe(3,4,'W','G');
		Giraffe g6 = new Giraffe(4,2,'W','G');
		Giraffe g7 = new Giraffe(4,3,'W','G');
		Giraffe g8 = new Giraffe(5,4,'W','G');



		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, p1, null, p2, null, p3, null},
				{null, null, g1, g2, g3, null, null},
				{null, p4, g4, giraffe, g5, p5, null},
				{null, null, g6, g7, g8, null, null},
				{null, p6, null, p7, null, p8, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},giraffe);

		int[][] movesReturned = giraffe.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		giraffe.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 1; expectedMoves[0][1]= 1;
		expectedMoves[1][0]= 1; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 1; expectedMoves[2][1]= 5;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 5;
		expectedMoves[5][0]= 5; expectedMoves[5][1]= 1;
		expectedMoves[6][0]= 5; expectedMoves[6][1]= 3;
		expectedMoves[7][0]= 5; expectedMoves[7][1]= 5;

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
