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
                    taskInList.unMark();
                    Echo("OK, I've marked this task as not done yet: \n" + space + space + taskInList);
                    break;
                case "todo": //todo borrow book
                    restCommand = command.split(" ",2)[1];
                    Todo todo = new Todo(restCommand);
                    lists.add(todo);
                    Echo("Got it. I've added this task: \n" + space + space + todo + "\n"
                    + " Now you have "+ lists.size() +" tasks in the list.");
                    break;
                case "deadline": //deadline return book /by Sunday
                                 //[D][ ] return book (by: Sunday)
                    restCommand = command.split(" ",2)[1];
                    int dividerPosition = restCommand.indexOf("/");
                    String beforeBy = restCommand.substring(0, dividerPosition);
                    String afterBy = restCommand.substring(dividerPosition + 4);

                    Deadline deadline = new Deadline(beforeBy, afterBy);
                    lists.add(deadline);
                    Echo("Got it. I've added this task: \n" + space + space + deadline + "\n"
                            + " Now you have "+ lists.size() +" tasks in the list.");
                    break;
                case "event": //event project meeting /at Mon 2-4pm
                    //[E][ ] project meeting (at: Mon 2-4pm)
                    restCommand = command.split(" ",2)[1];
                    int dividerPos = restCommand.indexOf("/");
                    String beforeAt = restCommand.substring(0, dividerPos);
                    String afterAt = restCommand.substring(dividerPos + 4);

                    Event event = new Event(beforeAt, afterAt);
                    lists.add(event);
                    Echo("Got it. I've added this task: \n" + space + space + event + "\n"
                            + " Now you have "+ lists.size() +" tasks in the list.");
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
