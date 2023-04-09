/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

public class Event extends Task {

    /**
     *  Attributes
     */
    protected String start;
    protected String end;

    /**
     *  Constructor
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     *  @return start value of Task
     */
    public String getStart() {
        return start;
    }

    /**
     *  Change start value of Task
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     *  @return end value of Task
     */
    public String getEnd() {
        return end;
    }

    /**
     *  Change end value of Task
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     *  toString() method will print out Task's standard output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

}
