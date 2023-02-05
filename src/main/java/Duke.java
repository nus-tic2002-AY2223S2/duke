import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void drawLine() {
        System.out.println("\n_________________________________________\n");
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
        System.out.println("Hello. I'm Duke.\nWhat can i do for you?");

        // use .equals to compare string
        // if bye then exit()
        // other than bye than print the statement
        do {
            sentences = in.nextLine();
            String[] wordsInSentences = sentences.split(" ");

            // *********************
            // level 1 bye feature
            // *********************
            if (sentences.equals("bye")) {
                continue;
            }

            // **********************
            // level 2 list feature
            // **********************
            else if (sentences.equals("list")) {
                //System.out.println("_________________________________________");
                drawLine();
                for (int i = 1; i < tasksArray.size() + 1; i++) {
                    System.out.println(tasksArray.get(i - 1).toString());
                }
                //System.out.println("_________________________________________\n");
                drawLine();
            }

            // ***************************
            // level 3 mark/unmark feature
            // ***************************
            else if (wordsInSentences[0].equals("mark")) {
                // corner cases: mark, mark1, mark 0, mark paper, mark/unmark number > task number)
                try {
                    int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        if (taskNumber == 0) {
                            System.out.println("There is no task 0. The task number is start from 1!\n");
                            continue;
                        } else if (taskNumber > tasksArray.size()) {
                            System.out.println("Invalid task number! Please make sure the task number that you have!\n");
                            continue;
                        }
                        tasksArray.get(taskNumber - 1).markAsDone();
                } catch (NumberFormatException nfe) {
                    System.out.println("mark is a function key. Please indicate task number to be marked!");
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException obe) {
                    System.out.println("mark feature must contain a task number");
                }
            }

            else if (wordsInSentences[0].equals("unmark")) {
                // corner cases: mark, mark1, mark 0, mark paper, mark/unmark number > task number)
                try {
                        int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        if (taskNumber == 0) {
                            System.out.println("There is no task 0. The task number is start from 1!\n");
                            continue;
                        } else if (taskNumber > tasksArray.size()) {
                            System.out.println("Invalid task number! Please make sure the task number that you have!\n");
                            continue;
                        }
                        tasksArray.get(taskNumber - 1).markAsNotDone();
                } catch (NumberFormatException nfe) {
                    System.out.println("mark is a function key. Please indicate task number to be marked!");
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException obe) {
                    System.out.println("unmark feature must contain a task number");
                }
            }

            // **********************
            // level 4 To do feature
            // **********************
            else if (wordsInSentences[0].equals("todo")) {
                // corner cases: to do, todo1,
                String[] descriptionInToDo = sentences.split(" ");
                String todo = new String();
                try {
                    // if there is to do task: skip "to do" and put the task in taskArray
                    todo = descriptionInToDo[1];
                    for (int i = 2; i < descriptionInToDo.length; i++) {
                        todo += " " + descriptionInToDo[i];
                    }
                    tasksArray.add(new ToDo(todo));
                    drawLine();
                    System.out.println("Got it. I've added this task:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException obe) {
                    System.out.println("A todo command must contain a task!");
                }
            }

            // *************************
            // level 4 Deadline feature
            // *************************
            else if (wordsInSentences[0].equals("deadline")) {
                // corner case: deadline, deadline1, task = check return book deadline,
                String[] descriptionInDeadline = sentences.split("/by");
                try {
                    // if there is deadline task: skip "deadline" and extract the task and by
                    tasksArray.add(new Deadline(descriptionInDeadline[0].substring(9), descriptionInDeadline[1]));
                    drawLine();
                    System.out.println("Got it. I've added this task:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException obe) {
                    System.out.println("A deadline command must provide a deadline date (/by)!");
                }
            }

            // *************************
            // level 4 Event feature
            // *************************
            else if (wordsInSentences[0].equals("event")) {
                // private case: event, event1,
                String[] descriptionInEvent = sentences.split("/from|/to");
                try {
                    // if there is event task: skip "event" and put the task in taskArray
                    tasksArray.add(new Event(descriptionInEvent[0].substring(6), descriptionInEvent[1], descriptionInEvent[2]));
                    drawLine();
                    System.out.println("Got it. I've added this task:\n    "
                            + tasksArray.get(tasksArray.size() - 1).toString() +
                            "\nNow you have " + tasksArray.size() + " tasks in the list.");
                    drawLine();
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException obe) {
                    System.out.println("An event must contain a start period (/from) and a end period (/to)!");
                }
            }

            else {
                System.out.println("Please provide task feature in the 1st words!");
            }
        } while (!sentences.equals("bye"));
        drawLine();
        System.out.println("Bye. Hope to see you soon again!");
        drawLine();
    }
}






