package nus.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from:" + start.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + " to:" + end.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }
}
