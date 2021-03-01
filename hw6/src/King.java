public class King extends Piece {

    public King(int row, int col, ChessColor color){
        super(row, col, color);
    }

    public boolean canMove(int row, int col){
        int vertical = Math.abs(this.getRow() - row);
        int horizontal = Math.abs(this.getColumn() - col);
        return (vertical <= 1 && horizontal <= 1);
    }
}
