/**
 * This class represents Pawn which derived Piece Abstract class
 */
public class Pawn extends Piece {

    /**
     * Constructor of Pawn and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     */
    public Pawn(int row, int col, ChessColor color){
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of Pawn in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    public boolean canMove(int row, int col){
        int temp = (this.getColor() == ChessColor.white)? 1: -1;
        return (row - this.getRow() == temp) && (col == this.getColumn());
    }

    /**
     * Override the can Kill method in the Piece abstract class, since Pawn has different killing move rule.
     * @param piece the ChessPiece object
     * @return boolean
     */
    @Override
    public boolean canKill(ChessPiece piece){
        int temp = (this.getColor() == ChessColor.white)? 1 : -1;
        return (piece.getRow() - this.getRow() == temp)
                && Math.abs(piece.getColumn() - this.getColumn()) == 1
                && (this.getColor() != piece.getColor());
    }

}
