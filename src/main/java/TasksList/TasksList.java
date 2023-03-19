package TasksList;

import Task.*;
import UI.Ui;
import java.util.ArrayList;

public class TasksList {
    public ArrayList<Task> tasksList;

    public TasksList(){
        this.tasksList = new ArrayList<>();
    }

    /**
     * To add all tasks from file into tasksList
     *
     * @param task a Task object
     */
    public void loadTask(Task task){
        tasksList.add(task);
    }

    /**
     * To add all tasks from user input into tasksList
     *
     * @param task a Task object
     */
    public void taskAdd(Task task){
        tasksList.add(task);
        Ui.Echo("Got it. I've added this task: \n  " + task +
                "\n Now you have " + tasksList.size() + " tasks in the list.");
    }
    /**
     * Mark task number get from user input
     *
     * @param index a specified task number
     */
    public void mark(int index){
        Task task = tasksList.get(index);
        task.Mark();
        Ui.Echo("Nice! I've marked this task as done:\n  " + task);
    }

    public void unmark(int index){
        Task task = tasksList.get(index);
        task.unMark();
        Ui.Echo("OK, I've marked this task as not done yet:\n  " + task);
    }

    public void deleteTask(int index){
        Task task = tasksList.get(index);
        tasksList.remove(index);
        Ui.Echo("Noted. I've removed this task:\n  " + task +
                "\n Now you have " + tasksList.size() + " tasks in the list.");
    }

    // To write tasks to file
    // @return text to save to file
    public String tasksAddInFile(){
        StringBuilder content = new StringBuilder();
        for(Task task : tasksList){
            content.append(task.contentToFile());
        }
        return content.toString();
    }

    /**
     * Parse all tasks from file to TasksList object
     *
     * @param nextLine each line of task
     * @return a Task object
     */

    public static Task taskByLine(String nextLine) {
        String[] body = nextLine.split(" \\| ");
        Task task = null;
        switch (body[0]) {
            case "T":
                task = new Todo(body[2]);
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
            case "D":
                task = new Deadline(body[2], body[3]);
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
            case "E":
                task = new Event(body[2], body[3]);
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
        }
        return task;
    }

}

