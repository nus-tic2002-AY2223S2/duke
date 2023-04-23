package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class is used to represent a task which has deadline that extends Task Class
 */
public class Deadline extends Task {
    /**
     * The due date and time of the deadline task.
     */
    private final LocalDateTime by;

    /**
     * Constructs a deadline task with the given description and due date and time
     * @param description the description of task
     * @param by the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string contains the task type icon, the description of the task,
     * and the due date and time of the task.
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "\t [D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    /**
     * Returns the task type of the deadline task.
     * @return type of deadline task
     */
    public String getTaskType() {
        return "D";
    }

    /**
     * This is because of Deadline Class extends the Task Class, but deadline task does not have start time
     * @return null
     */
    public String getTaskStartTime() {
        return null;
    }

    /**
     * Returns the end time of the deadline task
     * @return the end time of the deadline task
     */
    public String getTaskEndTime() {
        return this.by.toString();
    }
}
