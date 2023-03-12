import java.util.ArrayList;
import java.util.Scanner;

/**
 * A <code>Ui</code> class deals with interactions with the user
 */
public class Ui {

    private TaskList tasks;

    public void welcome(){
        System.out.println("------------------------------------------------------------");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("------------------------------------------------------------");
    }

    public void getUserInput(TaskList tasks){
        Scanner in = new Scanner(System.in);
        while(true) {
            String command = in.nextLine().trim();
            CommandType commandType = Parser.getCommandType(command);
            switch (commandType) {
                case EXIT:
                    //Exit program
                    Ui.exitCommand();
                    break;
                case LIST:
                    //Print list
                    Ui.printList(tasks);
                    break;
                case MARK:
                case UNMARK:
                    //Mark or Unmark Task
                    Parser.markTask(tasks, command, commandType);
                    break;
                case DELETE:
                    //Delete Task
                    Parser.deleteTask(tasks, command);
                    break;
                case ADD:
                    //Create and add task to list
                    Parser.addTask(tasks, command);
                    break;
                default:
                    break;
            }
        }
    }

    public static void exitCommand() {
        printCommand("Bye. Hope to see you again soon!");
    }

    public static void printCommand(String command) {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t" + command);
        System.out.println("------------------------------------------------------------");
    }

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

    public static String getDeletedTaskString(TaskList taskList, Task task) {
        String addedTask = "Noted. I've removed this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.getSize() + " tasks in the list.";
        return addedTask;
    }


    public static String getNewTaskString(TaskList taskList, Task task) {
        String addedTask = "Got it. I've added this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.getSize() + " tasks in the list.";
        return addedTask;
    }
}
