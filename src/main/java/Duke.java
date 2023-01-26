import java.util.Scanner;

public class Duke {
    ;
    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        String goodBye = "BYE";
        String lists = "LIST";


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\n1Hello! I'm Duke");

        String[] list = new String[100];

        for (int i = 0; i < 100; i ++) {
            System.out.println("\nWhat can I do for you?\n");
            userInput = in.nextLine();

            boolean isSame = userInput.toUpperCase().equals(goodBye);
            boolean isSame_list = userInput.toUpperCase().equals(lists);

            if (isSame) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (isSame_list) {
                displayList(list);
            }
            else {
                list[i] = userInput;
                System.out.println("Added:" + userInput);
            }
        }
    }
    public static void displayList(String[] list) {
        int counter = 1;
        for (int i = 0; i < list.length; i++) {
            System.out.println(counter +":"+ list[i]);
            counter++;
            if (list[i+1] == null) {
                break;
            }
        }
    }
}

