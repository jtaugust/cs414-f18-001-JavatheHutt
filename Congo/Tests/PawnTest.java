package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import BoardLogic.*;
import BoardLogic.State;
import BoardLogic.Pawn;

public class PawnTest { 
    //Helper Method for converting array of moves into ArrayList
    public ArrayList<String> movesToArrayList(int[][] possibleMoves){
    	ArrayList<String> possibleMovesList=new ArrayList<String>();
    	for(int i = 0; i < possibleMoves.length && possibleMoves[i][0]!=-1; i++) {
			possibleMovesList.add(""+Integer.toString(possibleMoves[i][0])+Integer.toString(possibleMoves[i][1]));
    	}
    	return possibleMovesList;
    }
    
	@Test
    public void testWhitePawnotherSideOfRiver() {
		
		Pawn pawn = new Pawn(2,1,'W','P');
		Pawn opponentPawn= new Pawn(1,1,'B','P');
		Pawn samePawn=new Pawn(1,0,'W','P');
		
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{samePawn, opponentPawn, null, null, null, null, null},
				{null, pawn, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},pawn);
		
		int[][] movesReturned = pawn.legalMoves(state);
		ArrayList<String> movesList= movesToArrayList(movesReturned);
//        System.out.println(movesList);
		ArrayList<String> expectedMoves= new ArrayList<String>();
		expectedMoves.add("11");
		expectedMoves.add("12");
		expectedMoves.add("31");
		expectedMoves.add("41");

		assertTrue(movesList.containsAll(expectedMoves) && expectedMoves.containsAll(movesList));
	}
	@Test
    public void testPawnSameSideOfRiver() {
		
		Pawn pawn = new Pawn(4,1,'B','P');
		Pawn opponentPawn= new Pawn(5,1,'W','P');
		Pawn samePawn=new Pawn(5,0,'B','P');
		
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, pawn, null, null, null, null, null},
				{samePawn, opponentPawn, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'W',new int[] {0,0},pawn);
		
		int[][] movesReturned = pawn.legalMoves(state);
		ArrayList<String> movesList= movesToArrayList(movesReturned);
//        System.out.println(movesList);
		ArrayList<String> expectedMoves= new ArrayList<String>();
		expectedMoves.add("30");
		expectedMoves.add("31");
		expectedMoves.add("32");

		assertTrue(movesList.containsAll(expectedMoves) && expectedMoves.containsAll(movesList));
	}
	
	@Test
    public void testPawnIfItJumps() {
		
		Pawn pawn = new Pawn(5,1,'B','P');
		Pawn opponentPawn= new Pawn(4,0,'W','P');
		Pawn samePawn=new Pawn(4,1,'B','P');
		Pawn otherOpPawn= new Pawn(3,1,'W','P');
		
		Piece[][] board = {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, otherOpPawn, null, null, null, null, null},
				{opponentPawn, samePawn, null, null, null, null, null},
				{null, pawn, null, null, null, null, null},
				{null, null, null, null, null, null, null}
			};
		State state = new State(board,'B',new int[] {0,0},pawn);
		
		int[][] movesReturned = pawn.legalMoves(state);
		ArrayList<String> movesList= movesToArrayList(movesReturned);
//        System.out.println(movesList);
		ArrayList<String> expectedMoves= new ArrayList<String>();
		expectedMoves.add("40");
		expectedMoves.add("42");
//		expectedMoves.add("3");

		assertTrue(movesList.containsAll(expectedMoves) && expectedMoves.containsAll(movesList));
   }
	
}
