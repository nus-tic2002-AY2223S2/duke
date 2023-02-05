import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
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

        //Store text
        int count = 0;
        Task[] taskList = new Task[100];

        String command;
        Scanner in = new Scanner(System.in);
        while(true) {
            command = in.nextLine().trim();

            if (command.isEmpty()){
                continue;
            }else if (command.equalsIgnoreCase("BYE")) {
                //Exit program
                exitCommand();
                break;
            }else if (command.equalsIgnoreCase("LIST")){
                //Print list
                printList(taskList, count);
                continue;
            }else if (command.toUpperCase().startsWith("MARK") || command.toUpperCase().startsWith("UNMARK")) {
                //Mark or Unmark Task
                command = command.toUpperCase();
                boolean markAsDone = command.startsWith("MARK") ? true : false;
                String numberStr = command.replace("UN", "").replace("MARK", "").trim();
                try{
                    int number = Integer.parseInt(numberStr) - 1;
                    if (number < count) {
                        markOrUnmakrTask(taskList[number], markAsDone);
                    }
                }
                catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                continue;
            }

            //Add to list
            Task task = new Task(command);
            taskList[count] = task;
            count++;
            printCommand("added: " + command);

        }
    }


    public static void markOrUnmakrTask(Task task, boolean markAsDone) {
        String printStr = "";
        if (markAsDone) {
            task.markAsDone();
            printStr = "Nice! I've marked this task as done:";
        }else {
            task.unMarkDone();
            printStr = "OK, I've marked this task as not done yet:";
        }
        printCommand(printStr + System.lineSeparator() + "\t" + task);
    }

    public static void printList(Task[] list, int count) {
        String printStr = "";
        for (int i = 0; i < count; i++) {
            String index = Integer.toString(i + 1);
            printStr +=  index + "." + list[i] + System.lineSeparator() + "\t";
        }
        printCommand(printStr);
    }

    public static void exitCommand() {
        printCommand("Bye. Hope to see you again soon!");
    }

    public static void printCommand(String command) {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t" + command);
        System.out.println("------------------------------------------------------------");
    }
}
