import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello. I'm Duke.\nWhat can i do for you?");

        // use .equals to compare string
        // if bye then exit()
        // other than bye than print the statement
        do {
            line = in.nextLine();
            if (!line.equals("bye")) {
                System.out.println("_________________________________________\n" + line + "\n_________________________________________\n");
            }
        } while (!line.equals("bye"));
        System.out.println("_________________________________________\n Bye. Hope to see you soon again! \n_________________________________________\n");
    }
}
