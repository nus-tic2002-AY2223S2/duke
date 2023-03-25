package duke.ui;

import duke.task.TaskList;
import duke.task.Task;
import java.util.Scanner;

/**
 * A <code>duke.ui.Ui</code> class deals with interactions with the user
 */
public class Ui {

    private TaskList tasks;

    /**
     * This method shows welcome message to user.
     *
     */
    public void welcome(){
        System.out.println("------------------------------------------------------------");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("\tHello! I'm duke.Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * This method reads user input.
     *
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        return command;
    }

    /**
     * This method prints exit message for user.
     *
     */
    public static void exitCommand() {
        printCommand("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints custom message for user.
     *
     * @param command A String representing custom messages
     */
    public static void printCommand(String command) {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t" + command);
        System.out.println("------------------------------------------------------------");
    }


    /**
     * This method prints list of tasks.
     *
     * @param taskList A TaskList object representing the list of tasks.
     */
    public static void printList(TaskList taskList) {
        String printStr = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            String index = Integer.toString(i + 1);
            printStr +=  index + "." + taskList.getItem(i) + System.lineSeparator() + "\t";
        }

        if (taskList.getSize() > 0){
            printCommand(printStr);
        }else {
            printCommand("You have 0 tasks in the list.");
        }
    }

    /**
     * This method prints deleted task and number of tasks left in the task list.
     *
     * @param taskList A TaskList object representing the list of tasks.
     * @param task A Task object representing the deleted task.
     */
    public static String getDeletedTaskString(TaskList taskList, Task task) {
        String addedTask = "Noted. I've removed this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.getSize() + " tasks in the list.";
        return addedTask;
    }


    /**
     * This method prints new task added and number of tasks in the task list.
     *
     * @param taskList A TaskList object representing the list of tasks.
     * @param task A Task object representing the new task.
     */
    public static String getNewTaskString(TaskList taskList, Task task) {
        String addedTask = "Got it. I've added this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.getSize() + " tasks in the list.";
        return addedTask;
    }
}
