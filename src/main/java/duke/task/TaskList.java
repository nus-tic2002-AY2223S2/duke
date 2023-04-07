/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.task;

import duke.DukeException;
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
        this.list = new ArrayList<Task>();
    }

    /**
     *  This method will print all the elements in the list
     */
    public void printTaskList() {
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
     *  This method will return size of currentList
     */
    public int getSizeOfList () {
        return this.list.size();
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
    public Task deleteTask(int indexToBeDeleted) throws DukeException {
        if(indexToBeDeleted > this.list.size()) {
            throw new DukeException("☹ OOPS!!! The value after delete is over the size of the list.");
        }
        else {
            Task deletedItem = getElementFromList(indexToBeDeleted);
            Task item = this.list.remove(indexToBeDeleted);
            System.out.println("Noted. I've removed this task:\n" + deletedItem);
            return deletedItem;
        }
    }

    /**
     *  This method will retrieve the item to mark it
     */
    public void markTask(int indexToBeMarked) throws DukeException {
        if(indexToBeMarked > this.list.size()) {
            throw new DukeException("☹ OOPS!!! The value after mark is over the size of the list.");
        }
        else {
            this.list.get(indexToBeMarked).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.list.get(indexToBeMarked).toString());
        }
    }

    /**
     *  This method will retrieve the item to mark it
     */
    public void unmarkTask(int indexToBeUnmarked) throws DukeException {

        if(indexToBeUnmarked > this.list.size()) {
            throw new DukeException("☹ OOPS!!! The value after unmark is over the size of the list.");
        }
        else {
            this.list.get(indexToBeUnmarked).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.list.get(indexToBeUnmarked).toString());
        }
    }


}
