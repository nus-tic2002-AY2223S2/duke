public class Deadline extends Task{
    protected String afterBy;
    public Deadline(String description,String word) {
        super(description);
        this.afterBy = word;
    }
    public String toString(){
        return "[D]" + super.toString() + "(by:" + afterBy + ")";
    }
}
