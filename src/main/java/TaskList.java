
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String description, boolean status) {
        Task task = new Task(description);
        this.tasks.add(task);

        if (task instanceof Deadline){
            Deadline deadlineTask = (Deadline) tasks;
            System.out.println(deadlineTask.getTaskType());
        }else if (task instanceof Event){
            Event eventTask = (Event) tasks;
            System.out.println(eventTask.getTaskType());
        }else if(task instanceof ToDo){
            ToDo toDoTask = (ToDo) tasks;
            System.out.println(toDoTask.getTaskType());

        }
    }

}
