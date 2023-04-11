package task;

import task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * deadline task
 * -> returns a specific deadline message
 */
public class Deadline extends Task{
    protected String by;

    /**
     *
     * @param description -> deadline's description
     * @param by -> deadline's due date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
        return "D " + super.toSave() + " | " + by;
    }

}
