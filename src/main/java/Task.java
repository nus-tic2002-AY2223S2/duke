public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = null;
    }

    //Getters method
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    //Setters method
    public void setStatusAsMarked() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
    }

    public void setStatusAsUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");
        isDone = false;
    }

    public String getPriority() {
        if (this.priority == null) {
            return " ";
        }
        switch (this.priority) {
            case LOW:
                return "L";
            case MEDIUM:
                return "M";
            case HIGH:
                return "H";
            default:
                return " ";
        }
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    public String toString() {
        return String.format("[" + this.getTaskType() + "]" + "[" + this.getStatus() + "]" + "[" + this.getPriority() + "] " + description);
    }
//

}