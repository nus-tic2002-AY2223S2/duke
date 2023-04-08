package task;

/**
 * task
 * -> to mark/unmark certain tasks and returns specific messages
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void notMark() {
        isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String saveStatusIcon() {
        if (isDone) {
            return "| 1 |";
        } else {
            return "| 0 |";
        }
    }

    //https://www.freecodecamp.org/news/java-array-methods-how-to-print-an-array-in-java/
    @Override
    public String toString(){
        return getStatusIcon() + " " + description;
    }


    public String toSave(){
        return saveStatusIcon() + " " + description;
    }

}
