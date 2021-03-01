/**
 * This class represents Knight which derived Piece Abstract class
 */
public class Knight extends Piece{

    /**
     * Constructor of Knight and initialized it to given row, column, and ChessColor
     * @param row the row value
     * @param col the column value
     * @param color the ChessColor
     */
    public Knight(int row, int col, ChessColor color){
        super(row, col, color);
    }


    /**
     * Method to check if this move follows the moving rule of Knight in the Chess game
     * @param row destination row value
     * @param col destination column value
     * @return boolean
     */
    public boolean canMove(int row, int col){
       int vertical = Math.abs(this.getRow() - row);
       int horizontal = Math.abs(this.getColumn() - col);
       return (horizontal == 2 && vertical == 1) || (horizontal == 1 && vertical == 2);
    }

}

