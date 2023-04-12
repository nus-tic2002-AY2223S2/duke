package duke.TasksType;

import duke.Utility.Util;
import java.time.LocalDateTime;

public class Deadline extends Task
{
    private LocalDateTime by;

    /**
     * Constructor for Deadline task type, has a description (inherited) member and a by member for datetime
     * @param description takes in a string to store the description of the deadline class
     * @param by takes in a LocalDateTime input to store the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * This method formats the deadline print requirement.
     * @return a string to methods that calls it for printing
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Util.displayDT(by) + ")" + " [Priority " + getPriorityLevel() + "]";
    }

    /**
     * This method is called to get the date and time of the particular deadline clas
     * @return a LocalDateTime for formatting and subsequently for display or save
     */
    public LocalDateTime getBy() {
        return by;
    }
}
