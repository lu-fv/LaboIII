package Exceptions;

public class ExceptionIncorrectPassword extends RuntimeException {
    public ExceptionIncorrectPassword(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}