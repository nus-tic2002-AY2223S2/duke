import java.util.*;
public class Duke {
    public static void main(String[] args) {

        String borderLine = "____________________________________________________________";
        String greetings = "Hello! I'm Duke \n\tWhat can I do for you";
        System.out.printf("\t%s\n\t%s\n\t%s\n",borderLine, greetings, borderLine);

        ArrayList itemList = new ArrayList();
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.print("Enter input : ");
            String userInput = input.nextLine().trim();
            String lineItem = "";

            if("list".equalsIgnoreCase(userInput)) {
                for(int i=0; i<itemList.size(); i++) {
                    if(!"list".equalsIgnoreCase(itemList.get(i).toString())) {
                        lineItem += "\t" + (i + 1) + ". " + itemList.get(i);
                    }
                }
                lineItem.trim();
            } else {
                lineItem = "\t" + userInput + "\n";
                itemList.add(lineItem);
            }

            if("bye".equalsIgnoreCase(userInput)) {
                String message = "Bye. Hope to see you again soon!";
                System.out.printf("\t%s\n\t%s\n\t%s\n",borderLine, message, borderLine);
                break;
            }

            System.out.printf("\t%s\n%s\t%s\n",borderLine, lineItem, borderLine);
        }
    }
}
