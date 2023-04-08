/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */
package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    /**
     *  Attributes
     */
    protected String by;

    /**
     *  Constructor
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline of Task
     */
    public String getBy() {
        return by;
    }

    /**
     * change the deadline of Task
     */
    public void setBy(String by) {
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
