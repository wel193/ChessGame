public class Pawn implements ChessPiece{
    private int row;
    private int column;
    private ChessColor color;

    public Pawn(int row, int column, ChessColor color){
        this.row = row;
        this.column = column;
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
}
