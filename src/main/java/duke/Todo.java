package duke;
public class Todo extends Task {
    //protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }



    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}