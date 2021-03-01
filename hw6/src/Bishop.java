/**
 * This class represents Bishop which derived Piece Abstract class
 */
public class Bishop extends Piece {

    /**
     * Constructor of Bishop and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     */
    public Bishop(int row, int col, ChessColor color){
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of Bishop in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    public boolean canMove(int row, int col){
        return Math.abs((this.getRow() - row) / (this.getColumn() - col)) == 1;
    }
}
