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
    private ArrayList<Task> list; //string initialize duke.Task arraylist

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
    }

    /**
     *  This method will print all the elements in the list
     */
    public void printTaskList(Task newTask) {

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
        return newTask;
    }

    /**
     *  this method will remove an existing Tasking into the current list
     */
    public Task deleteTask(Task newTask) {
        return newTask;
    }

}
