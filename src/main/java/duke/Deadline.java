/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */
package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     *  Attributes
     */
    protected LocalDate by;

    /**
     *  Constructor
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline of Task
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * change the deadline of Task
     */
    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * print out Task's standard output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


}
