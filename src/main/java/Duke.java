import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\n1Hello! I'm Duke");
        readLine();
    }
    public static void print(String command) {

        String goodBye = "BYE";
        boolean isSame = command.toUpperCase().equals(goodBye);

        if (isSame) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            System.out.println(command);
            readLine();
        }

    }

    public static void readLine() {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("\nWhat can I do for you?\n");
        userInput = in.nextLine();
        print(userInput);

    }
}
