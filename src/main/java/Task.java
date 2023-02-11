public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription(){
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        isDone=true;
        System.out.println("Awesome! The task has been marked done!\n");
        //System.out.println("["+ getStatusIcon()+"] "+ description+"\n");
    }

    public void markAsUndone(){
        isDone=false;
        System.out.println("Alright! The task has been marked Undone!\n");
        //System.out.println("[ ] "+ description+"\n");
    }

    @Override
    public String toString() {

        return "["+ getStatusIcon()+"] "+ description;
    }
}
