package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "\t [T]" + super.toString();
    }
    public String getTaskType() {
        return "T";
    }
    public String getTaskStartTime() {
        return "";
    }
    public String getTaskEndTime() {
        return "";
    }
}
