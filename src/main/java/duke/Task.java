package duke;
import java.time.LocalDate;

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


    }

    public void markAsUndone(){
        isDone=false;
        System.out.println("Alright! The task has been marked Undone!\n");

    }

    @Override
    public String toString() {

        return "["+ getStatusIcon()+"] "+ description;
    }

    public LocalDate toDate(String da){
        LocalDate dat= LocalDate.parse(da);
        return dat;
    }
}
