package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.Lion;
import BoardLogic.Piece;
import BoardLogic.State;

public class LionTest {

	@Test
	 public void testLionAttackMoves() {
    	Lion lion = new Lion(6,3,'W','P');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, lion, null, null, null}
			};
    	State state = new State(board,'W',"40NN","50WP");
		int[][] movesReturned=lion.legalMoves(state);
		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 5; expectedMoves[0][1]= 2;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 3;
		expectedMoves[2][0]= 5; expectedMoves[2][1]= 4;
		expectedMoves[3][0]= 6; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 6; expectedMoves[4][1]= 4;

		for(int i=0; i<expectedMoves.length;i++) {
//			System.out.println(expectedMoves[i][0]+","+expectedMoves[i][1]);
		}
		assertArrayEquals(movesReturned, expectedMoves);
    }

}
