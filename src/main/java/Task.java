public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    //Getter Methods
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Setter Methods
    public void setStatusIconMarked() {
       isDone = true;
    }

    public void setStatusIconUnmarked() {

        isDone = false;
    }
}