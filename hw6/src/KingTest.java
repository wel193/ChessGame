import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the King class.
 */
public class KingTest {
    King kw03, kb73;

    /**
     * Create objects for King. Initialized them with given row, column and ChessColor.
     *
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException {
        kw03 = new King(0, 3, ChessColor.WHITE);
        kb73 = new King(7, 3, ChessColor.BLACK);
    }


    @Test
    public void testCanMove() {
        //move diagonal by one step
        assertTrue(kw03.canMove(1, 4));
        assertTrue(kb73.canMove(6, 2));

        //can't move diagonal by more than one steps
        assertFalse(kw03.canMove(3, 6));
        assertFalse(kb73.canMove(5, 1));

        //can move horizontal or vertical by one step
        assertTrue(kw03.canMove(0, 4));
        assertTrue(kb73.canMove(6, 3));

        //can't move horizontal or vertical by more than one steps
        assertFalse(kw03.canMove(0, 6));
        assertFalse(kb73.canMove(5, 3));
    }

    @Test
    public void testCanKill() throws IllegalPieceException {
        //can kill the piece on the movable path and the target piece is different color
        Rook rb04 = new Rook(0, 4, ChessColor.BLACK);
        Rook rw62 = new Rook(6, 2, ChessColor.WHITE);
        assertTrue(kw03.canKill(rb04));
        assertTrue(kb73.canKill(rw62));

        //can't kill the piece on the unmovable path
        Rook rb06 = new Rook(0, 6, ChessColor.BLACK);
        Rook rw51 = new Rook(5, 1, ChessColor.WHITE);
        assertFalse(kw03.canKill(rb06));
        assertFalse(kb73.canKill(rw51));

        //can't kill the piece with same color even on the movable path
        Rook rw04 = new Rook(0, 4, ChessColor.WHITE);
        Rook rb62 = new Rook(6, 2, ChessColor.BLACK);
        assertFalse(kw03.canKill(rw04));
        assertFalse(kb73.canKill(rb62));
    }
}

