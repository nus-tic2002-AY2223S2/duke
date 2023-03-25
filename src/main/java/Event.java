import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        // *************************
        // level 8 Dates and Times
        // *************************
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //this.start = LocalDateTime.parse(start,formatter);
        //this.end = LocalDateTime.parse(end,formatter);
    }

    public String toString() {
        return "[E]" + super.toString() + "(from:" + start.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + " to:" + end.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }
}
