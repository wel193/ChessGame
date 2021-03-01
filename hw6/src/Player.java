public class Player {
    private final ChessColor color;
    private King king;

    public Player(ChessColor color){
        this.color = color;
    }

    public void setKing(King piece){
        this.king = piece;
    }

    public boolean win(){
        return  !this.king.isAlive();
    }

}
