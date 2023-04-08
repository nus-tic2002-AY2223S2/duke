package duke.task;

public class Deadline extends Task {
    protected String taskType;
    protected String by;

    public Deadline(String description, String taskType, String by) {
        super(description);
        this.taskType = taskType;
        this.by = by;
    }

    //Getter Methods
    public String getTaskType() {
        return taskType;
    }

    public String getBy() {
        return by;
    }

    //Setter Methods
    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        return super.toString() + "(by: " + by + ")";
    }
}
