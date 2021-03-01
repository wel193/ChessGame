public class Rook extends Piece implements ChessPiece{

    public Rook(int row, int col, ChessColor color){
        super(row, col, color);
    }

    public boolean canMove(int row, int col){
        return this.getRow() == row || this.getColumn() == col;
    }
}
