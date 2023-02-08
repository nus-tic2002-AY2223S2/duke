import java.util.Scanner;  // Import Scanner class

public class Duke {
    public static void main(String[] args) {
        //variable initialization
        boolean factor = true;
        Scanner scanObj = new Scanner(System.in);

/*        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Get input from user
        while(factor)
        {
            String input = scanObj.nextLine();
            if(input.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                factor = false;
            }
            else
            {
                System.out.println(input);
            }
        }




    }
}
