import java.util.Scanner;


public class Duke {
    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        boolean bye = false;
        Scanner Obj = new Scanner(System.in);
        int taskIndex = 0;
        Task[] list = new Task[100];


        while (!bye) {
            String question = Obj.nextLine();

            if (question.equals("bye")) { //if the input is bye then end the program
                bye = true;
                System.out.println("Bye. Hope to see you again soon!");
            }

            if (question.contains("mark")) {
                if (checkMark(question)) {
                    String[] splitted = question.split(" ");
                    if (question.contains("unmark")) {
                        list[Integer.parseInt(splitted[1]) - 1].markAsNotDone();
                        continue;
                    }
                    else {
                        list[Integer.parseInt(splitted[1]) - 1].markAsDone();
                        continue;
                    }
                }
            }

            if (question.equals("list")) { //loop to print out the items in list[]
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println(i + 1 + ". " + "[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
            }
            else { //store the string
                Task t = new Task(question);
                //t.markAsDone();
                list[taskIndex] = t;
                taskIndex++;
                System.out.println("added: " + question);
            }
        }
    }
}
