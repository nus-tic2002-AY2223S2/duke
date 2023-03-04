import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> task = new ArrayList<String>();
        int index = task.toArray().length + 1;

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (!input.equals("list") && !input.equals("bye")) {
                task.add(input);
                index++;
                System.out.println("added:" + input);
            }

            if (input.equals("list")) {
                for (int i = 0; i < task.size(); i++)
                {
                    System.out.println(i+1 + ". " + task.get(i));
                }
            }

            if (input.equals("bye") ) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
        }
        scanner.close();
    }

}