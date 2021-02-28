public class Pawn implements ChessPiece{
    private int row;
    private int column;
    private ChessColor color;

    public Pawn(int row, int column, ChessColor color){
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
        int temp = (this.getColor() == ChessColor.white)? 1: -1;
        return (x - this.getRow() == temp) && (y == this.getColumn());
    }

    public boolean canKill(ChessPiece piece){
        int temp = (this.getColor() == ChessColor.white)? 1 : -1;
        return (piece.getRow() - this.getRow() == temp)
                && (piece.getColumn() - this.getColumn() == temp)
                && (this.getColor() != piece.getColor());
    }
}
