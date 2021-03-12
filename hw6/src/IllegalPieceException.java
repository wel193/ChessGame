/**
 * This class represents an exception class if the Piece has illegal set up value.
 */
public class IllegalPieceException extends Exception{

    /**
     * Default constructor fot IllegalPieceException class
     */
    public IllegalPieceException(){
        super();
    }

    /**
     * Constructor for IllegalPieceException class to print out personalized message.
     * @param s exception message
     */
    public IllegalPieceException(String s){
        super(s);
    }
}
