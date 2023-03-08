public class Task {
    protected String description;
    protected boolean isDone;
    public Task(){

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }



    //Getters
    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getTaskType() {
        return " ";
    }
    public String getBy() {
        return " ";
    }

    //Setters
    public void setStatusAsMarked() {
        System.out.println("Nice! I've marked this task as done:");

        isDone = true;
    }
    public void setStatusAsUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");

        isDone = false;
    }

    public void setTaskType(String TaskType) {

    }

    public String toString(){
        return String.format("[" + this.getTaskType() + "]" + "[" + this.getStatus() + "] " + description);
    }


}