import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * A JUnit test class for the Rook class.
 */
public class RookTest {
    Rook rw00, rb70;

    /**
     * Create objects for Bishop. Initialized them with given row, column and ChessColor.
     * @throws IllegalPieceException if the row or column value is not valid.
     */
    @Before
    public void setUp() throws IllegalPieceException{
        rw00 = new Rook(0, 0, ChessColor.WHITE);
        rb70 = new Rook(7, 7, ChessColor.BLACK);
    }


    @Test
    public void testCanMove() {
        //can move vertical
        assertTrue(rw00.canMove(4,0));
        assertTrue(rb70.canMove(5,7));

        //can move horizontal
        assertTrue(rw00.canMove(0,4));
        assertTrue(rb70.canMove(7,4));

        //can't move diagonal
        assertFalse(rw00.canMove(3,3));
        assertFalse(rb70.canMove(4,4));

    }

    @Test
    public void testCanKill() throws IllegalPieceException{
        //can kill the piece on the movable path and the target piece is different color
        Pawn pb40 = new Pawn(4,0, ChessColor.BLACK);
        Pawn pw74 = new Pawn(7, 4, ChessColor.WHITE);
        assertTrue(rw00.canKill(pb40));
        assertTrue(rb70.canKill(pw74));

        //can't kill the piece with same color even on the movable path
        Pawn pw40 = new Pawn(4,0, ChessColor.WHITE);
        Pawn pb74 = new Pawn(7, 4, ChessColor.BLACK);
        assertFalse(rw00.canKill(pw40));
        assertFalse(rb70.canKill(pb74));

        //can't kill the piece on the unmovable path
        Pawn pb33 = new Pawn(3,3, ChessColor.BLACK);
        Pawn pw44 = new Pawn(4, 4, ChessColor.WHITE);
        assertFalse(rw00.canKill(pb33));
        assertFalse(rb70.canKill(pw44));
    }

}