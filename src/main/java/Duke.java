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

        //Store text
        int count = 0;
        Task[] taskList = new Task[100];

        Task task;
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
                markOrUnmarkTask(count, taskList, command);
                continue;
            }

            //Create and add task to list
            count = getTaskCount(count, taskList, command);
        }
    }

    private static void markOrUnmarkTask(int count, Task[] taskList, String command) {
        command = command.toUpperCase();
        boolean markAsDone = command.startsWith("MARK") ? true : false;
        try{
            String numberStr = command.split(" ")[1].trim();
            int number = Integer.parseInt(numberStr) - 1;
            if (number < count) {
                markOrUnmakrTask(taskList[number], markAsDone);
            }
        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private static int getTaskCount(int count, Task[] taskList, String command) {
        Task task;
        task = getTask(command);
        if (task != null) {
            taskList[count] = task;
            count++;
            String addedTask = getNewTaskString(count, task);
            printCommand(addedTask);
        }
        return count;
    }

    private static String getNewTaskString(int count, Task task) {
        String addedTask = "Got it. I've added this task: " + System.lineSeparator();
        addedTask += "\t\t" + task + System.lineSeparator();
        addedTask += "\t" + "Now you have " + count + " tasks in the list.";
        return addedTask;
    }

    private static Task getTask(String command) {
        Task task = null;
        try {
            if (command.toUpperCase().startsWith("TODO")) {
                task = getTodo(command);
            }else if (command.toUpperCase().startsWith("DEADLINE")) {
                task = getDeadline(command);
            }else if (command.toUpperCase().startsWith("EVENT")) {
                task = getEvent(command);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Incomplete task description: " + command);
        }catch (IllegalTaskException e){
            System.out.println("Unable to process task: " + command);
        }
        return task;
    }

    private static Todo getTodo(String command) throws IndexOutOfBoundsException {
        String[] commandStr = command.split(" ");
        String description = command.replaceFirst(commandStr[0], "").trim();
        return new Todo(description);
    }

    private static Deadline getDeadline(String command) throws IndexOutOfBoundsException, IllegalTaskException{
        String[] deadlines = command.split("/by");
        if (deadlines.length > 2) {
            throw new IllegalTaskException();
        }
        String[] commandStr = deadlines[0].split(" ");
        String description = deadlines[0].replaceFirst(commandStr[0], "").trim();
        String by = deadlines[1].trim();
        return new Deadline(description, by);
    }

    private static Event getEvent(String command) throws IndexOutOfBoundsException, IllegalTaskException{
        String[] events = command.split("/from");
        if (events.length > 2) {
            throw new IllegalTaskException();
        }
        String[] commandStr = events[0].split(" ");
        String description = events[0].replaceFirst(commandStr[0], "").trim();

        String[] dates = events[1].split("/to");
        String fromDate = dates[0].trim();
        String toDate = dates[1].trim();

        return new Event(description, fromDate, toDate);
    }

    private static void markOrUnmakrTask(Task task, boolean markAsDone) {
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

    private static void printList(Task[] list, int count) {
        String printStr = "";
        for (int i = 0; i < count; i++) {
            String index = Integer.toString(i + 1);
            printStr +=  index + "." + list[i] + System.lineSeparator() + "\t";
        }
        printCommand(printStr);
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
