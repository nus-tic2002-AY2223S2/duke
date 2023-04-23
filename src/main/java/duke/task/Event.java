package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Event Class is used to represent an event task which has description, start time and end time.
 * Event Class extends Task Class
 */
public class Event extends Task {

    /**
     * The start time of event task
     * The end time of event task
     */
    private final LocalDateTime start_time;
    private final LocalDateTime end_time;


    /**
     * Constructs a new Event object with the specified description, start time and end time.
     * @param description the description of task
     * @param start_time the task time of task
     * @param end_time the end time of task
     */
    public Event(String description, LocalDateTime start_time, LocalDateTime end_time) {
        super(description);
        this.start_time = start_time;
        this.end_time = end_time;
    }

    /**
     * Return a string represent the event task details with icon,status, description, date and time
     * @return a string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "\t [E]" + super.toString() + " (From: " + this.start_time.format(formatter) + " to " + this.end_time.format(formatter) + ")";
    }

    /**
     * Return the task type of event task
     * @return "E"
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Return the start time of event task
     * @return start_time variable of event task
     */
    public String getTaskStartTime() {
        return this.start_time.toString();
    }

    /**
     * Return the end time of event task
     * @return end_time variable of event task
     */
    public String getTaskEndTime() {
        return this.end_time.toString();
    }
}
