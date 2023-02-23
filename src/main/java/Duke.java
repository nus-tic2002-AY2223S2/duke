import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void drawLine() {
        System.out.println("\n_________________________________________\n");
    }

    public static void checkDescriptionExist(int wordsDescription, int minWords) throws DukeException {
        if (wordsDescription < minWords) {
            throw new DukeException("no description");            // To handle exception: to-do
        }
    }
    public static void checkTaskExist(int index, int min) throws NoTaskException {
        if (index == min) {
            throw new NoTaskException("no task");            // To handle exception: to-do
        }
    }

    public static void checkPartsRequirement(int partsDescription, int numberParts) throws IncompleteDescriptionException {
        if (partsDescription > numberParts) {
            throw new IncompleteDescriptionException("incomplete description");            // To handle exception: description more than 1 /by /from /to
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String sentences;

        // instantiate new empty ArrayList
        ArrayList<Task> tasksArray = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello. This is Reminder created by WP.\nWhat can i do for you?");


        // *********************
        // level 1 bye feature
        // *********************
        // use .equals to compare string
        // if bye then exit()
        do {
            sentences = in.nextLine();

            // initialized a new object for the task that user type in
            Task objectTask = new Task(sentences);

            // initialized arrays and string variable
            String[] wordsInSentences = sentences.split(" ");
            String[] wordsInDescription = sentences.split(" ");
            String[] partsInDescription = sentences.split("/by|/from|/to");
            String task = new String();
/*
            Feature duty = null;
            try {
                duty = Feature.valueOf(wordsInSentences[0].toUpperCase());   // convert different types of values into string
            } catch (IllegalArgumentException iae) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }
 */
            //initialized an object for Enum task
            Task.task objectEnumTask = null;
            try {
                objectEnumTask = Task.task.valueOf(task.valueOf(wordsInSentences[0].toUpperCase()));   // convert different types of values into string
            } catch (IllegalArgumentException iae) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }
            // *************************
            // level 6 ENUM
            // *************************
            switch (objectEnumTask) {
                // **********************
                // level 2 list feature
                // **********************
                case LIST:
                    drawLine();
                    for (int i = 1; i < tasksArray.size() + 1; i++) {
                        System.out.println(tasksArray.get(i - 1).toString());
                    }
                    drawLine();
                    break;

                // ***************************
                // level 3 mark/unmark feature
                // ***************************
                case MARK:
                    // *************************
                    // level 5 ErrorHandle
                    // *************************
                    try {
                        int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        tasksArray.get(taskNumber - 1).markAsDone();
                    } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                        System.out.println("mark is a function key. Please indicate task number to be marked!");
                    } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                        System.out.println("mark feature must contain a task number");
                    } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to mark!");
                    }
                    break;

                case UNMARK:
                    try {
                        int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        tasksArray.get(taskNumber - 1).markAsNotDone();
                    } catch (NumberFormatException nfe) {              // to handle exception: mark nonINTEGER
                        System.out.println("unmark is a function key. Please indicate task number to be marked!");
                    } catch (ArrayIndexOutOfBoundsException obe) {  // to handle exception: mark
                        System.out.println("unmark feature must contain a task number");
                    } catch (IndexOutOfBoundsException obe) {        // to handle exception:  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to unmark!");
                    }
                    break;

                // **********************
                // level 4 To-do feature
                // **********************
                case TODO:
                    String todo = new String();
                    try {
                        checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no Task
                        // if there is to-do task: skip "to-do" and put description in taskArray
                        for (int i = 1; i < wordsInDescription.length; i++) {
                            todo += wordsInDescription[i] + " ";
                        }
                        tasksArray.add(new ToDo(todo));
                        drawLine();
                        System.out.println("Got it. I've added this task:\n    "
                                + tasksArray.get(tasksArray.size() - 1).toString() +
                                "\nNow you have " + tasksArray.size() + " tasks in the list.");
                        drawLine();
                    } catch (DukeException de) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;

                // *************************
                // level 4 Deadline feature
                // *************************
                case DEADLINE:
                    try {
                        checkDescriptionExist(wordsInDescription.length, 2); //handle exception: no deadline(/by)
                        checkPartsRequirement(partsInDescription.length, 2);
                        // if there is deadline task: skip "deadline" and extract the task and by
                        int indexBy = Arrays.asList(wordsInDescription).indexOf("/by");
                        //duty = new String();
                        for (int i = 1; i < indexBy; i++) {
                            task += wordsInDescription[i] + " ";
                        }
                        checkTaskExist(indexBy, 1);
                        tasksArray.add(new Deadline(task, partsInDescription[1]));
                        drawLine();
                        System.out.println("Got it. I've added this task:\n    "
                                + tasksArray.get(tasksArray.size() - 1).toString() +
                                "\nNow you have " + tasksArray.size() + " tasks in the list.");
                        drawLine();
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
                        checkDescriptionExist(wordsInDescription.length, 2); //handle exception: event only)
                        checkPartsRequirement(partsInDescription.length, 3);
                        // if there is event duty: skip "event" and put the duty in taskArray
                        //String duty = new String();
                        for (int i = 1; i < indexFrom; i++) {
                            task += wordsInDescription[i] + " ";
                        }
                        checkTaskExist(indexFrom, 1);
                        tasksArray.add(new Event(task, partsInDescription[1], partsInDescription[2]));
                        drawLine();
                        System.out.println("Got it. I've added this duty:\n    "
                                + tasksArray.get(tasksArray.size() - 1).toString() +
                                "\nNow you have " + tasksArray.size() + " tasks in the list.");
                        drawLine();
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
                        int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        drawLine();
                        System.out.println("Noted. I've removed this task:\n    "
                                + tasksArray.get(taskNumber - 1).toString() +
                                "\nNow you have " + (tasksArray.size() - 1) + " tasks in the list.");
                        drawLine();
                        tasksArray.remove(taskNumber - 1);
                    } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                        System.out.println("delete is a function key. Please indicate task number to be deleted!");
                    } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                        System.out.println("delete feature must contain a task number");
                    } catch (
                            IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                        System.out.println("Please give a valid task number that you want to delete!");
                    }
                    break;
                default:

            }
        } while (!sentences.equalsIgnoreCase("bye"));
        drawLine();
        System.out.println("Bye. Hope to see you soon again!");
        drawLine();
    }
}


