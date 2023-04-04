/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.task;

import duke.Task;

import java.util.ArrayList;

public class TaskList {
    /**
     *  Attribute
     */
    private static ArrayList<Task> list; //string initialize duke.Task arraylist

    /**
     *  Constructor [with inputs]
     */
    public TaskList(ArrayList<Task> inputList) {
        this.list = inputList;
    }

    /**
     *  Constructor 2 [w/o inputs]
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     *  This method will print all the elements in the list
     */
    public void printTaskList(Task newTask) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++)
        {
            System.out.println(i+1 + "." + this.list.get(i).toString());
        }
    }

    /**
     *  This method will add a new Tasking into the current list
     */
    public Task getElementFromList(int eleIndex) {
        Task item = this.list.get(eleIndex);
        return item;
    }

    /**
     *  This method will add a new Tasking into the current list
     */
    public Task addNewTask(Task newTask) {
        this.list.add(newTask);
        System.out.println("Got it. I've added this task:\n" + newTask.toString());
        return newTask;
    }

    /**
     *  this method will take in an index & remove an existing Tasking from the current list
     */
    public Task deleteTask(int indexToBeDeleted) {
        Task deletedItem = getElementFromList(indexToBeDeleted);
        Task item = this.list.remove(indexToBeDeleted);
        System.out.println("Noted. I've removed this task:\n" + deletedItem);
        return deletedItem;
    }

}
