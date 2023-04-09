package duke.TasksType;

public class Task
{
    protected String description;
    protected boolean isDone;

    protected priorityLevel level;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.level = priorityLevel.Medium; // new
        assert !description.isEmpty() && description != null : "Description cannot be empty";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public enum priorityLevel { // new
        Low,
        Medium,
        High
    }

    public priorityLevel getPriorityLevel() { // new
        return this.level;
    }

    /**
     *
     * @param p 
     */
    public void changePriority(priorityLevel p) {
        this.level = p;
    }
}
