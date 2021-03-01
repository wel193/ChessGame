/**
 * This class represents Bishop which derived Piece Abstract class
 */
public class Rook extends Piece {

    /**
     * Constructor of Rook and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     */
    public Rook(int row, int col, ChessColor color){
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of Rook in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    public boolean canMove(int row, int col){
        return this.getRow() == row || this.getColumn() == col;
    }
}
