import java.util.Scanner;

public class Duke {
    public static String[] list = new String[100];
    static int counter = 1;
    static String mark = "[X] ";
    static String unmark = "[ ] ";

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        String goodBye = "BYE";
        String lists = "LIST";
        String marked = "MARK";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke");

        for (int i = 0; i < 100; i++) {
            System.out.println("\nWhat can I do for you?\n");
            userInput = in.nextLine();

            if (userInput.equalsIgnoreCase(goodBye)) { // User Input: BYE

                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (userInput.equalsIgnoreCase(lists)) { // User Input: LIST
                printList(i);
                i--;

            } else if (userInput.toUpperCase().contains("MARK")) { // User Input contains MARK or UNMARK

                int dividerPosition = userInput.indexOf(" ");
                String type = userInput.substring(0, dividerPosition);
                String itemName = userInput.substring(dividerPosition, userInput.length());
                itemName = itemName.replace(" ", "");
                int itemNum = Integer.parseInt(itemName);

                if (type.equalsIgnoreCase(marked)){ // User Input equals MARK
                    i--;
                    setToMark(itemNum);
                }
                else { // User Input equals UNMARK
                    i--;
                    setToUnmark(itemNum);
                }

            }  else {
                addToList(userInput);
            }
        }
    }

    public static void addToList(String a) {
        String toAdd = counter + "." + unmark + a;
        list[counter - 1] = toAdd;
        System.out.println("Added: " + a);
        counter++;
    }

    public static void printList(int a) {
        for (int i = 0; i < a; i++) {
            System.out.println(list[i]);
        }
    }

    public static void setToMark(int num) {

        list[num - 1] = list[num - 1].replace(unmark, mark);
        System.out.println("Nice! I've marked this task as done:");
        int pos = list[num - 1].indexOf(".");
        String marked = list[num - 1].substring(pos, list[num - 1].length()).replace(".", " ");
        System.out.println(marked);
    }

    public static void setToUnmark(int num) {

        list[num - 1] = list[num - 1].replace(mark, unmark);
        System.out.println("OK, I've marked this task as not done yet:");
        int pos = list[num - 1].indexOf(".");
        String marked = list[num - 1].substring(pos, list[num - 1].length()).replace(".", " ");
        System.out.println(marked);

    }
}
