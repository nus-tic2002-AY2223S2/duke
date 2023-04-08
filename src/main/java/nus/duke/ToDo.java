package nus.duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(Task task) {
        super(task.description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
