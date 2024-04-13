package exception;

public class InvalidPositionException extends RuntimeException {
    /**
     * Creates an invalidPosition runtime exception
     *
     * @post An InvalidPosition runtime exception is created
     */
    public InvalidPositionException() {
    }

    /**
     * Creates an invalidPosition runtime exception with the given message
     *
     * @param message Message to be shown
     * @post An InvalidPosition runtime exception with the given message is created
     */
    public InvalidPositionException(String message) {
        super(message);
    }
}
