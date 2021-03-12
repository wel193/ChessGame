import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the King class.
 */
public class PawnTest {
    Pawn pw10, pb63;

    /**
     * Create objects for Bishop, Pawn, Rook, Knight, Queen, and King. Initialized them with given row, column and ChessColor.
     *
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException {
        pw10 = new Pawn(1, 0, ChessColor.WHITE);
        pb63 = new Pawn(6, 3, ChessColor.BLACK);

    }

    /**
     * test the getRow method
     */
    @Test
    public void testGetRow() {
        assertEquals(1, pw10.getRow());
        assertEquals(6, pb63.getRow());
    }

    /**
     * Test the setRow method
     *
     * @throws IllegalPieceException if the value is invalid
     */
    @Test
    public void testSetRow() throws IllegalPieceException {
        pw10.setRow(4);
        assertEquals(4, pw10.getRow());
    }

    /**
     * Test the setRow exception
     *
     * @throws IllegalPieceException if the row value is less than 0 or greater than 7
     */
    @Test(expected = IllegalPieceException.class)
    public void testSetRowException() throws IllegalPieceException {
        pw10.setRow(-1);
    }

    /**
     * Test the setRow exception when the value is greater than 7
     *
     * @throws IllegalPieceException
     */
    @Test(expected = IllegalPieceException.class)
    public void _testSetRowException() throws IllegalPieceException {
        pb63.setRow(8);
    }


    /**
     * Test the getColumn method
     */
    @Test
    public void testGetColumn() {
        assertEquals(0, pw10.getColumn());
        assertEquals(3, pb63.getColumn());
    }

    /**
     * Test the setColumn method
     *
     * @throws IllegalPieceException if the value is invalid
     */
    @Test
    public void testSetColumn() throws IllegalPieceException {
        pb63.setColumn(1);
        assertEquals(1, pb63.getColumn());

    }

    /**
     * Test the setColumn exception when the value is less than zero
     *
     * @throws IllegalPieceException
     */
    @Test(expected = IllegalPieceException.class)
    public void testSetColumnException() throws IllegalPieceException {
        pb63.setColumn(-3);
    }

    /**
     * Test the setColumn exception when the value is greater than 7
     *
     * @throws IllegalPieceException
     */
    @Test(expected = IllegalPieceException.class)
    public void _testSetColumnException() throws IllegalPieceException {
        pw10.setColumn(9);
    }

    /**
     * Test the getColor method
     */
    @Test
    public void testGetColor() {
        assertEquals(ChessColor.WHITE, pw10.getColor());
        assertEquals(ChessColor.BLACK, pb63.getColor());
    }


    @Test
    public void testCanMove() {
        //can move one step ahead
        assertTrue(pw10.canMove(2, 0));
        assertTrue(pb63.canMove(5, 3));

        //can't move more than one step ahead
        assertFalse(pw10.canMove(3, 0));
        assertFalse(pb63.canMove(4, 3));

        //can't move backward
        assertFalse(pw10.canMove(1, 0));
        assertFalse(pb63.canMove(7, 3));

        //can't move diagonal
        assertFalse(pw10.canMove(2, 1));
        assertFalse(pb63.canMove(5, 2));

    }

    @Test
    public void testCanKill() throws IllegalPieceException {
        //can kill the piece on the the diagonal path by one step and
        // the target piece is different color
        Rook rb21 = new Rook(2, 1, ChessColor.BLACK);
        Rook rw52 = new Rook(5, 2, ChessColor.WHITE);
        assertTrue(pw10.canKill(rb21));
        assertTrue(pb63.canKill(rw52));

        //can't kill the piece on the the diagonal path by one step and
        // the target piece is same color
        Rook rw21 = new Rook(2, 1, ChessColor.WHITE);
        Rook rb52 = new Rook(5, 2, ChessColor.BLACK);
        assertFalse(pw10.canKill(rw21));
        assertFalse(pb63.canKill(rb52));

        //can't kill the piece on the movable path
        Rook rb20 = new Rook(2, 0, ChessColor.BLACK);
        Rook rw53 = new Rook(5, 3, ChessColor.WHITE);
        assertFalse(pw10.canKill(rb20));
        assertFalse(pb63.canKill(rw53));

        //can't kill the piece on the diagonal path by more than one step
        Rook rb32 = new Rook(3, 2, ChessColor.BLACK);
        assertFalse(pw10.canKill(rb32));

        //can't kill the piece other path
        Rook rw44 = new Rook(4, 4, ChessColor.WHITE);
        assertFalse(pb63.canKill(rw44));

    }

    @Test
    public void testToString() {
        assertEquals("WHITE Pawn", pw10.toString());
        assertEquals("BLACK Pawn", pb63.toString());
    }
}