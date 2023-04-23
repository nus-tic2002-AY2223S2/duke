package tasklist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import task.Task;

/**
 * Represents a list of tasks with various operations.
 */
public class TaskList implements Iterable<Task>{
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task markAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkAsDone(int index) {
        Task task = tasks.get(index);
        task.unmarkAsDone();
        return task;
    }

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}