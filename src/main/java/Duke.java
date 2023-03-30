import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("So... how can I assist you?");


        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        System.out.println(line);
    }
}

