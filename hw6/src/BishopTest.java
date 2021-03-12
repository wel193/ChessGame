import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the Bishop class.
 */
public class BishopTest {
    Bishop bw05, bb72;

    /**
     * Create objects for Bishop. Initialized them with given row, column and ChessColor.
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException{
        bw05 = new Bishop(0, 5, ChessColor.WHITE);
        bb72 = new Bishop(7, 2, ChessColor.BLACK);
    }


    @Test
    public void testCanMove() {
        //move diagonal
        assertTrue(bw05.canMove(3,2));
        assertTrue(bb72.canMove(4,5));

        //can't move vertical
        assertFalse(bw05.canMove(3, 5));
        assertFalse(bb72.canMove(6,2));

        //can't move horizontal
        assertFalse(bw05.canMove(0,3));
        assertFalse(bb72.canMove(7,6));

    }

    @Test
    public void testCanKill() throws IllegalPieceException{
        //can kill the piece on the diagonal path and the target piece is different color
        Pawn pb23 = new Pawn(2,3, ChessColor.BLACK);
        Pawn pw45 = new Pawn(4, 5, ChessColor.WHITE);
        assertTrue(bw05.canKill(pb23));
        assertTrue(bb72.canKill(pw45));

        //can't kill the piece with same color even on the movable path
        Pawn pw23 = new Pawn(2,3, ChessColor.WHITE);
        Pawn pb45 = new Pawn(4, 5, ChessColor.BLACK);
        assertFalse(bw05.canKill(pw23));
        assertFalse(bb72.canKill(pb45));

    }

}