import java.util.Scanner;

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

        String command;
        Scanner in = new Scanner(System.in);
        while(true) {
            command = in.nextLine().trim();
            if (command.equalsIgnoreCase("bye")) {
                exitCommand();
                break;
            }
            printCommand(command);
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
}
