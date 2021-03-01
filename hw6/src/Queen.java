/**
 * This class represents Queen which derived Piece Abstract class
 */
public class Queen extends Piece {

    /**
     * Constructor of Queen and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     */
    public Queen(int row, int col, ChessColor color) {
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of Queen in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    public boolean canMove(int row, int col) {
        if (this.getRow() == row || this.getColumn() == col) {
            return true;
        }
        return Math.abs((this.getRow() - row) / (this.getColumn() - col)) == 1;
    }

}

