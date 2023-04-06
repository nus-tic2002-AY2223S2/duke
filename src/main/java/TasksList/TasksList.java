package taskslist;

import task.*;
import ui.Ui;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TasksList {
    public static ArrayList<Task> tasksList;

    public TasksList(){
        this.tasksList = new ArrayList<>();
    }

    public static ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Adds all tasks from user input into tasksList
     *
     * @param task a Task object
     */
    public void taskAdd(Task task){
        String space = " ";
        Ui.showLine();
        int taskNum = 0;
        for (Task tasks : tasksList) {
            if(task.getDescription().equals(tasks.getDescription())){
                taskNum++;
                System.out.println(space + "The task being added clashes with another task in the list.");
                break;
            }
        }
        if(taskNum == 0){
            tasksList.add(task);
            Ui.Echo("Got it. I've added this task: \n  " + task +
                    "\n Now you have " + tasksList.size() + " tasks in the list.");
        }
        Ui.showLine();
    }

    /**
     * Loads all tasks from disk file and add into tasksList
     *
     * @param task a Task object
     */
    public void loadTask(Task task){
        tasksList.add(task);
    }
    /**
     * Marks task from taskList
     * @param index , mark a specified task number
     */
    public void mark(int index){
        Task task = tasksList.get(index);
        task.Mark();
        Ui.Echo("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * unMarks task from taskList
     * @param index , unmark a specified task number
     */
    public void unmark(int index){
        Task task = tasksList.get(index);
        task.unMark();
        Ui.Echo("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Deletes task from taskList
     * @param index , delete a specified task number
     */
    public void deleteTask(int index){
        Task task = tasksList.get(index);
        tasksList.remove(index);
        Ui.Echo("Noted. I've removed this task:\n  " + task +
                "\n Now you have " + tasksList.size() + " tasks in the list.");
    }

    /**
     * All tasks in the tasks list have been keyed in by user
     * Saved in disk file.
     *
     * @return String, as a parameter to writeToFile method
     */
    public String tasksAddInFile(){
        StringBuilder content = new StringBuilder();
        for(Task tasks : tasksList){
            content.append(tasks.contentToFile());
        }
        return content.toString();
    }

    /**
     * Parses and read all tasks(String) from disk file to Task object
     *
     * @param nextLine each line of tasks saved in disk file
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
                task = new Deadline(body[2],
                        LocalDateTime.parse(body[3].replace("T"," "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
            case "E":
                task = new Event(body[2],
                        LocalDateTime.parse(body[3].replace("T"," "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse(body[4].replace("T"," "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (body[1].equals("1")) {
                    task.Mark();
                }
                break;
        }
        return task;
    }



    public void findTask(String restCommand){
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Here are the matching tasks in your list:");
        int taskNum = 0;
        for (Task tasks : tasksList) {
            if(tasks.getDescription().contains(restCommand)){
                taskNum++;
                System.out.println(space + taskNum + "." + tasks);
            }
        }
        if(taskNum == 0){
            System.out.println(space + "No tasks found in the tasks list!");
        }
        Ui.showLine();
    }

    public void scheduleView(LocalDateTime scheduleDate){
        String space = " ";
        Ui.showLine();
        int taskNum = 0;
        for (Task tasks : tasksList) {
            if(scheduleDate.equals(tasks.getStartData()) || scheduleDate.equals(tasks.getEndData())){
                taskNum++;
                System.out.println(space + taskNum + "." + tasks);
            }
        }
        if(taskNum == 0){
            System.out.println(space + "No tasks found in the form of a schedule!");
        }
        Ui.showLine();
    }

    /**
     * Prints and show all tasks in the list to User
     */
    public static void showToUser() {
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Here are the tasks in your list:");
        int taskNum = 0;
        for (Task tasks : tasksList) {
            taskNum++;
            System.out.println(space + taskNum + "." + tasks);
        }
        Ui.showLine();
    }

}

