public class Task {
    protected String description;
    protected boolean isDone;

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

    //https://www.freecodecamp.org/news/java-array-methods-how-to-print-an-array-in-java/
    @Override
    public String toString(){
        return getStatusIcon() + " " + description;
    }


}
