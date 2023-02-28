import java.util.ArrayList;
import java.util.Scanner;

public class DukeInit {
    public static void duke(){
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
    }
    public static String inputCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static void printList(ArrayList<Task> lists) {
        String space = " ", line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(space + "Here are the tasks in your list:");
        int taskNum = 0;
        for(Task list : lists){
            taskNum ++;
            System.out.println(space + taskNum + "." + list.toString());
        }
        System.out.println(line);
    }

    public static void Echo(String s) {
        String space = " ", line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(space + s);
        System.out.println(line);
    }

    public static String keyCommand(String inputCommand){
        return inputCommand.split(" ",2)[0].trim();
    }

    public static String restCommand(String inputCommand){
        return inputCommand.split(" ",2)[1].trim();
    }

    public static int commandLength(String inputCommand){
        return inputCommand.split(" ",2).length;
    }

}
