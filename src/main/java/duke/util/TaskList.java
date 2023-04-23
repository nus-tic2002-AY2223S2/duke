package duke.util;

import java.util.ArrayList;
import duke.task.Task;
import duke.exceptions.DukeException;
import java.time.LocalDateTime;

/**
 * The TaskList class represents a list of tasks
 */
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

    /**
     * Adds a task to the task list.
     * @param task the task to be added to the task list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }


    /**
     * Deletes a task from the task list.
     * @param index the index of the task to be deleted
     * @return the task that was deleted
     * @throws DukeException if the index is out of range
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t (>w<)OOPS!!! The index is out of range.");
        }
    }

    /**
     * Get tasks from task list
     * @param index  index the index of the task to be retrieved
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Get the length of task list
     * @return length of task list
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     * @param index the index of the task to be marked as done
     * @return the task that was marked as done
     * @throws DukeException if the index is out of range
     */
    public Task markDone(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t (>w<)OOPS!!! The index is out of range.");
        }
    }

    /**
     * Marks a task as undone.
     * @param index the index of the task to be marked as undone
     * @return the task that was marked as undone
     * @throws DukeException if the index is out of range
     */
    public Task markUndone(int index) throws DukeException {
        try {
            Task task = this.tasks.get(index);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t (>w<)OOPS!!! The index is out of range.");
        }
    }

    /**
     * Find the task include the keyword
     * @param keyword the input keyword
     * @return the task that matches keyword
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Find the tasks before the due date and time
     * @param dueTime the due date and time
     * @return the tasks before the due date and time
     */
    public TaskList tasksDueBefore(LocalDateTime dueTime) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (!task.getTaskType().equals("T") && LocalDateTime.parse(task.getTaskEndTime()).isBefore(dueTime)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks);
    }

}
