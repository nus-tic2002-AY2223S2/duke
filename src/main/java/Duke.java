import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc=new Scanner(System.in);

        System.out.println(logo + "\nHello I'm Duke");
        System.out.println("What can i do for you?");

        String userInput = "";

        while(!userInput.equals("bye")){

            userInput= sc.nextLine();

            if (userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            }else {
                System.out.println(userInput);
            }


        }



    }
}
