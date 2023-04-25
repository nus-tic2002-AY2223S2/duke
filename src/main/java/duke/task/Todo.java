package duke.task;

/**
 * To do Class represents a Todo task without any time constraint.
 * To do Class extends Task Class
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class
     * @param description the description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method of the Task class to add the task type
     * @return A formatted String representing the Todo task.
     */
    @Override
    public String toString() {
        return "\t [T]" + super.toString();
    }

    /**
     * Return the task type of todo task
     * @return string "T"
     */
    public String getTaskType() {
        return "T";
    }

    /**
     * This is because of todo Class extends the Task Class, but deadline task dose not have start time
     * @return empty
     */
    public String getTaskStartTime() {
        return "";
    }

    /**
     * This is because of todo Class extends the Task Class, but deadline task dose not have end time
     * @return empty
     */
    public String getTaskEndTime() {
        return "";
    }
}
