package nus.duke;

public class InvalidCodeException extends Throwable {
    private String message;
    public InvalidCodeException(String message) {
        this.message = message;
    }
}
