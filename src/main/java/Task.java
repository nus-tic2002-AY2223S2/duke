public class Task {
    protected String description;

    protected String taskString;
    protected String task;
    protected String[] wordsInSentences;
    protected String[] wordsInDescription;
    protected String[] partsInDescription;
    protected boolean isDone;


    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.wordsInSentences = description.split(" ");
        this.wordsInDescription = description.split(" ");
        this.partsInDescription = description.split("/by|/from|/to");

    }

    /*
    // Enumerate the task features
    public enum task {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }
    */

    //get method
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
