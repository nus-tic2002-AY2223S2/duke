public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at=at;
        this.isDone=false;
    }

    public void settime(String at) {
        this.at = at;
    }

    public String gettime() {
        return at;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
