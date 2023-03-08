
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task tasks) {
        this.tasks.add(tasks);

        if (tasks instanceof Deadline){
            Deadline deadlineTask = (Deadline) tasks;
            System.out.println(deadlineTask.getTaskType());
        }else if (tasks instanceof Event){
            Event eventTask = (Event) tasks;
            System.out.println(eventTask.getTaskType());
        }else if(tasks instanceof ToDo){
            ToDo toDoTask = (ToDo) tasks;
            System.out.println("TaskList:AddTask Method: " + toDoTask.getTaskType());

        }
    }

}
