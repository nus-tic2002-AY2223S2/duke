public class Deadline extends Task {
    protected String taskString;
    protected String byString;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
    }

    public Deadline(String description) {
        super(description);
        this.partsInDescription = description.split("/by");
        this.taskString = partsInDescription[0];
        this.byString = partsInDescription[1];
    }
    public String getBy() {
        return this.byString;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by:" + byString + ")";
    }

}
