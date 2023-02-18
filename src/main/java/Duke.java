import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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

        String command, keyCommand, restCommand="";
        List<Task> lists = new ArrayList<>();

        whileLoop: while (true){
            command = inputCommand().trim();
            Task taskInList = new Task(command);
            try{
                validateInputCommand(command);
            }
            catch (DukeException e){
                Echo(e.getMessage());
                continue ;
            }

            keyCommand = command.split(" ",2)[0];

            switch (keyCommand){
                case "bye":
                    Echo("Bye. Hope to see you again soon!");
                    break whileLoop;
                case "list":
                    printList(lists);
                    break;
                case "mark":
                    restCommand = command.split(" ",2)[1];
                    try {
                        taskInList = lists.get(Integer.parseInt(restCommand) - 1);
                        taskInList.Mark();
                        Echo("Nice! I've marked this task as done: \n" + space + space + taskInList);
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! Please key into correct number in the list!" );
                    }
                    break;
                case "unmark":
                    restCommand = command.split(" ",2)[1];
                    try{
                        taskInList = lists.get(Integer.parseInt(restCommand) - 1);
                        taskInList.unMark();
                        Echo("OK, I've marked this task as not done yet: \n" + space + space + taskInList);
                    }
                    catch(IndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! Please key into correct number!" );
                    }
                    break;
                case "todo":
                    restCommand = command.split(" ",2)[1];
                    Task todo = new Todo(restCommand);
                    lists.add(todo);
                    Echo("Got it. I've added this task:\n"+ space + space + todo
                            + "\n Now you have " + lists.size() + " tasks in the list.");
                    break;
                case "deadline":
                    restCommand = command.split(" ",2)[1];
                    String beforeBy,afterBy;
                    beforeBy = restCommand.split("/by")[0];
                    afterBy = restCommand.split("/by")[1];
                    Task deadline = new Deadline(beforeBy,afterBy);
                    lists.add(deadline);
                    Echo("Got it. I've added this task:\n"+ space + space + deadline
                            + "\n Now you have " + lists.size() + " tasks in the list.");
                    break;
                case "event":
                    restCommand = command.split(" ",2)[1];
                    String beforeAt,afterAt;
                    beforeAt = restCommand.split("/at",2)[0];
                    afterAt = restCommand.split("/at",2)[1];
                    Task event = new Event(beforeAt,afterAt);
                    lists.add(event);
                    Echo("Got it. I've added this task:\n"+ space + space + event
                            + "\n Now you have " + lists.size() + " tasks in the list.");
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
    private static void validateInputCommand(String inputCommand) throws DukeException{
        String keyInput;
        keyInput = inputCommand.split(" ",2)[0];
        switch (keyInput) {
            case "list":
            case "bye":
                return;
            case "mark":
                if(inputCommand.split(" ",2).length < 2){
                    throw new DukeException("☹ OOPS!!! Mark number can not be empty.");
                }
                break;
            case "unmark":
                if(inputCommand.split(" ",2).length < 2){
                    throw new DukeException("☹ OOPS!!! Unmark number can not be empty.");
                }
                break;
            case "deadline":
                if(inputCommand.split(" ",2).length < 2){
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                break;
            case "todo":
                if(inputCommand.split(" ",2).length < 2){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "event":
                if(inputCommand.split(" ",2).length < 2){
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
