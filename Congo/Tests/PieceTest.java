package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class PieceTest {

    //
    // Test Getters 
    //

    @Test 
    public void testGetRow(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        int returnedRow = pawn1.getRow();
        int expectedRow = 3;

        assertEquals(returnedRow,expectedRow);
    }

    @Test 
    public void testGetColumn(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        int returnedColumn = pawn1.getColumn();
        int expectedColumn = 3;

        assertEquals(returnedColumn,expectedColumn);
    }

    @Test 
    public void testGetColor(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        char returnedColor = pawn1.getColor();
        char expectedColor = 'W';

        assertEquals(returnedColor,expectedColor);
    }

    @Test 
    public void testGetType(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        char returnedColor = pawn1.getType();
        char expectedColor = 'P';

        assertEquals(returnedColor,expectedColor);
    }

    //
    // Test Getters 
    //

    @Test 
    public void testSetRow(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        pawn1.setRow(2);
        int returnedRow = pawn1.getRow();
        int expectedRow = 2;

        assertEquals(returnedRow,expectedRow);
    }

    @Test 
    public void testSetColumn(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        pawn1.setColumn(2);
        int returnedRow = pawn1.getColumn();
        int expectedRow = 2;

        assertEquals(returnedRow,expectedRow);
    }

    @Test 
    public void testSetColor(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        pawn1.setColor('B');
        int returnedColor = pawn1.getColor();
        char expectedColor = 'B';

        assertEquals(returnedColor,expectedColor);
    }

    @Test 
    public void testSetType(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        pawn1.setType('S');
        char returnedColor = pawn1.getType();
        char expectedColor = 'S';

        assertEquals(returnedColor,expectedColor);
    }

    @Test 
    public void testIsIndexBounded1(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        assertTrue(pawn1.isIndexBounded(3, 3));
    }

    @Test 
    public void testIsIndexBounded2(){
        Pawn pawn1 = new Pawn(3,3,'W','P');
        assertTrue(!pawn1.isIndexBounded(7, 8));
    }

    @Test
    public void testContainsMoveWhenTrue() {
		Pawn pawn1 = new Pawn(1,3,'B','P');

    	int[][] expectedMoves= new int[20][2];
		expectedMoves = pawn1.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 3; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 3; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 2; expectedMoves[2][1]= 0;
		expectedMoves[3][0]= 2; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 2; expectedMoves[4][1]= 2;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 0;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 1;
        expectedMoves[7][0]= 4; expectedMoves[7][1]= 2;
        
        assertTrue(pawn1.containsMove(expectedMoves, new int[] {3,0}));   	
    }

    @Test
    public void testContainsMoveWhenFalse() {
		Pawn pawn1 = new Pawn(1,3,'B','P');

    	int[][] expectedMoves= new int[20][2];
		expectedMoves = pawn1.formatMoveArray(expectedMoves);

		expectedMoves[0][0]= 3; expectedMoves[0][1]= 0;
		expectedMoves[1][0]= 3; expectedMoves[1][1]= 2;
		expectedMoves[2][0]= 2; expectedMoves[2][1]= 0;
		expectedMoves[3][0]= 2; expectedMoves[3][1]= 1;
		expectedMoves[4][0]= 2; expectedMoves[4][1]= 2;
		expectedMoves[5][0]= 4; expectedMoves[5][1]= 0;
		expectedMoves[6][0]= 4; expectedMoves[6][1]= 1;
        expectedMoves[7][0]= 4; expectedMoves[7][1]= 2;
        
        assertTrue(!pawn1.containsMove(expectedMoves, new int[] {5,0}));   	
    }

    @Test
    public void testFormateMovesArray() {
		Pawn pawn1 = new Pawn(1,3,'B','P');

        int[][] expectedMoves = new int[20][2];
        int[][] returnedMoves = new int[20][2]; 
        pawn1.formatMoveArray(returnedMoves);

		expectedMoves[0][0]= -1; expectedMoves[0][1]= -1;
		expectedMoves[1][0]= -1; expectedMoves[1][1]= -1;
		expectedMoves[2][0]= -1; expectedMoves[2][1]= -1;
		expectedMoves[3][0]= -1; expectedMoves[3][1]= -1;
		expectedMoves[4][0]= -1; expectedMoves[4][1]= -1;
		expectedMoves[5][0]= -1; expectedMoves[5][1]= -1;
		expectedMoves[6][0]= -1; expectedMoves[6][1]= -1;
        expectedMoves[7][0]= -1; expectedMoves[7][1]= -1;
        expectedMoves[8][0]= -1; expectedMoves[8][1]= -1;
		expectedMoves[9][0]= -1; expectedMoves[9][1]= -1;
		expectedMoves[10][0]= -1; expectedMoves[10][1]= -1;
		expectedMoves[11][0]= -1; expectedMoves[11][1]= -1;
		expectedMoves[12][0]= -1; expectedMoves[12][1]= -1;
		expectedMoves[13][0]= -1; expectedMoves[13][1]= -1;
		expectedMoves[14][0]= -1; expectedMoves[14][1]= -1;
        expectedMoves[15][0]= -1; expectedMoves[15][1]= -1;
        expectedMoves[16][0]= -1; expectedMoves[16][1]= -1;
		expectedMoves[17][0]= -1; expectedMoves[17][1]= -1;
		expectedMoves[18][0]= -1; expectedMoves[18][1]= -1;
		expectedMoves[19][0]= -1; expectedMoves[19][1]= -1;
		
        
        assertArrayEquals(expectedMoves,returnedMoves);   	
    }
}
