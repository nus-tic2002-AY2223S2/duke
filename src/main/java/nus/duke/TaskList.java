package nus.duke;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
* represent a sequence of tasks that allows users to add task (to-do, deadline, events) and delete task,
* retrieve the certain task, retrieve the taskList size and  view the list of tasks.
 */
public class TaskList {

    protected static ArrayList<Task> tasksArray;
    protected int size = 0;


    public TaskList(ArrayList t) {
        this.tasksArray = new ArrayList<>((Collection) t);
        this.size = tasksArray.size();
    }


    public int getSize() {
        return size;
    }

    public TaskList() {

    }

    public void add(ToDo todo) {
        tasksArray.add(todo);
        size++;
    }

    public void add(Deadline deadline) {
        tasksArray.add(deadline);
        size++;
    }

    public void add(Event event) {
        tasksArray.add(event);
        size++;
    }

    public static Task get(int taskNumber) {
        return tasksArray.get(taskNumber);
    }

    public void remove(int i) {
        tasksArray.remove(tasksArray.get(i));
        size--;
    }

}

