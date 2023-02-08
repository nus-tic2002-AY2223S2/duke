import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke{

    public static void updateTask(String userInput, List<Task> userTask, String status) {
        int listNumber = 0;
        int currentItem = 0;
        String formatString = "";
        String description = "";
        String[] stringSplit = new String[100];
        try {
            if (status.equals("mark") || status.equals("unmark")) {

                stringSplit = formatString.split("");
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
                case "todo":
                    formatString = userInput.replace("todo", "").trim();
                    if (formatString.equals("")){
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    System.out.println("Got it. I've added this task:");

                    userTask.add(new ToDo(formatString, "T"));
                    currentItem = userTask.size() - 1;

                    System.out.println(userTask.get(currentItem));
                    break;
                case "deadline":
                    formatString = userInput.replace("deadline", "").trim();
                    stringSplit = formatString.split("/");
                    description = stringSplit[0];
                    String by = stringSplit[1].replace("by", "").trim();
                    userTask.add(new Deadlines(description, "D", by));

                    System.out.println("Got it. I've added this task:");

                    currentItem = userTask.size() - 1;
                    System.out.println(userTask.get(currentItem));

                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");
                    break;
                case "event":
                    formatString = userInput.replace("event", "").trim();
                    stringSplit = formatString.split("/");
                    description = stringSplit[0];
                    String from = stringSplit[1].replace("from", "").trim();
                    String to = stringSplit[2].replace("to", "").trim();
                    System.out.println("Got it. I've added this task:");

                    userTask.add(new Events(description, "E", from, to));
                    currentItem = userTask.size() - 1;

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
            //System.out.println(e);
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
            try{
            //Exit the chatbot.
            if (userInput.equals("bye")) {

                System.out.println("Bye. Hope to see you again soon!");

                //List out all the elements stored.
            } else if (userInput.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < userTask.size(); i++) {
                    try {

                        System.out.println((i + 1) + ". " + userTask.get(i)); 

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter only numbers.");
                    } catch (IndexOutOfBoundsException e) {

                        System.out.println("Task number not found, please try again.");
                    }

                }
                //Store user input and echo out.
            } else if (userInput.startsWith("mark")) {
                //Marks a task as done.
                updateTask(userInput, userTask, "mark");

            } else if (userInput.startsWith("unmark")) {
                //unmarks a task as not done.
                updateTask(userInput, userTask, "unmark");

            } else if (userInput.startsWith("todo")) {
                updateTask(userInput, userTask, "todo");

            } else if (userInput.startsWith("deadline")) {
                updateTask(userInput, userTask, "deadline");

            } else if (userInput.startsWith("event")) {
                updateTask(userInput, userTask, "event");

            }else {

                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }catch (Exception e){

            }
}
    }

}
