public class Queen implements ChessPiece{
    private int row;
    private int column;
    private ChessColor color;

    public Queen(int row, int column, ChessColor color){
        this.row = row;
        this.column = column;
        this.color = color;
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

    public boolean canMove(int x, int y){
        return Math.abs((this.getRow() - x) / (this.getColumn() - y)) == 1
                && (this.getRow() == x ) || (this.getRow() == y));
    }

    public boolean canKill(ChessPiece piece){
        return (this.canMove(piece.getRow(), piece.getColumn()))
                && (this.getColor() != piece.getColor());
    }
}
