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

        while (!userInput.equals("bye")) {

            userInput = sc.nextLine();

            //Exit the chatbot.
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

                //List out all the elements stored.
            } else if (userInput.equals("list")) {
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i+1) + ". " + userList.get(i));
                }
                //Store user input and echo out.
            } else {
                userList.add(userInput);
                System.out.println(userInput);
            }
        }
    }
}
