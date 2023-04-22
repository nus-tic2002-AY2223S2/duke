package Duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isPriority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getIsDone() {
        return (isDone ? "X" : " "); 
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isPriority() {
        return isPriority;
    }
    public void setPriority(boolean priority) {
        isPriority = priority;
    }
    public String toString() {
        String isPriority = "";
        if (this.isPriority) {
            isPriority = "High Priority!!";
        }
        return "["+getIsDone()+"] " + getDescription() + isPriority;
    }

    public abstract String toFileString();
}