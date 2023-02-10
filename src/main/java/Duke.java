import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String borderLine = "____________________________________________________________";
        String greetings = "Hello! I'm Duke \n\tWhat can I do for you";
        System.out.printf("\t%s\n\t%s\n\t%s\n",
                borderLine, greetings, borderLine);

        Scanner input = new Scanner(System.in);
        System.out.print("Enter input : ");

        while(true) {
            String userInput = input.nextLine();
            System.out.println(userInput);
            System.out.printf("\t%s\n\t%s\n\t%s\n",
                    borderLine, userInput, borderLine);
            if("bye".equalsIgnoreCase(userInput)) {
                break;
            }
        }
    }
}
