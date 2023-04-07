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

    /////////////////////////////////////////////////////////////
    //  getStart() method will return the start value of duke.Task  //
    /////////////////////////////////////////////////////////////
    public String getStart() {
        return start;
    }

    /////////////////////////////////////////////////////////////
    //  setStart() method will change the start value of duke.Task  //
    /////////////////////////////////////////////////////////////
    public void setStart(String start) {
        this.start = start;
    }

    /////////////////////////////////////////////////////////
    //  getEnd() method will return the end value of duke.Task  //
    /////////////////////////////////////////////////////////
    public String getEnd() {
        return end;
    }

    /////////////////////////////////////////////////////////
    //  setEnd() method will change the end value of duke.Task  //
    /////////////////////////////////////////////////////////
    public void setEnd(String end) {
        this.end = end;
    }

    ///////////////////////////////////////////////////////////////
    //  toString() method will print out duke.Task's standard output  //
    ///////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

}
