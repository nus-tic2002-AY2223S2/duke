package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start_time;
    private final LocalDateTime end_time;
    public Event(String description, LocalDateTime start_time, LocalDateTime end_time) {
        super(description);
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "\t [E]" + super.toString() + " (From: " + this.start_time.format(formatter) + " to " + this.end_time.format(formatter) + ")";
    }
    public String getTaskType() {
        return "E";
    }
    public String getTaskStartTime() {
        return this.start_time.toString();
    }
    public String getTaskEndTime() {
        return this.end_time.toString();
    }
}
