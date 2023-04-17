public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markDone(int i){
        this.isDone =true;
    }

    public void unMark(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() +"]" +this.getDescription();
    }

}