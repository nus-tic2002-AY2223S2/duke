public class Event extends Task{
    protected String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + ")";
    }
}
