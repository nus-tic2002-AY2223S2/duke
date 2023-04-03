/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */
package duke;

public class Deadline extends Task {
    ///////////////////
    //  Attributes   //
    ///////////////////
    protected String by;

    ///////////////////
    //  Constructor  //
    ///////////////////
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    ///////////////////////////////////////////////////////
    //  getBy() method will return the deadline of duke.Task  //
    ///////////////////////////////////////////////////////
    public String getBy() {
        return by;
    }

    ///////////////////////////////////////////////////////
    //  setBy() method will change the deadline of duke.Task  //
    ///////////////////////////////////////////////////////
    public void setBy(String by) {
        this.by = by;
    }

    ///////////////////////////////////////////////////////////////
    //  toString() method will print out duke.Task's standard output  //
    ///////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
