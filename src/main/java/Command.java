import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Command {

    protected Parser.taskEnum task;
    protected String taskDescription;
    protected Boolean isExit = false;
    protected int taskNumber;
    protected LocalDateTime taskDeadline;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String findItem;
    protected String statItem;

    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException, IOException {

        if (task != null) {
            switch (task) {
                // *********************
                // level 1 bye feature
                // *********************
                case BYE:
                    this.isExit = true;
                    break;
                // **********************
                // level 2 list feature
                // **********************
                case LIST:
                    for (int i = 1; i < tl.getSize() + 1; i++) {
                        System.out.println(i + "." + TaskList.tasksArray.get(i - 1).toString());
                    }
                    break;

                // ***************************
                // level 3 mark/unmark feature
                // ***************************
                case MARK:
                    // *************************
                    // level 5 ErrorHandle
                    // *************************
                    try {
                        tl.get(taskNumber - 1).markAsDone();
                    } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                        System.out.println("mark is a function key. Please indicate task number to be marked!");
                    } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                        System.out.println("mark feature must contain a task number");
                    } catch (
                            IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to mark!");
                    }
                    break;

                case UNMARK:
                    try {
                        tl.get(taskNumber - 1).markAsNotDone();
                    } catch (NumberFormatException nfe) {              // to handle exception: mark nonINTEGER
                        System.out.println("unmark is a function key. Please indicate task number to be marked!");
                    } catch (ArrayIndexOutOfBoundsException obe) {  // to handle exception: mark
                        System.out.println("unmark feature must contain a task number");
                    } catch (
                            IndexOutOfBoundsException obe) {        // to handle exception:  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to unmark!");
                    }
                    break;

                // **********************
                // level 4 To-do feature
                // **********************
                case TODO:
                    if (taskDescription != null) {
                        tl.add(new ToDo(taskDescription));
                        System.out.println("Got it. I've added this task:\n    "
                                + taskDescription +
                                "\nNow you have " + tl.getSize() + " tasks in the list.");
                    }
                    break;

                // *************************
                // level 4 Deadline feature
                // *************************
                case DEADLINE:
                    if (taskDescription != null && taskDeadline != null) {
                        tl.add(new Deadline(taskDescription, taskDeadline));
                        System.out.println("Got it. I've added this task:\n    "
                                + taskDescription + "/by " + taskDeadline +
                                "\nNow you have " + tl.getSize() + " tasks in the list.");
                    }
                    break;

                // *************************
                // level 4 Event feature
                // *************************
                case EVENT:
                    if (taskDescription != null && start != null && end != null) {
                        tl.add(new Event(taskDescription, start, end));
                        System.out.println("Got it. I've added this duty:\n    "
                                + taskDescription + "/from " + start + " /to " + end +
                                "\nNow you have " + tl.getSize() + " tasks in the list.");
                    }
                    break;

                // *************************
                // level 5 Delete feature
                // *************************
                case DELETE:
                    try {
                        System.out.println("Noted. I've removed this task:\n    "
                                + tl.get(taskNumber - 1).toString() +
                                "\nNow you have " + (tl.getSize() - 1) + " tasks in the list.");
                        tl.remove(taskNumber - 1);
                    } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                        System.out.println("delete is a function key. Please indicate task number to be deleted!");
                    } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                        System.out.println("delete feature must contain a task number");
                    } catch (
                            IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to delete!");
                    }
                    break;

                // *************************
                // level 9 Find feature
                // *************************
                case FIND:
                    if (findItem != null) {
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (TaskList.tasksArray.get(i - 1).description.contains(findItem)) {
                                System.out.println(i + "." + TaskList.tasksArray.get(i - 1).toString());
                            }
                        }
                    }
                    break;

                // *************************
                // level 9 Add Individual Feature: C-Statistics
                // *************************
                case STAT:
                    System.out.println("Please select the number to get different Statistics:\n1. Stats for Tasks done.\n2. Stats for Tasks not done.\n" +
                            "3. Stats for Todo.\n4. Stats for Deadline.\n5. Stats for Events" );
                    Scanner in = new Scanner(System.in);
                    this.statItem = in.nextLine();
                    if (Integer.parseInt(statItem) == 1 ) {
                        int stats = 0;
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (TaskList.tasksArray.get(i - 1).isDone) {
                                stats ++;
                            }
                        }
                        System.out.println("Number of Tasks done = " + stats);
                    }
                    else if (Integer.parseInt(statItem) == 2 ) {
                        int stats = 0;
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (!TaskList.tasksArray.get(i - 1).isDone) {
                                stats ++;
                            }
                        }
                        System.out.println("Number of Tasks not done = " + stats);
                    }
                    else if (Integer.parseInt(statItem) == 3 ) {
                        int stats = 0;
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (TaskList.tasksArray.get(i - 1) instanceof ToDo) {
                                stats++;
                            }
                        }
                        System.out.println("Number of Todo Task = " + stats);
                    }
                    else if (Integer.parseInt(statItem) == 4 ) {
                        int stats = 0;
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (TaskList.tasksArray.get(i - 1) instanceof Deadline) {
                                stats++;
                            }
                        }
                        System.out.println("Number of Deadline Task = " + stats);
                    }
                    else if (Integer.parseInt(statItem) == 5 ) {
                        int stats = 0;
                        for (int i = 1; i <= tl.getSize(); i++) {
                            if (TaskList.tasksArray.get(i - 1) instanceof Event) {
                                stats++;
                            }
                        }
                        System.out.println("Number of Event Task = " + stats);
                    }
                    break;

                default:
            }
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
