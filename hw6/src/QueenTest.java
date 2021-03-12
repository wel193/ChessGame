import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the Queen class.
 */
public class QueenTest {
    Queen qw04, qb74;

    /**
     * Create objects for Queen. Initialized them with given row, column and ChessColor.
     *
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException {
        qw04 = new Queen(0, 4, ChessColor.WHITE);
        qb74 = new Queen(7, 4, ChessColor.BLACK);
    }


    @Test
    public void testCanMove() {
        //move diagonal
        assertTrue(qw04.canMove(3,7));
        assertTrue(qb74.canMove(4,1));

        //move vertical
        assertTrue(qw04.canMove(3, 4));
        assertTrue(qb74.canMove(1,4));

        //move horizontal
        assertTrue(qw04.canMove(0, 0));
        assertTrue(qb74.canMove(7,7));

        //can't move L patter
        assertFalse(qw04.canMove(2,5));
        assertFalse(qb74.canMove(1,2));

    }

    @Test
    public void testCanKill() throws IllegalPieceException {
        //can kill the piece on the movable path and the target piece is different color
        Rook rb37 = new Rook(3, 7, ChessColor.BLACK);
        Rook rw77 = new Rook(7, 7, ChessColor.WHITE);
        assertTrue(qw04.canKill(rb37));
        assertTrue(qb74.canKill(rw77));

        //can't kill the piece on the unmovable path
        Rook rb25 = new Rook(2, 5, ChessColor.BLACK);
        Rook rw12 = new Rook(1, 2, ChessColor.WHITE);
        assertFalse(qw04.canKill(rb25));
        assertFalse(qb74.canKill(rw12));

        //can't kill the piece with same color even on the movable path
        Rook rw37 = new Rook(3, 7, ChessColor.WHITE);
        Rook rb77 = new Rook(7, 7, ChessColor.BLACK);
        assertFalse(qw04.canKill(rw37));
        assertFalse(qb74.canKill(rb77));
    }
}

