import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;

    private List<Task> tasks;
    public Duke(String filename){
        storage = new Storage(filename);
    }


public static void updateSaveFile(List<Task> userTask){
        try{
            FileWriter myWriter = new FileWriter("data\\duke.txt");
            for (int i = 0; i < userTask.size(); i++) {
              //  System.out.println((i + 1) + ". " + userTask.get(i));
                String test = userTask.get(i).toString().replace("[", "").trim();
                String test2 = test.replace("]", "|").trim();
                String test3 = test2.replace("/by", "|").trim();
                String test4 = test3.replace("/from", "|").trim();
                String test5 = test4.replace("/to", "|").trim();

               // System.out.println(test5);

                try {

                    myWriter.write(test5 + "\n");

                   // System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
}

public static void updateTask(String userInput, List<Task> userTask, String status) {
        int listNumber = 0;
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
                    userTask.get(listNumber).setStatusAsMarked();
                    System.out.println(userTask.get(listNumber));
                    updateSaveFile(userTask);
                    break;
                case "unmark":
                    userTask.get(listNumber).setStatusAsUnmarked();
                    System.out.println(userTask.get(listNumber));
                    updateSaveFile(userTask);
                    break;
                case "delete":

                    System.out.println(userTask.get(listNumber));
                    userTask.remove(listNumber);
                    System.out.println("I've removed this task:");
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");
                    updateSaveFile(userTask);
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
        new Duke("data/test.txt").run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, I'm Duke. What can i do for you?");
        Parser parser = new Parser();

        String userInput = "";
        Task t ;

        List<Task> userTask = new ArrayList<>();

        List<TaskList> tasks;

        while (!userInput.equals("bye")) {
            //Read user input.
            userInput = sc.nextLine();
            try {
                //Exit the chatbot.
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    //List out all the elements stored.
                } else if (parser.isListCommand(userInput)) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userTask.size(); i++) {
                        System.out.println((i + 1) + ". " + userTask.get(i));
                    }
                    //Store user input and echo out.
                } else if (parser.isDeleteCommand(userInput)) {
                    //Deletes a task.
                    updateTask(userInput, userTask, "delete");
                } else if (parser.isMarkCommand(userInput)) {
                    //Marks a task as done.
                    updateTask(userInput, userTask, "mark");

                } else if (parser.isUnmarkCommand(userInput)) {
                    //unmarks a task as not done.
                    updateTask(userInput, userTask, "unmark");

                } else if (parser.isToDoCommand(userInput)) {
                    //Create to-do task
                    //updateTask(userInput, userTask, "todo");
                    t = parser.createToDo(userInput);
                    userTask.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(userTask.size()-1));
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");

                } else if (parser.isDeadlineCommand(userInput)) {
                    //Create Deadline task
                   // updateTask(userInput, userTask, "deadline");
                    t = parser.createDeadline(userInput);
                    userTask.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(userTask.size()-1));
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");

                } else if (parser.isEventCommand(userInput)) {
                    //Create event task
                    //updateTask(userInput, userTask, "event");
                    t = parser.createEvent(userInput);
                    userTask.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(userTask.get(userTask.size()-1));
                    System.out.println("Now you have " + userTask.size() + " tasks in the list.");

                } else {

                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            } catch (Exception e) {

            }
        }
    }

}
