/**
 * This represents a Player class with ChessColor and if King is alive.
 */
public class Player {
    private final ChessColor color;
    private King king;

    /**
     * Constructor of Player object and initialized to given ChessColor
     * @param color
     */
    public Player(ChessColor color){
        this.color = color;
    }

    /**
     * Set the given King to the Player object
     * @param piece King
     */
    public void setKing(King piece){
        this.king = piece;
    }

    /**
     * Method to check if the player's King is killed. The Player lose once its King been killed.
     * @return boolean
     */
    public boolean lose(){
        return  !this.king.isAlive();
    }

}
