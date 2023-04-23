package task;

/**
 * Represents a to-do task with a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        assert description != null : "Description cannot be null";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
