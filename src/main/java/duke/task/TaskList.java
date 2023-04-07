/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.task;

import duke.Todo;
import duke.Deadline;
import duke.Event;
import duke.DukeException;
import duke.Task;

import java.util.ArrayList;

public class TaskList {
    /**
     *  Attribute
     */
    private ArrayList<Task> list; //string initialize Task arraylist

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
        for(int i = 0; i < this.list.size(); i++) {
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
    public Task addNewTask(Task newTask) throws DukeException{
        if (checkDuplicate(newTask)) {
            throw new DukeException("☹ OOPS!!! This already task exists");
        }
        else {
            this.list.add(newTask);
            System.out.println("Got it. I've added this task:\n" + newTask.toString());
            System.out.println("Now you have " + this.getSizeOfList() + " task(s) in the list");
            return newTask;
        }
    }

    /**
     *  this method will take in an index & remove an existing Tasking from the current list
     */
    public Task deleteTask(int indexToBeDeleted) throws DukeException {
        if(indexToBeDeleted > (this.getSizeOfList()-1)) {
            throw new DukeException("☹ OOPS!!! The value after delete is over the size of the list.");
        }
        else {
            Task deletedItem = getElementFromList(indexToBeDeleted);
            Task item = this.list.remove(indexToBeDeleted);
            System.out.println("Noted. I've removed this task:\n" + deletedItem);
            System.out.println("Now you have " + this.getSizeOfList() + " task(s) in the list");
            return deletedItem;
        }
    }

    /**
     *  This method will retrieve the item to mark it
     */
    public void markTask(int indexToBeMarked) throws DukeException {
        if(indexToBeMarked > (this.getSizeOfList()-1)) {
            throw new DukeException("☹ OOPS!!! The value after mark is over the size of the list.");
        }
        else {
            this.list.get(indexToBeMarked).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.list.get(indexToBeMarked).toString());
        }
    }

    /**
     *  This method will retrieve the item to unmark it
     */
    public void unmarkTask(int indexToBeUnmarked) throws DukeException {

        if(indexToBeUnmarked > (this.getSizeOfList()-1)) {
            throw new DukeException("☹ OOPS!!! The value after unmark is over the size of the list.");
        }
        else {
            this.list.get(indexToBeUnmarked).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.list.get(indexToBeUnmarked).toString());
        }
    }

    /**
     *  This method will search task by keyword and display matching tasks
     */
    public void findItemInList(String searchKeyword) {
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < this.getSizeOfList(); i++) {
            if(this.list.get(i).getDescription().contains(searchKeyword)) {
                System.out.println(i+1 + "." + this.list.get(i).toString());
            }
        }
    }

    /**
     *  This method will search the list to check for duplicates
     * @return true if duplicate exists
     * @return false if no duplicates
     */
    public boolean checkDuplicate(Task newTask)
    {
        boolean counter = false;
        for(int i = 0; i < this.getSizeOfList(); i++) {
            Task currItem = this.getElementFromList(i);
            if(newTask.getClass().equals(currItem.getClass())) {
                if(newTask instanceof Todo)
                {
                    if(newTask.getDescription().equalsIgnoreCase(currItem.getDescription())) {
                        return true;
                    }
                }
                else if(newTask instanceof Deadline)
                {
                    if(newTask.getDescription().equalsIgnoreCase(currItem.getDescription()) && ((Deadline) newTask).getBy().equalsIgnoreCase(((Deadline) currItem).getBy())) {
                        return true;
                    }
                }
                else {
                    if(newTask.getDescription().equalsIgnoreCase(currItem.getDescription()) && ((Event) newTask).getStart().equalsIgnoreCase(((Event) currItem).getStart()) && ((Event) newTask).getEnd().equalsIgnoreCase(((Event) currItem).getEnd())) {
                        return true;
                    }
                }
            }
        }
        return counter;
    }


}
