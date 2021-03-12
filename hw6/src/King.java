/**
 * This class represents King which derived Piece Abstract class
 */
public class King extends Piece {

    /**
     * Constructor of King and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     * @throws IllegalPieceException if the row or column has invalid value
     */
    public King(int row, int col, ChessColor color) throws IllegalPieceException{
        super(row, col, color);
    }

    /**
     * Method to check if this move follows the moving rule of King in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    @Override
    public boolean canMove(int row, int col){
        int vertical = Math.abs(this.getRow() - row);
        int horizontal = Math.abs(this.getColumn() - col);
        return (vertical <= 1 && horizontal <= 1);
    }
}
