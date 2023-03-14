public class ToDo extends Task {

    protected String taskType;

    public ToDo(String description, String taskType) {
        super(description);
        this.taskType = taskType;
    }

    //Getter Methods
    public String getTaskType() {
        return taskType;
    }
}
