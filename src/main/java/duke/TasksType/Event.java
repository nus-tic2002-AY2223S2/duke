package duke.TasksType;

public class Event extends Task
{
    protected String from;
    protected String to;
    public Event(String description, String from, String to)
    {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    public String getDescription() {
        return description;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
}
