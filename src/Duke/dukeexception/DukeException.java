package Duke.dukeexception;

public class DukeException extends Exception {
    public DukeException() {
    }

    public DukeException(String s) {
        System.err.println(s);
    }
}