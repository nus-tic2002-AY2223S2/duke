public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    @Override
    public String toCommand() {
        return getType().name() + " " + description + " /from " + startDateTime + " /to " + endDateTime;
    }

    public TaskType getType(){
        return TaskType.EVENT;
    }
}
