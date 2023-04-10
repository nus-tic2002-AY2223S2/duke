package duke.TasksType;

import duke.Utility.Util;

import java.time.LocalDateTime;

public class Event extends Task
{
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event task type, has description (inherited) member, a from member, and to member
     * @param description takes in a string input description to describe the event
     * @param from takes in a LocalDateTime input to indicate the start date time of the event
     * @param to takes in a LocalDateTime input to indicate the end date time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This method is called to format the display for event tasks
     * @return a string used for display
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Util.displayDT(from) + " to: " + Util.displayDT(to) + ")" + " [Priority " + getPriorityLevel() + "]";
    }
//    public String getDescription() {
//        return description;
//    }

    /**
     * This method is called to get the start date time of the event task
     * @return a LocalDateTime for display or conversion
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * This method is called to get the end date time of the event task
     * @return a LocalDateTime for display or conversion
     */
    public LocalDateTime getTo() {
        return to;
    }
}
