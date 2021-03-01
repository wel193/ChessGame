public abstract class Piece implements ChessPiece {
    private int row;
    private int column;
    private ChessColor color;
    private boolean alive = true;

    public Piece(int row, int column, ChessColor color){
        this.setRow(row);
        this.setColumn(column);
        this.color = color;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void killed(){
        this.alive = false;
    }

    public int getRow(){
        return this.row;
    }

    public void setRow(int row) throws IllegalArgumentException{
        if (row < 0 || row > 7){
            throw new IllegalArgumentException("The row set up is not valid");
        }
        this.row = row;
    }

    public void setColumn(int col) throws IllegalArgumentException{
        if (col < 0 || col > 7){
            throw new IllegalArgumentException("The column set up is not valid");
        }
        this.column = col;
    }

    public int getColumn(){
        return this.column;
    }

    public ChessColor getColor(){
        return this.color;
    }

    public abstract boolean canMove(int x, int y);

    public boolean canKill(ChessPiece piece){
        return canMove(piece.getRow(), piece.getColumn())
                && (piece.getColor() != this.getColor());
    }

    public String toString(){
        return String.format("%s %s", this.getColor(), this.getClass().getName());
    }

}

