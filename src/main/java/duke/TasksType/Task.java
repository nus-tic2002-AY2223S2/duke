package duke.TasksType;

public class Task
{
    //protected so that child class can utilize
    protected String description;
    protected boolean isDone;
    protected priorityLevel level;

    /**
     * Constructs Task class, parent of all task types.
     * Has description member, isDone member and priorityLevel
     * Asserts the assumption that the description should not be empty
     * @param description takes in a string to describe the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default is false because the task is not done
        this.level = priorityLevel.Medium; // default is medium level priority unless changed by user
        assert !description.isEmpty() : "Description cannot be empty";
    }

    /**
     * Returns a String to get the description of the particular task
     * @return a string of the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done
     * Changes the isDone member of the task to true
     * To indicate that the task has been done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task
     * Changes the isDone member of the task back to false
     * If the task was marked done previously e.g. marked wrongly by user or task was not completed
     * To indicate that the task has not been done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String to display the status of the task,
     * whether has it been done
     * Checks if isDone is true
     * @return a string "X" if it is true
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a String that contains the isDone status and the description of the task
     * overrides other classes' toString() method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * enum of priority levels that will not be changed
     * only these three levels will be used to indicate the priority level of each task
     */
    public enum priorityLevel {
        Low,
        Medium,
        High
    }

    /**
     * Returns the priorityLevel member of each task
     * This method is called to check the priority level of each task
     * @return priorityLevel
     */
    public priorityLevel getPriorityLevel() { // new
        return this.level;
    }

    /**
     * Changes the priorityLevel of each task
     * @param p takes in a priorityLevel input
     */
    public void changePriority(priorityLevel p) {
        this.level = p;
    }
}
