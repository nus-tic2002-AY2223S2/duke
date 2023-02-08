//////////////////////////////////////////////////////////////////////
// DONE BY: A0227169X; ANG JIA JIN, GABRIEL
//////////////////////////////////////////////////////////////////////
import java.util.Scanner;  // Import Scanner class

public class Duke {
    public static void main(String[] args) {
        //variable initialization
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
        String input = scanObj.nextLine();

        // Main loop of the programme
        while(!input.equals("bye"))
        {
            System.out.println(input);
            input = scanObj.nextLine();
        }

        // Motherhood Statement when bye is key-ed in
        System.out.println("Bye. Hope to see you again soon!");
        // End of programme

    }
}
