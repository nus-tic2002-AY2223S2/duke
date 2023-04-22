package duke.util;

import java.util.ArrayList;
import duke.task.Task;
import duke.exceptions.DukeException;
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

    public Task deleteTask(int index) throws DukeException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index is out of range.");
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }
    public Task markDone(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index is out of range.");
        }
    }
    public Task markUndone(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index is out of range.");
        }
    }
}
