package exception;

/**
 * Thrown when a user provides blank or invalid input.
 * Demonstrates: custom exception, throw/throws.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
