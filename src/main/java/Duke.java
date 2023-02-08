import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        boolean notBye = true;
        Scanner Obj = new Scanner(System.in);
        int stringIndex = 0;
        String[] list = new String[100];
        while (notBye) {
            String question = Obj.nextLine();
            if (question.equals("bye")) { //if the input is bye then end the program
                notBye = false;
                System.out.println("Bye. Hope to see you again soon!");
                return; // stop the program and not continue
            }
            else if (!question.equals("list")){ //store the string except "list"
                list[stringIndex] = question;
                stringIndex++;
                System.out.println("added: " + question);
            }
            else if (question.equals("list")) { //loop to print out the items in list[]
                for (int i = 0; i < stringIndex; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
            }
        }


    }
}
