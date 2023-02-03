public class Events extends Task{
    protected String taskType;
    protected String from;
    protected String to;
    public Events(String description, String taskType, String from, String to) {
        super(description);
        this.taskType = taskType;
        this.from = from;
        this.to = to;
    }


    //Getter Methods
    public String getTaskType() {
        return taskType;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    public String toString(){
        return super.toString() + "(from: " + from + " to: " + to +")";
    }
}
