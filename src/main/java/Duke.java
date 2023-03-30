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

        String line = " ";
        Scanner in = new Scanner(System.in);

        String[] storage = new String[100];
        int inputCount = 0;
        while(!line.equals("bye")) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
            }
            else if (line.equals("list")) {
                for(int i = 0; i < inputCount; i++) {
                    System.out.println(i+1 + ". " + storage[i]);
                }
            }
            else {
                storage[inputCount] = line;
                inputCount++;
                System.out.println("added: " + line);
            };
        }
    }
}

