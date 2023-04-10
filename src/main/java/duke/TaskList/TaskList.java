package duke.TaskList;

import java.util.ArrayList;

import duke.DukeException;
import duke.TasksType.Task;
import duke.Ui.Ui;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList() {
        //empty constructor
    }

    /**
     * This method stores an ArrayList of Tasks into another list
     * @param list takes in an ArrayList of Tasks
     */
    public void storeList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * This method adds a task into the ArrayList in accordance to the task type
     * asserts that the ArrayList should not be empty or no elements inside after a Task is added
     * @param task takes in a Task input and add into the ArrayList
     */
    public void addTask(Task task) {
        list.add(task);
        assert list.size() > 0 : "ArrayList should have task inside";
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " task(s) in your list.");
    }

    /**
     * This method deletes a specific task indicated by the user when a task is no longer useful or relevant
     * @param IndexToDelete takes in an integer input and remove the task in the ArrayList according to the index
     */
    public void deleteTask(int IndexToDelete) {
        Task toDelete = getTask(IndexToDelete);
        list.remove(IndexToDelete);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete);
    }

    /**
     * This method is called to retrieve a particular task with a given index
     * @param index takes in an integer input to locate the element in the ArrayList
     * @return the Task from the ArrayList
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * This method is called to simply retrieve the entire list of tasks
     * @return an ArrayList of tasks
     */
    public ArrayList getList() {
        return list;
    }

    /**
     * This method is called to display all the items in the ArrayList
     * called toString() methods of every different task type
     */
    public void listTask() {
        Ui.showLine();
        System.out.println("Sorted from High to Low priority...");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        Ui.showLine();
    }

    /**
     * This method is called to change priorityLevel of a specific task with the given index
     * @param index takes in an integer given by user to indicate the specific task
     * @param p takes in a priorityLevel to indicate the level that the user wants to change to
     */
    public void setPriority(int index, Task.priorityLevel p) {
//        if (index < 0 || index >= taskList.size()) {
//            throw new DukeException("☹ OOPS!!! The task index provided is invalid.");
//        } else if (p == null) {
//            throw new DukeException("☹ OOPS!!! Please specify a priority level for the task.");
//        } else {
            getTask(index).changePriority(p);
            System.out.println("Noted, item " + (index + 1) + " (" + getTask(index).getDescription() + ") priority has been set to " + p);
            System.out.println(index + 1 + ". " + list.get(index).toString());
            Ui.showLine();
//        }
    }

    /**
     * This method basically sorts the list according the priorityLevel
     * Elements with High will be at the front of the Arraylist while Low will be at the back
     * Since there are only 3 levels, the sorting is done by splitting into three different ArrayList and combining them again
     */
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

    /**
     * This method loops through the ArrayList and prints out the every element that is related to the string provided by user
     * @param word takes in a string word to perform search every item using contains() method
     */
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
