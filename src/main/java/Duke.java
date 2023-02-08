import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);
        int counter = 1;
        String toAdd;

        String goodBye = "BYE";
        String lists = "LIST";
        String mark = "[X] ";
        String unmark = "[ ] ";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke");

        String[] list = new String[100];

        for (int i = 0; i < 100; i++) {
            System.out.println("\nWhat can I do for you?\n");
            userInput = in.nextLine();

            boolean isSame = userInput.toUpperCase().equals(goodBye);
            boolean isSame_list = userInput.toUpperCase().equals(lists);

            if (isSame) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (isSame_list) {
                // displayList(list, listCounter);
                int listCounter = 0;
                System.out.println("Here are the tasks in your list:");
                for (String l : list) {
                    if (listCounter == counter - 1) {
                    } else {
                        System.out.println(l);
                        listCounter++;
                    }
                }
                i--;
            }
            else if (userInput.toUpperCase().contains("MARK")) {

                int dividerPosition = userInput.indexOf(" ");
                String type = userInput.substring(0, dividerPosition);
                String itemName = userInput.substring(dividerPosition, userInput.length());
                itemName = itemName.replace(" ", "");
                int itemNum = Integer.parseInt(itemName);

                if (type.toUpperCase().equals("MARK")) {

                    list[itemNum - 1] = list[itemNum - 1].replace(unmark, mark);
                    System.out.println("Nice! I've marked this task as done:");
                    int pos = list[itemNum - 1].indexOf(".");
                    String marked = list[itemNum - 1].substring(pos, list[itemNum - 1].length()).replace(".", " ");
                    System.out.println(marked);
                }
                else {

                    list[itemNum - 1] = list[itemNum - 1].replace(mark, unmark);
                    System.out.println("OK, I've marked this task as not done yet:");
                    int pos = list[itemNum - 1].indexOf(".");
                    String marked = list[itemNum - 1].substring(pos, list[itemNum - 1].length()).replace(".", " ");
                    System.out.println(marked);
                }
                i--;
            }
            else {
                toAdd = counter + "." + unmark + userInput;
                list[i] = toAdd;
                System.out.println("Added:" + userInput);
                counter++;
            }
        }
    }
}

