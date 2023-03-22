public class ToDo extends Task {

    protected String[] taskInDescription;
    protected String taskString;

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
