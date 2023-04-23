package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private final LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "\t [D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
    public String getTaskType() {
        return "D";
    }
    public String getTaskStartTime() {
        return null;
    }
    public String getTaskEndTime() {
        return this.by.toString();
    }
}
