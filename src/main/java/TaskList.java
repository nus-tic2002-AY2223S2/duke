import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasksArray;

    public TaskList(ArrayList A){
        for (Object i: A) {
            this.tasksArray.add((Task) i);
        }
    }

}
