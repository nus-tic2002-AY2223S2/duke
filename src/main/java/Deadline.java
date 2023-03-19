public class Deadline extends Task {
    protected String taskString;
    protected String byString;

    public Deadline(String description, String deadline) {
        super(description);
        this.byString = deadline;
    }
    public String getBy() {
        return this.byString;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by:" + byString + ")";
    }

}
