public class Queen extends Piece {

    public Queen(int row, int col, ChessColor color) {
        super(row, col, color);
    }


    public boolean canMove(int x, int y) {
        if (this.getRow() == x || this.getColumn() == y) {
            return true;
        }
        return Math.abs((this.getRow() - x) / (this.getColumn() - y)) == 1;
    }

}

