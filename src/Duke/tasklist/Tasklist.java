package Duke.tasklist;

import Duke.task.Task;
import java.util.List;

public class Tasklist {
    protected List<Task> tasks;

    public Tasklist() {
    }
    public Tasklist(List<Task> tasks) {

        this.tasks = tasks;
    }
    public List<Task> getTasks() {

        return tasks;
    }
    public void setTasks(List<Task> tasks) {

        this.tasks = tasks;
    }
    public void addTasks(Task t) {
        tasks.add(t);
    }
}