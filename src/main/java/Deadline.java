public class Deadline extends Task{

    protected String by;
    //protected boolean isDone;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isDone= false;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
