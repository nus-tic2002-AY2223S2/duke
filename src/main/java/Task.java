public class Task {
    protected String description;
    protected boolean isDone;
    public Task(){

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    //Getter Methods
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTaskType() {
        return " ";
    }
    public String getBy() {
        return " ";
    }
    //Setter Methods
    public void setStatusIconMarked() {
        System.out.println("Nice! I've marked this task as done:");

        isDone = true;
    }

    public void setStatusIconUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");

        isDone = false;
    }

    public String toString(){
        return String.format("[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "] " + description);
    }
}