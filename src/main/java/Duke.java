import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //  this isNumeric method get from https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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

            if (sentences.equals("bye")) {
                continue;
            } else if (sentences.equals("list")) {
                System.out.println("_________________________________________");
                for (int i = 1; i < tasksArray.size() + 1; i++) {
                    System.out.println(tasksArray.get(i - 1).toString());
                }
                System.out.println("_________________________________________\n");
                // level 3 mark feature
            } else if (sentences.contains("mark")) {
                // private case: mark, mark1, mark 0, mark paper, mark/unmark number > task number)
                if (sentences.equals("mark")) {
                    System.out.println("mark is a function key. Please indicate which task you want to mark!\n");
                } else {
                    String[] wordsInSentences = sentences.split(" ");
                    if (wordsInSentences.length == 2 && isNumeric(wordsInSentences[1])) {
                        int taskNumber = Integer.parseInt(wordsInSentences[1]);
                        if (taskNumber == 0) {
                            System.out.println("There is no task 0. The task number is start from 1!\n");
                            continue;
                        }
                        else if (taskNumber > tasksArray.size()) {
                            System.out.println("Invalid task number! Please make sure the task number that you have!\n");
                            continue;
                        }
                        if (wordsInSentences[0].equals("mark")) {
                            tasksArray.get(taskNumber - 1).markAsDone();
                        } else if (wordsInSentences[0].equals("unmark")) {
                            tasksArray.get(taskNumber - 1).markAsNotDone();
                        }
                    } else {
                        System.out.println("_________________________________________\n added:" + sentences + "\n_________________________________________\n");
                        tasksArray.add(new Task(sentences));
                    }
                }
                //*********************
                // level 4 To do feature
                //*********************
            } else if (sentences.contains("todo")) {
                // private case: to do, todo1,
                if (sentences.equals("todo")) {
                    System.out.println("todo is a function key. Please indicate what task to do!\n");
                } else {
                    String[] wordsInToDo = sentences.split(" ");
                    if (wordsInToDo.length >= 2) {
                        // if there is to do task: skip "to do" and put the task in taskArray
                        tasksArray.add(new ToDo(sentences.substring(5)));
                        System.out.println("_________________________________________\nGot it. I've added this task:\n    " + tasksArray.get(tasksArray.size() - 1).toString() + "\nNow you have " + tasksArray.size() + " tasks in the list.\n_________________________________________\n" );
                    } else {
                        System.out.println("_________________________________________\n added:" + sentences + "\n_________________________________________\n");
                        tasksArray.add(new Task(sentences));
                    }
                }
                //**************************
                // level 4 Deadline feature
                //**************************
            } else if (sentences.contains("deadline")) {
                    // private case: deadline, deadline1,
                    if (sentences.equals("deadline")) {
                        System.out.println("deadline is a function key. Please indicate what task to do!\n");
                    } else {
                        String[] wordsInDeadline = sentences.split("/by");
                        if (wordsInDeadline.length == 2) {
                            // if there is deadline task: skip "deadline" and put the task in taskArray
                            tasksArray.add(new Deadline(wordsInDeadline[0].substring(9),wordsInDeadline[1]));
                            System.out.println("_________________________________________\nGot it. I've added this task:\n    " + tasksArray.get(tasksArray.size() - 1).toString() + "\nNow you have " + tasksArray.size() + " tasks in the list.\n_________________________________________\n" );
                        } else {
                            System.out.println("_________________________________________\n added:" + sentences + "\n_________________________________________\n");
                            tasksArray.add(new Task(sentences));
                        }
                    }
                //**************************
                // level 4 Event feature
                //**************************
            } else if (sentences.contains("event")) {
                // private case: event, event1,
                if (sentences.equals("event")) {
                    System.out.println("event is a function key. Please indicate what task to do!\n");
                } else {
                    String[] wordsInEvent = sentences.split("/from|/to");
                    if (wordsInEvent.length > 2) {
                        // if there is event task: skip "event" and put the task in taskArray
                        tasksArray.add(new Event(wordsInEvent[0].substring(6),wordsInEvent[1], wordsInEvent[2]));
                        System.out.println("_________________________________________\nGot it. I've added this task:\n    " + tasksArray.get(tasksArray.size() - 1).toString() + "\nNow you have " + tasksArray.size() + " tasks in the list.\n_________________________________________\n" );
                    } else {
                        System.out.println("_________________________________________\n added:" + sentences + "\n_________________________________________\n");
                        tasksArray.add(new Task(sentences));
                    }
                }
            } else {
                System.out.println("_________________________________________\n added:" + sentences + "\n_________________________________________\n");
                tasksArray.add(new Task(sentences));
            }
        } while (!sentences.equals("bye"));
        System.out.println("_________________________________________\n Bye. Hope to see you soon again! \n_________________________________________\n");
    }
}



//            //use switch-case statement
//            switch (line) {
//                case "bye":
//                    continue;
//                case "list":
//                    System.out.println("_________________________________________");
//                    for (int i = 1; i < tasksArray.size() + 1; i++) {
//                        System.out.println(i + ". [" + tasksArray.get(i - 1).getStatusIcon() + "] " + tasksArray.get(i - 1).getTask());
//                    }
//                    System.out.println("_________________________________________\n");
//                    break;
//                case if (line.substring(0,3) == "mark") :
//                    tasks.Array.get
//                default:
//                    System.out.println("_________________________________________\n added:" + line + "\n_________________________________________\n");
//                    tasksArray.add(new Task(line));
//                    break;
//            }
//        }while (!line.equals("bye")) ;
//            System.out.println("_________________________________________\n Bye. Hope to see you soon again! \n_________________________________________\n");
//    }
//}





