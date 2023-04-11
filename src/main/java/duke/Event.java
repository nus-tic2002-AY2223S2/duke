/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    /**
     *  Attributes
     */
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     *  Constructor
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     *  @return start value of Task
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Change start value of Task
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return end value of Task
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Change end value of Task
     * @param end end time of the event
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * toString() method will print out Task's standard output
     * @return string format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateValidator.convertDateToDisplay(start) + " to: " + DateValidator.convertDateToDisplay(end) + ")";
    }

}
