public class Pawn extends Piece {

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
                && Math.abs(piece.getColumn() - this.getColumn()) == 1
                && (this.getColor() != piece.getColor());
    }

    public static void main(String[] arg){
        Pawn p = new Pawn(0, 4, ChessColor.black);
        System.out.println(p.getRow());
    }
}
