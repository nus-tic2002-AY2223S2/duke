package duke;

import java.util.ArrayList;
import duke.Ui.Ui;
public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {
        /**
         *empty constructor
         */
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println();
    }

    public void deleteTask(int IndexToDelete) {
        Task toDelete = getTask(IndexToDelete);
        list.remove(IndexToDelete);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete);
    }
    public Task getTask(int index) {
        return list.get(index);
    }

    public void listTask() {
        Ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        Ui.showLine();
    }

}
