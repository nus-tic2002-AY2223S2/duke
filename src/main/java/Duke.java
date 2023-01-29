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
        String[] list = new String[100];

        String command;
        Scanner in = new Scanner(System.in);
        while(true) {
            command = in.nextLine().trim();

            if (command.isEmpty()){
                continue;
            }else if (command.equalsIgnoreCase("bye")) {
                //Exit program
                exitCommand();
                break;
            }else if (command.equalsIgnoreCase("list")){
                //Print list
                printList(list, count);
                continue;
            }

            //Add to list
            list[count] = command;
            count++;
            printCommand("added: " + command);

        }
    }

    public static void printList(String[] list, int count) {
        String printStr = "";
        for (int i = 0; i < count; i++) {
            String index = Integer.toString(i + 1);
            printStr +=  index + "." + list[i] + "\n\t";
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