/* If else if method:

            // **********************
            // level 2 list feature
            // **********************
            if (sentences.equals("list")) {
                drawLine();
                for (int i = 1; i < tasksArray.size() + 1; i++) {
                    System.out.println(tasksArray.get(i - 1).toString());
                }
                drawLine();
            }

            // ***************************
            // level 3 mark/unmark feature
            // ***************************
            else if (wordsInSentences[0].equals("mark")) {
                try {
                    int taskNumber = Integer.parseInt(wordsInSentences[1]);
                    tasksArray.get(taskNumber - 1).markAsDone();
                } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                    System.out.println("mark is a function key. Please indicate task number to be marked!");
                } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                    System.out.println("mark feature must contain a task number");
                } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                    System.out.println("Please give a valid task number that you want to mark!");
                }
            } else if (wordsInSentences[0].equals("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(wordsInSentences[1]);
                    tasksArray.get(taskNumber - 1).markAsNotDone();
                } catch (NumberFormatException nfe) {              // to handle exception: mark nonINTEGER
                    System.out.println("unmark is a function key. Please indicate task number to be marked!");
                } catch (ArrayIndexOutOfBoundsException obe) {  // to handle exception: mark
                    System.out.println("unmark feature must contain a task number");
                } catch (IndexOutOfBoundsException obe) {        // to handle exception:  taskNumber > taskArray.size
                    System.out.println("Please give a valid task number that you want to unmark!");
                }
            }

            // **********************
            // level 4 To-do feature
            // **********************
            else if (wordsInSentences[0].equals("todo")) {
                // corner cases: to do, todo1,
                String[] wordsInDescription = sentences.split(" ");
                String todo = new String();
                try {
                    checkDescriptionExist(wordsInDescription.length, 2);  // handle exception: no Task
                    // if there is to-do task: skip "to-do" and put description in taskArray
                    for (int i = 1; i < wordsInDescription.length; i++) {
                        todo += wordsInDescription[i] + " ";
                    }
                    tasksArray.add(new ToDo(todo));
                    drawLine();
                    System.out.println("Got it. I've added this task:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (DukeException de) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }

            // *************************
            // level 4 Deadline feature
            // *************************
            else if (wordsInSentences[0].equals("deadline")) {
                // corner case: deadline, deadline1, task = check return book deadline,
                String[] wordsInDescription = sentences.split(" ");
                String[] partsInDescription = sentences.split("/by");
                try {
                    checkDescriptionExist(wordsInDescription.length, 2); //handle exception: no deadline(/by)
                    checkPartsRequirement(partsInDescription.length, 2);
                    // if there is deadline task: skip "deadline" and extract the task and by
                    int indexBy = Arrays.asList(wordsInDescription).indexOf("/by");
                    task = new String();
                    for (int i = 1; i < indexBy; i++) {
                        task += wordsInDescription[i] + " ";
                    }
                    checkTaskExist(indexBy, 1);
                    tasksArray.add(new Deadline(task, partsInDescription[1]));
                    drawLine();
                    System.out.println("Got it. I've added this task:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException obe) {
                    System.out.println("A deadline command must provide a deadline date (/by)!");
                } catch (IncompleteDescriptionException ide) {
                    System.out.println("cannot give more than 2 deadline (/by)");
                } catch (NoTaskException nte) {
                    System.out.println("There is no Task .");
                } catch (DukeException de) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }

            // *************************
            // level 4 Event feature
            // *************************
            else if (wordsInSentences[0].equals("event")) {
                // private case: event, event1,
                String[] wordsInDescription = sentences.split(" ");
                String[] partsInDescription = sentences.split("/from|/to");
                int indexFrom = Arrays.asList(wordsInDescription).indexOf("/from");
                try {
                    checkDescriptionExist(wordsInDescription.length, 2); //handle exception: event only)
                    checkPartsRequirement(partsInDescription.length, 3);
                    // if there is event duty: skip "event" and put the duty in taskArray
                    String duty = new String();
                    for (int i = 1; i < indexFrom; i++) {
                        duty += wordsInDescription[i] + " ";
                    }
                    checkTaskExist(indexFrom, 1);
                    tasksArray.add(new Event(duty, partsInDescription[1], partsInDescription[2]));
                    drawLine();
                    System.out.println("Got it. I've added this duty:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException obe) {
                    System.out.println("An event must contain a start period (/from) and a end period (/to)!");
                } catch (IncompleteDescriptionException ide) {
                    System.out.println("Events must contain 1 task, 1 /from and 1 /to");
                } catch (NoTaskException nte) {
                    System.out.println("There is no Task .");
                } catch (DukeException de) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                }
            }

            // *************************
            // level 6 Delete feature
            // *************************
            else if (wordsInSentences[0].equals("delete")) {
                String[] wordsInDescription = sentences.split(" ");
                try {
                    int taskNumber = Integer.parseInt(wordsInSentences[1]);
                    drawLine();
                    System.out.println("Noted. I've removed this task:\n    "
                            + tasksArray.get(taskNumber - 1).toString() +
                            "\nNow you have " + (tasksArray.size() - 1) + " tasks in the list.");
                    drawLine();
                    tasksArray.remove(taskNumber - 1);
                } catch (NumberFormatException nfe) {                 // corner case: mark nonINTEGER
                    System.out.println("delete is a function key. Please indicate task number to be deleted!");
                } catch (ArrayIndexOutOfBoundsException obe) {    // corner case: mark
                    System.out.println("delete feature must contain a task number");
                } catch (IndexOutOfBoundsException obe) {          // corner case: mark 0,  taskNumber > taskArray.size
                    System.out.println("Please give a valid task number that you want to delete!");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!sentences.equals("bye"));
        drawLine();
        System.out.println("Bye. Hope to see you soon again!");
        drawLine();
    }
}
*/
