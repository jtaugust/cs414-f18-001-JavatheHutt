package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class MonkeyTest {
    
    @Test
	public void testMonkeyCapture() {
		
		Monkey monkey = new Monkey(3,3,'W','M');

		Giraffe g1 = new Giraffe(2,2,'B','G');
		Giraffe g2 = new Giraffe(2,3,'B','G');
		Giraffe g3 = new Giraffe(2,4,'B','G');
		Giraffe g4 = new Giraffe(3,2,'B','G');
		Giraffe g5 = new Giraffe(3,4,'B','G');
		Giraffe g6 = new Giraffe(4,2,'B','G');
		Giraffe g7 = new Giraffe(4,3,'B','G');
		Giraffe g8 = new Giraffe(5,4,'B','G');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, g1, g2, g3, null, null},
				{null, null, g4, monkey, g5, null, null},
				{null, null, g6, g7, g8, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},monkey);

		int[][] movesReturned = monkey.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		monkey.formatMoveArray(expectedMoves);

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
    
    @Test
	public void testMonkeyMoves() {
		
		Monkey monkey = new Monkey(3,3,'W','M');

		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, monkey, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},monkey);

		int[][] movesReturned = monkey.legalMoves(state);

		int[][] expectedMoves= new int[20][2];
		monkey.formatMoveArray(expectedMoves);

        expectedMoves[0][0]= 2; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 2; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 2; expectedMoves[2][1]= 4;
		expectedMoves[3][0]= 3; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 3; expectedMoves[4][1]= 4;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 2;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 3;
        expectedMoves[7][0]= 4; expectedMoves[7][1]= 4;
        
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