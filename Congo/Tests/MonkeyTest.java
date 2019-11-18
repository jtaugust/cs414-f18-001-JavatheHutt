package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.Monkey;
import BoardLogic.Piece;
import BoardLogic.State;

public class MonkeyTest {

	@Test
	 public void testSimpleMonkeyMoves() {
    	Monkey m = new Monkey(6,1,'W','P');
    	Monkey bm = new Monkey(5,1,'B','P');
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, bm, null, null, null, null, null},
				{null, m, null, null, null, null, null}
			};
    	State state = new State(board,'W',"40NN","50WP");
		int[][] movesReturned=m.legalMoves(state);
		int[][] expectedMoves= new int[20][2];
		expectedMoves[0][0]= 5; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 5; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 6; expectedMoves[2][1]= 0;
		expectedMoves[3][0]= 6; expectedMoves[3][1]= 2;
		expectedMoves[4][0]= 4; expectedMoves[4][1]= 1;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 3;
		expectedMoves[6][0]= 6; expectedMoves[6][1]= 3;
		
		for(int i=0; i<movesReturned.length;i++) {
			System.out.println("Monkey"+movesReturned[i][0]+","+movesReturned[i][1]);
		}
		assertArrayEquals(movesReturned, expectedMoves);
    }

}
