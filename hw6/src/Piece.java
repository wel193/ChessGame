public abstract class Piece implements ChessPiece {
    private int row;
    private int column;
    private ChessColor color;
    private boolean alive = true;

    public Piece(int row, int column, ChessColor color){
        this.row = row;
        this.column = column;
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

