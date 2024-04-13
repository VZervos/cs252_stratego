package exception;

public class InvalidPieceException extends RuntimeException {
    /**
     * Creates an invalidPiece runtime exception
     *
     * @post An InvalidPiece runtime exception is created
     */
    public InvalidPieceException() {
    }

    /**
     * Creates an invalidPiece runtime exception with the given message
     *
     * @param message Message to be shown
     * @post An InvalidPiece runtime exception with the given message is created
     */
    public InvalidPieceException(String message) {
        super(message);
    }
}