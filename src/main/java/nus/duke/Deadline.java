package nus.duke;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byString;

    public Deadline(String description, LocalDateTime deadLine) {
        super(description);
        this.byString = deadLine;

    }


    public String toString() {
        return "[D]" + super.toString() + "(by:" + byString.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm")) + ")";
    }

}
