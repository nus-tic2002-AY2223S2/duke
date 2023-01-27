public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setStatusIcon(int taskNum) {
        if (this.isDone){
            this.isDone = false;
        }else {
            this.isDone = true;
        }
    }
}