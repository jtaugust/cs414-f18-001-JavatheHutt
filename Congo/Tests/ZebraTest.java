package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.Piece;
import BoardLogic.State;
import BoardLogic.Zebra;

public class ZebraTest {

	@Test
	public void testSimpleZebraMoves() {
        Zebra zebra = new Zebra(6,6,'W','P');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, zebra}
			};
    	State state = new State(board,'W',"40NN","50WP");
		int[][] movesReturned=zebra.legalMoves(state);
		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 5; expectedMoves[0][1]= 4;
		expectedMoves[1][0]= 4; expectedMoves[1][1]= 5;
//		expectedMoves[2][0]= 6; expectedMoves[2][1]= 3;
//		expectedMoves[3][0]= 6; expectedMoves[3][1]= 2;
//		expectedMoves[4][0]= 6; expectedMoves[4][1]= 5;
//		expectedMoves[5][0]= 6; expectedMoves[5][1]= 6;
		
		for(int i=0; i<movesReturned.length;i++) {
			System.out.println("Zebra"+movesReturned[i][0]+","+movesReturned[i][1]);
		}
		assertArrayEquals(movesReturned, expectedMoves);
    }

}
