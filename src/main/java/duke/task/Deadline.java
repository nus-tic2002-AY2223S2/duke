package duke.task;

public class Deadline extends Task {
    private final String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "\t [D]" + super.toString() + " (by: " + this.by + ")";
    }
    public String getTaskType() {
        return "D";
    }
    public String getTaskStartTime() {
        return "";
    }
    public String getTaskEndTime() {
        return this.by;
    }
}
