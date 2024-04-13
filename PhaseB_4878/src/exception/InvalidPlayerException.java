package exception;

public class InvalidPlayerException extends RuntimeException {
    /**
     * Creates an invalidPlayer runtime exception
     *
     * @post An InvalidPlayer runtime exception is created
     */
    public InvalidPlayerException() {
    }

    /**
     * Creates an invalidPlayer runtime exception with the given message
     *
     * @param message Message to be shown
     * @post An InvalidPlayer runtime exception with the given message is created
     */
    public InvalidPlayerException(String message) {
        super(message);
    }
}