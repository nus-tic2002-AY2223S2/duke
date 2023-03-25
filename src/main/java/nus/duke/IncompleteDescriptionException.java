package nus.duke;

public class IncompleteDescriptionException extends DukeException {
    private String message;
    public IncompleteDescriptionException(String msg) {
        super(msg);
    }

}



