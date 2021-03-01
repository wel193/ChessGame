/**
 * This represents an abstract class of Piece with row, column, ChessColor, and is alive.
 */
public abstract class Piece implements ChessPiece {
    private int row;
    private int column;
    private ChessColor color;
    private boolean alive = true;

    /**
     * Constructor of Piece and initialized it to given row, column, and ChessColor
     * @param row
     * @param column
     * @param color
     */
    public Piece(int row, int column, ChessColor color){
        this.setRow(row);
        this.setColumn(column);
        this.color = color;
    }

    /**
     * Method to check if the piece is alive
     * @return boolean
     */
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * Method to set alive to false when the piece been killed
     */
    public void killed(){
        this.alive = false;
    }

    /**
     * Accessor method to get the row of Piece object
     * @return the row of the calling object
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Modifier method to set the row of the Piece object
     * @param row the row value
     * @throws IllegalArgumentException if the row value is not valid
     */
    public void setRow(int row) throws IllegalArgumentException{
        if (row < 0 || row > 7){
            throw new IllegalArgumentException("The row set up is not valid");
        }
        this.row = row;
    }

    public int getColumn(){
        return this.column;
    }

    /**
     * Modifier method to set the column of the Piece object
     * @param col the column value
     * @throws IllegalArgumentException if the column value is not valid
     */
    public void setColumn(int col) throws IllegalArgumentException{
        if (col < 0 || col > 7){
            throw new IllegalArgumentException("The column set up is not valid");
        }
        this.column = col;
    }

    /**
     * Accessor method to get the color of the calling object
     * @return
     */
    public ChessColor getColor(){
        return this.color;
    }

    /**
     * Abstract method to check if the piece can move following the ChessGame rule
     * @param x the row on the board
     * @param y the column on the board
     * @return boolean
     */
    public abstract boolean canMove(int x, int y);

    /**
     * Method to check if the piece can kill the input piece
     * @param piece the ChessPiece object
     * @return boolean
     */
    public boolean canKill(ChessPiece piece){
        return canMove(piece.getRow(), piece.getColumn())
                && (piece.getColor() != this.getColor());
    }

    /**
     * toString method to print out certain piece
     * @return string format of certain piece
     */
    public String toString(){
        return String.format("%s %s", this.getColor(), this.getClass().getName());
    }

}

