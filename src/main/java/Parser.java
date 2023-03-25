import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    /*\      public static boolean isToDoCommand(String command) {
           String[] wordsInSentences = command.split(" ");
           if (String.valueOf(wordsInSentences[0].toUpperCase()) == "todo") {
               return true;
           } else return false;
       }

       public static ToDo createTodo(String command) {

       }
   */
    // *************************
    // level 6 ENUM
    // *************************
    public enum taskEnum {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, STAT
    }

    public static Command parse(String fullCommand) throws DukeException {

        Command c = new Command();
        String[] wordsInDescription = fullCommand.split(" ");
        String[] partsInDescription = fullCommand.split("/by|/from|/to");

        //initialized an object for Enum task
        Parser.taskEnum objectEnumTask = null;
        try {
            objectEnumTask = Parser.taskEnum.valueOf(String.valueOf(taskEnum.valueOf(wordsInDescription[0].toUpperCase())));   // convert different types of values into string
        } catch (IllegalArgumentException iae) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (objectEnumTask != null) {
            c.task = objectEnumTask;
            String taskJob = new String();

            switch (objectEnumTask) {
                case BYE:
                    break;

                case MARK:
                    // *************************
                    // level 5 ErrorHandle
                    // *************************
                    try {
                        c.taskNumber = Integer.parseInt(wordsInDescription[1]);
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
                        c.taskNumber = Integer.parseInt(wordsInDescription[1]);
                    } catch (NumberFormatException nfe) {              // to handle exception: mark nonINTEGER
                        System.out.println("unmark is a function key. Please indicate task number to be marked!");
                    } catch (ArrayIndexOutOfBoundsException obe) {  // to handle exception: mark
                        System.out.println("unmark feature must contain a task number");
                    } catch (
                            IndexOutOfBoundsException obe) {        // to handle exception:  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to unmark!");
                    }
                    break;

                case TODO:

                    try {
                        DukeException.checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no Task
                        // if there is to-do task: skip "to-do" and put description in taskArray
                        for (int i = 1; i < wordsInDescription.length; i++) {
                            taskJob += wordsInDescription[i] + " ";
                        }
                        c.taskDescription = taskJob;
                    } catch (DukeException de) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;

                // *************************
                // level 4 Deadline feature
                // *************************
                case DEADLINE:
                    try {
                        DukeException.checkDescriptionExist(wordsInDescription.length, 2); //handle exception: no deadline(/by)
                        DukeException.checkPartsRequirement(partsInDescription.length, 2);
                        // if there is deadline task: skip "deadline" and extract the task and by
                        int indexBy = Arrays.asList(wordsInDescription).indexOf("/by");
                        //duty = new String();
                        for (int i = 1; i < indexBy; i++) {
                            taskJob += wordsInDescription[i] + " ";
                        }
                        DukeException.checkTaskExist(indexBy, 1);
                        c.taskDescription = taskJob;

                        // *************************
                        // level 8 Dates and Times
                        // *************************
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        try {
                            c.taskDeadline = LocalDateTime.parse(partsInDescription[1].substring(1), formatter);
                        } catch (DateTimeParseException dtpe) {
                            System.out.println("write the deadline in yyyy-MM-dd HH:mm format");
                        }

                    } catch (ArrayIndexOutOfBoundsException obe) {
                        System.out.println("A deadline command must provide a deadline date (/by)!");
                    } catch (IncompleteDescriptionException ide) {
                        System.out.println(ide);
                    } catch (NoTaskException nte) {
                        System.out.println("There is no Task .");
                    } catch (DukeException de) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;

                // *************************
                // level 4 Event feature
                // *************************
                case EVENT:
                    int indexFrom = Arrays.asList(wordsInDescription).indexOf("/from");
                    try {
                        DukeException.checkDescriptionExist(wordsInDescription.length, 2); //handle exception: event only)
                        DukeException.checkPartsRequirement(partsInDescription.length, 3);
                        // if there is event duty: skip "event" and put the duty in taskArray
                        //String duty = new String();
                        for (int i = 1; i < indexFrom; i++) {
                            taskJob += wordsInDescription[i] + " ";
                        }
                        DukeException.checkTaskExist(indexFrom, 1);
                        c.taskDescription = taskJob;

                        // *************************
                        // level 8 Dates and Times
                        // *************************
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        try {
                            c.start = LocalDateTime.parse(partsInDescription[1].substring(1, partsInDescription[1].length() - 1), formatter);
                            c.end = LocalDateTime.parse(partsInDescription[2].substring(1), formatter);
                        } catch (DateTimeParseException dtpe) {
                            System.out.println("write the event date & time in yyyy-MM-dd HH:mm format");
                        }

                    } catch (ArrayIndexOutOfBoundsException obe) {
                        System.out.println("An event must contain a start period (/from) and a end period (/to)!");
                    } catch (IncompleteDescriptionException ide) {
                        System.out.println("Events must contain 1 task, 1 /from and 1 /to");
                    } catch (NoTaskException nte) {
                        System.out.println("There is no Task .");
                    } catch (DukeException de) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    break;

                // *************************
                // level 5 Delete feature
                // *************************
                case DELETE:
                    try {
                        c.taskNumber = Integer.parseInt(wordsInDescription[1]);
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
                    String findItem = new String();
                    try {
                        DukeException.checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no word
                        // if there is find task: skip "find" and put description in taskArray
                        for (int i = 1; i < wordsInDescription.length; i++) {
                            findItem += wordsInDescription[i] + " ";
                        }
                        c.findItem = findItem;
                    } catch (DukeException de) {
                        System.out.println("☹ OOPS!!! The description of a FIND cannot be empty.");
                    }
                    break;

                case STAT:

                    break;

                default:
            }
        }
        return c;
    }
}

