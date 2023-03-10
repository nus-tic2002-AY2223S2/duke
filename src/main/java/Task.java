public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void Mark(){
        this.isDone = true;
    }

    public void unMark(){
        this.isDone = false;
    }

    // [X] return book
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}
