import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the Knight class.
 */
public class KnightTest {
    Knight knw23, knb53;

    /**
     * Create objects for Knight. Initialized them with given row, column and ChessColor.
     *
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException {
        knw23 = new Knight(2, 3, ChessColor.WHITE);
        knb53 = new Knight(5, 3, ChessColor.BLACK);
    }


    @Test
    public void testCanMove() {
        //move L pattern, 8 places
        assertTrue(knw23.canMove(4, 4));
        assertTrue(knw23.canMove(1, 1));
        assertTrue(knw23.canMove(0, 4));
        assertTrue(knw23.canMove(3,5));

        assertTrue(knb53.canMove(7,2));
        assertTrue(knb53.canMove(6,5));
        assertTrue(knb53.canMove(4,1));
        assertTrue(knb53.canMove(3,4));

        //can't move diagonal
        assertFalse(knw23.canMove(3, 4));
        assertFalse(knb53.canMove(4, 2));

        //can't move horizontal or vertical
        assertFalse(knw23.canMove(1, 3));
        assertFalse(knb53.canMove(5, 4));

    }

    @Test
    public void testCanKill() throws IllegalPieceException {
        //can kill the piece on the movable path and the target piece is different color
        Rook rb44 = new Rook(4, 4, ChessColor.BLACK);
        Rook rw65 = new Rook(6, 5, ChessColor.WHITE);
        assertTrue(knw23.canKill(rb44));
        assertTrue(knb53.canKill(rw65));

        //can't kill the piece on the unmovable path
        Rook rb34 = new Rook(3, 4, ChessColor.BLACK);
        Rook rw54 = new Rook(5, 4, ChessColor.WHITE);
        assertFalse(knw23.canKill(rb34));
        assertFalse(knb53.canKill(rw54));

        //can't kill the piece with same color even on the movable path
        Rook rw44 = new Rook(4, 4, ChessColor.WHITE);
        Rook rb65 = new Rook(6, 5, ChessColor.BLACK);
        assertFalse(knw23.canKill(rw44));
        assertFalse(knb53.canKill(rb65));
    }
}

