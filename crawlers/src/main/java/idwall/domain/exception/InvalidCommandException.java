package idwall.domain.exception;

/**
 * Created by Bruno Fernandes.
 */
public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException() {
        super("Invalid command");
    }
}
