/**
 * This class represents Bishop which derived Piece Abstract class
 */
public class Bishop extends Piece {

    /**
     * Constructor of Bishop and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     * @throws IllegalPieceException if the row or column has invalid value
     */
    public Bishop (int row, int col, ChessColor color) throws IllegalPieceException{
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of Bishop in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    @Override
    public boolean canMove(int row, int col){
        int rowDiff =  Math.abs(this.getRow() - row);
        int colDiff = Math.abs (this.getColumn() - col);
        return rowDiff == colDiff;
    }
}
