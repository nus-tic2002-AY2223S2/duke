/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke;

public class Task {
    /**
    * Attributes
    */
    protected String description;
    protected boolean isDone;

    /**
    *  Constructor
    */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *  getDescription() method will return the description of Task
     */
    public String getDescription() {
        return this.description; // returns the value stored in the description attribute
    }

    /**
     *  setDescription() method will change the description of Task
     */
    public void setDescription(String newDescription) {
        this.description = newDescription; // changes the description with the new input
    }

    /**
     *  getTaskStatus() method will return the status of the Task
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     *  markAsDone() method will mark the current Task object as done by changing the isDone to TRUE
     */
    public void markAsDone() {
        this.isDone = true; // converts isDone attribute to 'TRUE'
    }

    /**
     *  markAsUndone() method will un-mark the current task object as undone by changing the isDone to FALSE
     */
    public void markAsUndone() {
        this.isDone = false; // converts isDone attribute to 'FALSE'
    }

    /**
     *  toString() method will print out duke.Task's standard output
     */

    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }

}
