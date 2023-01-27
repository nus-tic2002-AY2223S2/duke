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
        List<String> userList = new ArrayList<>();
        List<Task> userTask = new ArrayList<>();
        while (!userInput.equals("bye")) {
           Task t = new Task("read book");
           System.out.println(t.getStatusIcon());

            userInput = sc.nextLine();

            //Exit the chatbot.
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

                //List out all the elements stored.
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
               for (int i = 0; i < userTask.size();i++){
                   System.out.println((i+1) + ". [" + userTask.get(i).getStatusIcon() + "] " + userTask.get(i).description);

               }
               /**
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i+1) + ". [" + t.getStatusIcon() + "] " + userList.get(i));
                }
                */
                //Store user input and echo out.
            } else if (userInput.contains("mark")) {
                int value = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));

                System.out.println("test mark: " + value);
                userTask.get(value - 1).setStatusIcon(value - 1);

            }else {
                if (userInput.equals("")){

                }else {
                    userTask.add(new Task(userInput));
                    userList.add(userInput);
                    System.out.println(userInput);
                }

            }
        }
    }
}
