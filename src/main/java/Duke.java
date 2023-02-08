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

        while (notBye) {
            String question = Obj.nextLine();
            if (question.equals("bye")) {
                notBye = false;
                System.out.println("Bye. Hope to see you again soon!");
            }
            else {
                System.out.println(question);
            }

        }


    }
}
