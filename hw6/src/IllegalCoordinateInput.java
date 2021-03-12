/**
 * This class represents an exception class if the input coordinate is invalid
 */
public class IllegalCoordinateInput extends Exception {

    /**
     * Default constructor fot IllegalCoordinateInput class
     */
    public IllegalCoordinateInput() {
        super();
    }

    /**
     * Constructor for IllegalCoordinateInput class to print out personalized message.
     *
     * @param s exception message
     */
    public IllegalCoordinateInput(String s) {
        super(s);
    }
}
