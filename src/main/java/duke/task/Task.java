package duke.task;

/**
 * This is an abstract class representing a task.
 */
public abstract class Task {

    /**
     * The description of the task.
     * The status of the task (done/undone).
     */
    private final String description;
    private boolean isDone;


    /**
     * Constructs a Task object with the given description and sets its status to undone.
     * @param description the description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Return the status icon
     * @return "X" if task is done, empty if task is undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }


    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }


    /**
     * Returns the description of the task.
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Returns the string representation of the task.
     * @return The string representation of the task, which includes its status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }


    /**
     * Returns the type of the task.
     * @return type of task
     */
    public abstract String getTaskType();

    /**
     * Return the start time of task
     * @return start time of task
     */
    public abstract String getTaskStartTime();

    /**
     * Return the end time of task
     * @return the end time of task
     */
    public abstract String getTaskEndTime();

    /**
     * Returns the status of the task as a string for storage
     * @return 1 for done, 0 for undone
     */
    public String getTaskStatusInString() {
        return this.isDone ? "1" : "0";
    }
}
