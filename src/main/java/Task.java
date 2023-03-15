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

    public void setPriority(String p) throws DukeException {
        switch(p){
            case "HIGH":
            case "H":
                priority = Priority.HIGH;
                System.out.println("Priority set as HIGH");
                break;
            case "MEDIUM":
            case "M":
                priority = Priority.MEDIUM;
                System.out.println("Priority set as MEDIUM");
                break;
            case "LOW":
            case "L":
                priority = Priority.LOW;
                System.out.println("Priority set as LOW");
                break;
            case " ":
            case "":
                priority = null;
                break;
            default:
               throw new DukeException("OOPS! Please use either HIGH,MEDIUM,LOW when setting priority");

        }
    }

    public String toString() {
        return String.format("[" + this.getTaskType() + "]" + "[" + this.getStatus() + "]" + "[" + this.getPriority() + "] " + description);
    }

}