public class Deadlines extends Task{
    protected String taskType;
    protected String by;
    public Deadlines(String description, String taskType, String by) {
        super(description);
        this.taskType = taskType;
        this.by = by;
    }


    //Getter Methods
    public String getTaskType() {
        return taskType;
    }

    public String getBy() {
        return by;
    }

    public String toString(){
        return super.toString() + "(by: " + by + ")";
    }
}
