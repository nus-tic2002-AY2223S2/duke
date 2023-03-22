import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        // *************************
        // level 8 Dates and Times
        // *************************
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);

    }

    public String toString() {
        return "[E]" + super.toString() + "(from:" + start.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + " to:" + end.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }
}
