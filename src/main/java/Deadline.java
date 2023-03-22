import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String taskString;
    protected LocalDateTime byString;

    public Deadline(String description, String deadline) {
        super(description);
        // *************************
        // level 8 DATES AND TIMES
        // *************************
        this.byString = LocalDateTime.parse(deadline);
    }
    public LocalDateTime getBy() {
        return this.byString;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by:" + byString.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }

}
