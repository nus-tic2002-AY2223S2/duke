package duke.TasksType;

public class Task
{
    //protected so that child class can utilize
    protected String description;
    protected boolean isDone;

    protected priorityLevel level;

    /**
     * Constructor for Task class, parent of all task types.
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
     * This method is called to get the description of the particular task
     * @return a string of the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method changes the isDone member of the task to true
     * to indicate that the task has been done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method changes the isDone member of the task back to false
     * if the task was marked done previously e.g. marked wrongly by user or task was not completed
     * to indicate that the task has not been done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * This method is called to display the status of the task,
     * whether has it been done
     * Checks if isDone is true
     * @return a string "X" if it is true
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method is called to display the particular task
     * overrides other classes' toString() method
     * @return a string that contains the isDone status and the description of the task
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
     * This method is called to check the priority level of each task
     * @return the priorityLevel member of each task
     */
    public priorityLevel getPriorityLevel() { // new
        return this.level;
    }

    /**
     * This method is called to change the priorityLevel member of the particular task
     * @param p takes in a priorityLevel input
     */
    public void changePriority(priorityLevel p) {
        this.level = p;
    }
}
