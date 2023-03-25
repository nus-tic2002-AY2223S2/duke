import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime byString;

    public Deadline(String description, LocalDateTime deadLine) {
        super(description);
        this.byString = deadLine;
        // *************************
        // level 8 Dates and Times
        // *************************
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //this.byString = LocalDateTime.parse(deadline,formatter);
        //this.byString = LocalDateTime.parse(deadline);
    }


    public LocalDateTime getBy() {
        return this.byString;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by:" + byString.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }

}
