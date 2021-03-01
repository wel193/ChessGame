public class Queen extends Piece implements ChessPiece{

    public Queen(int row, int col, ChessColor color){
        super(row, col, color);
    }


    public boolean canMove(int x, int y){
        return Math.abs((this.getRow() - x) / (this.getColumn() - y)) == 1
                && ((this.getRow() == x ) || (this.getRow() == y));
    }

}
