package function;

public class Event extends Task {
    private final String start_time;
    private final String end_time;
    public Event(String description, String start_time, String end_time) {
        super(description);
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String toString() {
        return "\t [E]" + super.toString() + " (From: " + this.start_time + " to " + this.end_time + ")";
    }
    public String getTaskType() {
        return "E";
    }
    public String getTaskStartTime() {
        return this.start_time;
    }
    public String getTaskEndTime() {
        return this.end_time;
    }
}
