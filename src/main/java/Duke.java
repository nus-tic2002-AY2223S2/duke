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

        String command, splitCommand, restCommand;
        List<Task> lists = new ArrayList<>();

        whileLoop: while (true){
            command = inputCommand();
            Task taskInList = new Task(command);
            splitCommand = command.split(" ",2)[0];
            //System.out.println("splitCommand: " + splitCommand);
            switch (splitCommand){
                case "bye":
                    Echo("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    printList(lists);
                    break;
                case "mark": //mark 2
                    restCommand = command.split(" ",2)[1];
                    taskInList = lists.get(Integer.parseInt(restCommand) - 1);
                    //System.out.println("Before mark task: " + taskInList);
                    taskInList.Mark();
                    //System.out.println("After mark task: " + taskInList);
                    Echo("Nice! I've marked this task as done: \n" + space + space + taskInList);
                    break;
                case "unmark":
                    restCommand = command.split(" ",2)[1];
                    taskInList = lists.get(Integer.parseInt(restCommand) - 1);
                    System.out.println("task: " + taskInList);
                    taskInList.unMark();
                    Echo("OK, I've marked this task as not done yet: \n" + space + space + taskInList);
                    break;
                default:
                    lists.add(taskInList);
                    Echo("added: " + command);
            }
        }
    }

    private static void printList(List<Task> lists) {
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
