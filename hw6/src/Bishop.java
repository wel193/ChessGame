public class Bishop extends Piece implements ChessPiece{

    public Bishop(int row, int col, ChessColor color){
        super(row, col, color);
    }

    public boolean canMove(int row, int col){
        return Math.abs((this.getRow() - row) / (this.getColumn() - col)) == 1;
    }
}
