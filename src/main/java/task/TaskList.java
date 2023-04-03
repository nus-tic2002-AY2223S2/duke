package task;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import task.Deadline;
import task.ToDo;
import task.Event;
import task.TaskList;

public class TaskList {
    public static ArrayList<Task> taskslist;

    public TaskList(ArrayList<Task> tasks) {
        this.taskslist = tasks;
    }

    public TaskList() {
        taskslist = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks(){
        return taskslist;
    }

    public void getSize() {
        System.out.println(taskslist.size());
    }


    public void addDeadline(String deadLineTask, String lastDay) {
        taskslist.add(new Deadline(deadLineTask, lastDay));
        System.out.println(deadLineTask);
    }

    public void addTodo(String todoTask) {
        taskslist.add(new ToDo(todoTask));
        System.out.println(todoTask);
    }

    public void addEvent(String eventTask, String fromEvent, String toEvent) {
        taskslist.add(new Event(eventTask, fromEvent, toEvent));
        System.out.println(eventTask);
    }

    public void delete(int i) {
        System.out.println(taskslist.get(i));
        taskslist.remove(i);
    }



}
