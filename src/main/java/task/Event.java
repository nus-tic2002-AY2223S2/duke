package task;

import task.Task;

/**
 * event task
 * -> returns the specific event message
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     *
     * @param description -> event's description
     * @param from -> start of events'date/time
     * @param to -> end of events'date/time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSave() {
        return "E " + super.toSave() + " | " + from + "-" + to;
    }
}
