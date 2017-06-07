package draughts.exceptions;

/**
 * {@code EmptySquareException} extends {@link java.lang.RuntimeException RuntimeException}, 
 * so it is an unchecked exception just like the {@link OccupiedSquareException} exception. 
 */
public class EmptySquareException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmptySquareException(String message) {
        super(message);
    }

}
