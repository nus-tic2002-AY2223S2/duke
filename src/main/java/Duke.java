import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void updateTask(String userInput,List<Task> userTask,String status){
        int value = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
        int listNumber = value - 1;

        switch(status){
            case "mark":
                userTask.get(listNumber).setStatusIconMarked();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + userTask.get(listNumber).getStatusIcon() + "] " + userTask.get(listNumber).description);
                break;
            case "unmark":
                userTask.get(listNumber).setStatusIconUnmarked();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + userTask.get(listNumber).getStatusIcon() + "] " + userTask.get(listNumber).description);
                break;
            default:
                break;
        }


    }
    public static void main(String[] args) {
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

            //Exit the chatbot.
            if (userInput.equals("bye")) {

                System.out.println("Bye. Hope to see you again soon!");

                //List out all the elements stored.
            } else if (userInput.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < userTask.size(); i++) {

                    System.out.println((i + 1) + ". [" + userTask.get(i).getStatusIcon() + "] " + userTask.get(i).description);

                }
                //Store user input and echo out.
            } else if (userInput.startsWith("mark")) {
                //Marks a task as done.
                updateTask(userInput, userTask,"mark");

            } else if (userInput.startsWith("unmark")) {
                //unmarks a task as not done.
                updateTask(userInput, userTask,"unmark");

            } else {
                //accepts as a task only if there is input.
                if (!userInput.equals("")) {
                    userTask.add(new Task(userInput));
                }

            }
        }
    }
}
