public class Knight extends Piece{

    public Knight(int row, int col, ChessColor color){
        super(row, col, color);
    }


    public boolean canMove(int x, int y){
       int vertical = Math.abs(this.getRow() - x);
       int horizontal = Math.abs(this.getColumn() - y);
       return (horizontal == 2 && vertical == 1) || (horizontal == 1 && vertical == 2);
    }

}

