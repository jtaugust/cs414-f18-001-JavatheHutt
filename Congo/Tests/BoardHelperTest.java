package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import BoardLogic.*;

public class BoardHelperTest {

    BoardHelper boardHelper = new BoardHelper();

    @Test
    public void testConvertIndex(){
        int expectedIndex = 55;
        int returnedIndex = boardHelper.convertIndex("66");
        assertEquals(expectedIndex, returnedIndex);
    }

    @Test 
    public void testFindRow(){
        int expectedRow = 6;
        int returnedRow = boardHelper.findRow(450);
        assertEquals(expectedRow, returnedRow);
    }

    @Test 
    public void testFindColumn(){
        int expectedColumn = 6;
        int returnedColumn = boardHelper.findColumn(450);
        assertEquals(expectedColumn, returnedColumn);
    }
}