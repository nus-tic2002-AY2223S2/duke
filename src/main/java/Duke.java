import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line;

        // instantiate new empty ArrayList
        ArrayList<String> lists = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello. I'm Duke.\nWhat can i do for you?");

        // use .equals to compare string
        // if bye then exit()
        // other than bye than print the statement
        do {
            line = in.nextLine();
            if (line.equals("bye")) {
                continue;
            }
            else if (line.equals("list")) {
                System.out.println("_________________________________________");
                for (int i = 1; i < lists.size() + 1; i++) {
                    System.out.println(i + ". " + lists.get(i - 1));
                }
                System.out.println("_________________________________________");
            }
            else {
                System.out.println("_________________________________________\n added:" + line + "\n_________________________________________\n");
                lists.add(line);
            }
        } while (!line.equals("bye"));
        System.out.println("_________________________________________\n Bye. Hope to see you soon again! \n_________________________________________\n");
    }
}
