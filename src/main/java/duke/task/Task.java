package duke.task;

import duke.exception.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = null;
    }

    //Getters method
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the priority of a task as a string.
     *
     * @return a string of the priority of the task.
     */
    public String getPriority() {
        if (priority == null) {
            return " ";
        }
        switch (priority) {
            case LOW:
                return "L";
            case MEDIUM:
                return "M";
            case HIGH:
                return "H";
            default:
                return " ";
        }
    }

    //Setters method

    /**
     * Sets the priority of a task based on the given string input for both Mark method and storage load method.
     *
     * @param p a string representing the priority of the task.
     * @throws DukeException if the input string does not match "HIGH", "H", "MEDIUM", "M", "LOW", "L".
     */
    public void setPriority(String p) throws DukeException {
        switch (p) {
            case "HIGH":
            case "H":
                priority = Priority.HIGH;

                break;
            case "MEDIUM":
            case "M":
                priority = Priority.MEDIUM;

                break;
            case "LOW":
            case "L":
                priority = Priority.LOW;

                break;
            case " ":
            case "":
                priority = null;
                break;
            default:
                throw new DukeException("☹ OOPS!!! Please use either HIGH,MEDIUM,LOW when setting priority");

        }
    }

    public void setStatusAsMarked() {
        isDone = true;
    }

    public void setStatusAsUnmarked() {
        isDone = false;
    }

    public String toString() {
        return String.format("[" + this.getTaskType() + "]" + "[" + this.getStatus() + "]" + "[" + this.getPriority() + "] " + description);
    }

}