package duke.TasksType;

import duke.Utility.Util;

import java.time.LocalDateTime;

public class Event extends Task
{
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to)
    {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Util.displayDT(from) + " to: " + Util.displayDT(to) + ")" + " [Priority " + getPriorityLevel() + "]";
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }
}
