/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */
package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    /**
     *  Attributes
     */
    protected LocalDateTime by;

    /**
     *  Constructor
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline of Task
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * change the deadline of Task
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * print out Task's standard output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateValidator.convertDateToDisplay(by) + ")";
    }


}
