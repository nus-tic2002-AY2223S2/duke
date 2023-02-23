public class Task {
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;

    }

    public enum task {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public Task() {
    }

    //get method
    public String getStatusIcon(){
        return (isDone ? "X" : " "); //mark done task with X
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }



}
