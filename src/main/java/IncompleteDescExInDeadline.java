public class IncompleteDescExInDeadline extends IncompleteDescriptionException{

    private String msg;
    public IncompleteDescExInDeadline(String msg) {
        super(msg);
    }

    public String toString() {
        return ("cannot give more than 2 deadline (/by)");
    }
}
