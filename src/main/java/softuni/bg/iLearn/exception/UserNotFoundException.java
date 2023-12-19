package softuni.bg.iLearn.exception;

public class UserNotFoundException extends RuntimeException{

    private String username;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String username) {
        super("User not found!");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}
