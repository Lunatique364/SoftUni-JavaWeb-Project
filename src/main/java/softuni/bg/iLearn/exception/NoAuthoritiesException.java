package softuni.bg.iLearn.exception;

public class NoAuthoritiesException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "No authorities!";


    public NoAuthoritiesException() {
        super(EXCEPTION_MESSAGE);
    }

    public NoAuthoritiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthoritiesException(Throwable cause) {
        super(cause);
    }
}
