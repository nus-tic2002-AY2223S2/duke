package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert description != null : "Description cannot be null";
        assert by != null : "LocalDateTime cannot be null";
        this.by = by;
    }

    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return dateTime.format(formatter);
    }


    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + formatDateTime(by) + ")";
    }

    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }
}
