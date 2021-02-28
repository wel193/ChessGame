/**
 * This interface represents chess piece.
 */
public interface ChessPiece {

    /**
     * Determine current position
     * @return row
     */
     public int getRow();

    /**
     * Determine current position
     * @return column
     */
     public int getColumn();

    /**
     * Determine color
     * @return color
     */
    public ChessColor getColor();

    /**
     * Determine if the chess can move to certain position
     * @param row row
     * @param col column
     * @return true if can move
     */
    public boolean canMove(int row, int col);

    /**
     * Determine if the chess can kill the chess input
     * @param piece the ChessPiece object
     * @return boolean
     */
    public boolean canKill(ChessPiece piece);

}
