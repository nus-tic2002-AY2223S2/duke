import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    ArrayList<String> task = new ArrayList();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        if (isDone) return;
        this.isDone = true;
    }

    public void markUnDone() {
        if (!isDone) return;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return "" + getStatusIcon() + this.description;

    }

}