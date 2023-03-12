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

        Task task;
        String command;
        CommandType commandType;

        Scanner in = new Scanner(System.in);

        //Store task
        ArrayList<Task> taskList = DukeFileReader.getTasklistInFile();

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
                    //Delete Task
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

            String taskStr = getTaskList(taskList);
            DukeFileWriter.writeInFile(taskStr);

            printCommand(printStr + System.lineSeparator() + "\t" + task);
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException : " + command);
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
            String deletedTask = getDeletedTaskString(taskList, task);
            printCommand(deletedTask);

            String taskStr = getTaskList(taskList);
            DukeFileWriter.writeInFile(taskStr);
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

            DukeFileWriter.appendInFile(command + "@" + task.isDone + System.lineSeparator());

        }
    }

    private static String getNewTaskString(ArrayList<Task> taskList, Task task) {
        String addedTask = "Got it. I've added this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + taskList.size() + " tasks in the list.";
        return addedTask;
    }

    public static TaskType getTaskType(String command) {
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
    public static Task getTask(String command) {
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


    public static Todo getTodo(String command) throws IndexOutOfBoundsException, IllegalTodoException, NumberFormatException {
        String[] commandStr = command.split(" ");
        if (commandStr.length < 2) {
            throw new IllegalTodoException();
        }

        String[] taskStr = commandStr[1].split("@");

        String description = taskStr[0].trim();
        Todo todo = new Todo(description);

        if (taskStr.length > 1) {
            String markAsDone = taskStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                todo.markAsDone();
            }
        }

        return todo;
    }

    public static Deadline getDeadline(String command) throws IndexOutOfBoundsException, IllegalDeadlineException{
        String[] deadlines = command.split("/by");
        if (deadlines.length < 2) {
            throw new IllegalDeadlineException();
        }
        String[] commandStr = deadlines[0].split(" ");
        if (commandStr.length < 2) {
            throw new IllegalDeadlineException();
        }
        String description = deadlines[0].replaceFirst(commandStr[0], "").trim();

        String[] byStr = deadlines[1].split("@");
        String by = byStr[0].trim();
        Deadline deadline = new Deadline(description, by);

        if (byStr.length > 1) {
            String markAsDone = byStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                deadline.markAsDone();
            }
        }

        return deadline;
    }

    public static Event getEvent(String command) throws IndexOutOfBoundsException, IllegalEventException{
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

        String[] toStr = dates[1].split("@");
        String toDate = toStr[0].trim();

        Event event = new Event(description, fromDate, toDate);

        if (toStr.length > 1) {
            String markAsDone = toStr[1].trim();
            boolean mark = Boolean.parseBoolean(markAsDone);
            if (mark) {
                event.markAsDone();
            }
        }

        return event;
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

    private static String getTaskList(ArrayList<Task> list) {
        String printStr = "";
        for (int i = 0; i < list.size(); i++) {
            printStr +=  list.get(i).toCommand() + "@" + list.get(i).isDone + System.lineSeparator();
        }
        return printStr;
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
