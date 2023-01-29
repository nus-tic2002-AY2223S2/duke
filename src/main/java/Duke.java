import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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

                int value = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                int listNumber = value - 1;

                userTask.get(listNumber).setStatusIconMarked();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + userTask.get(listNumber).getStatusIcon() + "] " + userTask.get(listNumber).description);

            } else if (userInput.startsWith("unmark")) {
                int value = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                int listNumber = value - 1;

                userTask.get(listNumber).setStatusIconUnmarked();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + userTask.get(listNumber).getStatusIcon() + "] " + userTask.get(listNumber).description);

            } else {
                if (userInput.equals("")) {

                } else {
                    userTask.add(new Task(userInput));
                   // userList.add(userInput);
                    //System.out.println(userInput);
                }

            }
        }
    }
}
