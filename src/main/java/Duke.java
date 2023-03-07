import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        //Store task
        ArrayList<Task> taskList = new ArrayList<>();

        Task task;
        String command;
        CommandType commandType;

        Scanner in = new Scanner(System.in);
        while(true) {
            command = in.nextLine().trim();
            commandType = getCommandType(command);

            switch (commandType) {
                case EXIT:
                    //Exit program
                    exitCommand();
                    break;
                case LIST:
                    //Print list
                    printList(taskList);
                    break;
                case MARK:
                case UNMARK:
                    //Mark or Unmark Task
                    markTask(taskList, command, commandType);
                    break;
                case DELETE:
                    //Mark or Unmark Task
                    deleteTask(taskList, command);
                    break;
                case ADD:
                    //Create and add task to list
                    addTask(taskList, command);
                    break;
                default:
                    break;
            }
        }
    }

    private static CommandType getCommandType(String command) {
        if (command.isEmpty()){
            return CommandType.EMPTY;
        }else if (command.equalsIgnoreCase("BYE")) {
            return CommandType.EXIT;
        }else if (command.equalsIgnoreCase("LIST")){
            return CommandType.LIST;
        }else if (command.toUpperCase().startsWith("MARK")) {
            return CommandType.MARK;
        }else if (command.toUpperCase().startsWith("UNMARK")) {
            return CommandType.UNMARK;
        }else if (command.toUpperCase().startsWith("DELETE")) {
            return CommandType.DELETE;
        }else {
            return CommandType.ADD;
        }
    }

    private static void markTask(ArrayList<Task> taskList, String command, CommandType commandType) {
        command = command.toUpperCase();
        boolean markAsDone = commandType == CommandType.MARK ? true : false;
        try{
            String numberStr = command.split(" ")[1].trim();
            int index = Integer.parseInt(numberStr) - 1;
            Task task = taskList.get(index);
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
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException for delete: " + command);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteTask(ArrayList<Task> taskList, String command) {
        try{
            String numberStr = command.split(" ")[1].trim();
            int index = Integer.parseInt(numberStr) - 1;
            Task task = taskList.get(index);
            taskList.remove(index);
            String addedTask = getDeletedTaskString(taskList, task);
            printCommand(addedTask);
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException for task: " + command);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private static String getDeletedTaskString(ArrayList<Task> taskList, Task task) {
        String addedTask = "Noted. I've removed this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.size() + " tasks in the list.";
        return addedTask;
    }

    private static void addTask(ArrayList<Task> taskList, String command) {
        Task task = getTask(command);
        if (task != null) {
            taskList.add(task);
            String addedTask = getNewTaskString(taskList, task);
            printCommand(addedTask);
        }
    }

    private static String getNewTaskString(ArrayList<Task> taskList, Task task) {
        String addedTask = "Got it. I've added this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.size() + " tasks in the list.";
        return addedTask;
    }

    private static TaskType getTaskType(String command) {
        if (command.toUpperCase().startsWith("TODO")) {
            return TaskType.TODO;
        }else if (command.toUpperCase().startsWith("DEADLINE")) {
            return TaskType.DEADLINE;
        }else if (command.toUpperCase().startsWith("EVENT")) {
            return TaskType.EVENT;
        }else {
            return TaskType.UNKNOWN;
        }
    }
    private static Task getTask(String command) {
        Task task = null;
        try {
            TaskType taskType = getTaskType(command);
            switch (taskType) {
                case TODO:
                    task = getTodo(command);
                    break;
                case DEADLINE:
                    task = getDeadline(command);
                    break;
                case EVENT:
                    task = getEvent(command);
                    break;
                default:
                    throw new IllegalTaskException();
            }
        }catch (IllegalTodoException e){
            printCommand("☹ OOPS!!! The description of a todo cannot be empty.");
        }catch (IllegalDeadlineException e){
            printCommand("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        }catch (IllegalEventException e){
            printCommand("☹ OOPS!!! The description or start date or end date of an event cannot be empty.");
        }catch (IllegalTaskException e){
            printCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }catch (IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException for task: " + command);
        }
        return task;
    }

    private static Todo getTodo(String command) throws IndexOutOfBoundsException, IllegalTodoException {
        String[] commandStr = command.split(" ");
        if (commandStr.length < 2) {
            throw new IllegalTodoException();
        }
        String description = command.replaceFirst(commandStr[0], "").trim();
        return new Todo(description);
    }

    private static Deadline getDeadline(String command) throws IndexOutOfBoundsException, IllegalDeadlineException{
        String[] deadlines = command.split("/by");
        if (deadlines.length < 2) {
            throw new IllegalDeadlineException();
        }
        String[] commandStr = deadlines[0].split(" ");
        if (commandStr.length < 2) {
            throw new IllegalDeadlineException();
        }
        String description = deadlines[0].replaceFirst(commandStr[0], "").trim();
        String by = deadlines[1].trim();
        return new Deadline(description, by);
    }

    private static Event getEvent(String command) throws IndexOutOfBoundsException, IllegalEventException{
        String[] events = command.split("/from");
        if (events.length < 2) {
            throw new IllegalEventException();
        }
        String[] commandStr = events[0].split(" ");
        if (commandStr.length < 2) {
            throw new IllegalEventException();
        }
        String description = events[0].replaceFirst(commandStr[0], "").trim();

        String[] dates = events[1].split("/to");
        if (dates.length < 2) {
            throw new IllegalEventException();
        }
        String fromDate = dates[0].trim();
        String toDate = dates[1].trim();

        return new Event(description, fromDate, toDate);
    }

    private static void printList(ArrayList<Task> list) {
        String printStr = "";
        for (int i = 0; i < list.size(); i++) {
            String index = Integer.toString(i + 1);
            printStr +=  index + "." + list.get(i) + System.lineSeparator() + "\t";
        }

        if (list.size() > 0){
            printCommand(printStr);
        }else {
            printCommand("You have 0 tasks in the list.");
        }
    }

    private static void exitCommand() {
        printCommand("Bye. Hope to see you again soon!");
    }

    private static void printCommand(String command) {
        System.out.println("------------------------------------------------------------");
        System.out.println("\t" + command);
        System.out.println("------------------------------------------------------------");
    }
}
