package draughts.exceptions;

/**
 * {@code OccupiedSquareException} extends {@link java.lang.RuntimeException RuntimeException}, 
 * so it is an unchecked exception like the {@link java.lang.IllegalArgumentException IllegalArgumentException} exception. 
 */
public class OccupiedSquareException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OccupiedSquareException(String message) {
        super(message);
    }

}
