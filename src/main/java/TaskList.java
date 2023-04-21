import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final int length;
    private final boolean isEmpty;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.length = 0;
        this.isEmpty = true;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = tasks.size();
        this.isEmpty = false;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {

        return this.tasks.remove(index);

    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task markDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        return task;

    }

    public Task markUndone(int index) {
        Task task = this.tasks.get(index);
        task.markAsUndone();
        return task;

    }
}
