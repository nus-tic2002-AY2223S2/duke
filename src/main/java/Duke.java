import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String[] formatString(String userInput, String status) throws DukeException {
        String formatString = "";
        String[] stringSplit = new String[3];

        switch (status) {
            case "todo":
                stringSplit[0] = userInput.replace("todo", "").trim();
                if (stringSplit[0].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                formatString = userInput.replace("deadline", "").trim();
                stringSplit = formatString.split("/");
                stringSplit[1] = stringSplit[1].replace("by", "").trim();
                break;
            case "event":
                formatString = userInput.replace("event", "").trim();
                stringSplit = formatString.split("/");
                stringSplit[1] = stringSplit[1].replace("from", "").trim();
                stringSplit[2] = stringSplit[2].replace("to", "").trim();
                break;
            default:


        }
        return stringSplit;
    }


    public static void updateTask(String userInput, List<Task> userTask, String status) {
        int listNumber = 0;
        int currentItem = 0;
        String description = "";
        String[] stringSplit = new String[1];
        String[] formattedString = new String[3];
        try {
            if (status.equals("mark") || status.equals("unmark") || status.equals("delete")) {

                stringSplit = userInput.split(" ");
                int value = Integer.parseInt(stringSplit[1]);
                listNumber = value - 1;
            }
            switch (status) {
                case "mark":
                    userTask.get(listNumber).setStatusIconMarked();
                    System.out.println(userTask.get(listNumber));
                    break;
                case "unmark":
                    userTask.get(listNumber).setStatusIconUnmarked();
                    System.out.println(userTask.get(listNumber));
                    break;
                case "delete":
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(userTask.get(listNumber));
                    userTask.remove(listNumber);
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");
                    break;
                case "todo":
                    formattedString = formatString(userInput, status);

                    userTask.add(new ToDo(formattedString[0], "T"));
                    currentItem = userTask.size() - 1;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(currentItem));
                    break;
                case "deadline":
                    formattedString = formatString(userInput, status);
                    description = formattedString[0];
                    String by = formattedString[1];

                    userTask.add(new Deadlines(description, "D", by));
                    currentItem = userTask.size() - 1;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(currentItem));
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");
                    break;
                case "event":
                    formattedString = formatString(userInput, status);
                    description = formattedString[0];
                    String from = formattedString[1];
                    String to = formattedString[2];

                    userTask.add(new Events(description, "E", from, to));

                    currentItem = userTask.size() - 1;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(currentItem));
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number not found, please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter only numbers.");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "\nHello I'm Duke");
        System.out.println("What can i do for you?");

        String userInput = "";

        List<Task> userTask = new ArrayList<>();
        while (!userInput.equals("bye")) {
            //Read user input.
            userInput = sc.nextLine();
            try {
                //Exit the chatbot.
                if (userInput.equals("bye")) {

                    System.out.println("Bye. Hope to see you again soon!");

                    //List out all the elements stored.
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userTask.size(); i++) {
                        System.out.println((i + 1) + ". " + userTask.get(i));
                    }
                    //Store user input and echo out.
                } else if (userInput.startsWith("delete")) {
                    //Deletes a task.
                    updateTask(userInput, userTask, "delete");
                } else if (userInput.startsWith("mark")) {
                    //Marks a task as done.
                    updateTask(userInput, userTask, "mark");

                } else if (userInput.startsWith("unmark")) {
                    //unmarks a task as not done.
                    updateTask(userInput, userTask, "unmark");

                } else if (userInput.startsWith("todo")) {
                    //Create to-do task
                    updateTask(userInput, userTask, "todo");

                } else if (userInput.startsWith("deadline")) {
                    //Create Deadline task
                    updateTask(userInput, userTask, "deadline");

                } else if (userInput.startsWith("event")) {
                    //Create event task
                    updateTask(userInput, userTask, "event");

                } else {

                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            } catch (Exception e) {

            }
        }
    }

}
