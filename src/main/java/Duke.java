import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String space = " ", line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(space + "Hello! I'm Duke\n" + space +"What can I do for you?");
        System.out.println(line);

        String command;
        List<String> lists = new ArrayList<>();
        whileLoop: while (true){
            command = inputCommand();
            switch (command){
                case "bye":
                    Echo("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    printList(lists);
                    break;
                default:
                    lists.add(command);
                    Echo("added: " + command);
            }
        }
    }

    private static void printList(List<String> lists) {
        String space = " ", line = "____________________________________________________________";
        System.out.println(line);
        int taskNum = 0;
        for(String list : lists){
            taskNum ++;
            System.out.println(space + taskNum + ". " + list);
        }
        System.out.println(line);
    }

    private static void Echo(String s) {
        String space = " ", line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(space + s);
        System.out.println(line);
    }

    private static String inputCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
