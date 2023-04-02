package duke.task;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.parser.Parser.getTask;

/**
 * A <code>duke.task.TaskList</code> class contains the task list
 * e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private final ArrayList<Task> tasklist = new ArrayList<>();

    public TaskList(Scanner s) {
        while (s.hasNext()) {
            Task task = getTask(s.nextLine());
            this.tasklist.add(task);
        }
    }

    public TaskList() {

    }

    public ArrayList<Task> getItems() {
        return tasklist;
    }

    public Task getItem(int index) {
        return tasklist.get(index);
    }

    public Task removeItem(int index) {
        return tasklist.remove(index);
    }

    public int getSize() {
        return tasklist.size();
    }

    public void addItem(Task task) {
        tasklist.add(task);
    }

    /**
     * This method create the task list in a format to be saved in file
     *
     * @return A formatted String of tasks
     */
    public String getTaskListString() {
        String printStr = "";
        for (int i = 0; i < tasklist.size(); i++) {
            Task item = tasklist.get(i);
            printStr += item.toCommand() + "@" + item.isDone() + System.lineSeparator();
        }
        return printStr;
    }

    /**
     * This method create the task in a format to be saved in file
     *
     * @return A formatted String of task
     */
    public String getTaskString(int index) {
        Task task = tasklist.get(index);
        String printStr = task.toCommand() + "@" + task.isDone();
        return printStr;
    }

}
