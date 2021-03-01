public class Pawn extends Piece implements ChessPiece{

    public Pawn(int row, int col, ChessColor color){
        super(row, col, color);
    }

    public boolean canMove(int x, int y){
        int temp = (this.getColor() == ChessColor.white)? 1: -1;
        return (x - this.getRow() == temp) && (y == this.getColumn());
    }

    @Override
    public boolean canKill(ChessPiece piece){
        int temp = (this.getColor() == ChessColor.white)? 1 : -1;
        return (piece.getRow() - this.getRow() == temp)
                && (piece.getColumn() - this.getColumn() == temp)
                && (this.getColor() != piece.getColor());
    }
}
