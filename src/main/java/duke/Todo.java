/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

public class Todo extends Task {

    /**
     *  Constructor
     */
    public Todo(String description) {
        super(description);
    }

    /**
     *  toString() method will print out duke.Task's standard output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
