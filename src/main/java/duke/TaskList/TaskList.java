package duke.TaskList;

import java.util.ArrayList;
import duke.TasksType.Task;
import duke.Ui.Ui;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {
        /**
         *empty constructor
         */
    }
    public void storeList(ArrayList<Task> list) {
        this.list = list;
    }
    public void addTask(Task task) {
        list.add(task);
        assert list.size() > 0 : "ArrayList should have task inside";
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
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
    public ArrayList getList() {
        return list;
    }

    public void listTask() {
        Ui.showLine();
        System.out.println("Sorted from High to Low priority...");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        Ui.showLine();
    }
    public void setPriority(int index, Task.priorityLevel p) {
        getTask(index).changePriority(p);
        System.out.println("Noted, item " + (index + 1) +  " (" + getTask(index).getDescription() + ") priority has been set to " + p);
    }
    public void sortList() {

        ArrayList<Task> sortedList = new ArrayList<>();
        ArrayList<Task> high = new ArrayList<>();
        ArrayList<Task> low = new ArrayList<>();
        ArrayList<Task> medium = new ArrayList<>();
        for (Task t: list) {
            if (t.getPriorityLevel().equals(Task.priorityLevel.High)) {
                high.add(t);
            }
            else if (t.getPriorityLevel().equals(Task.priorityLevel.Low)) {
                low.add(t);
            }
            else {
                medium.add(t);
            }
        }
        sortedList.addAll(high);
        sortedList.addAll(medium);
        sortedList.addAll(low);
        list = sortedList;
    }

    public void find (String word) {
        Ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : list) {
            if (t.getDescription().contains(word)) {
                System.out.println(t);
            }
        }
        Ui.showLine();
    }
}
