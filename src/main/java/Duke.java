public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        //Level-1
        System.out.println("Hello! I'm Duke, the chatbot");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        if (command == "bye") {
            System.out.println("Bye! Hope to see you again soon!");
        } else {
            System.out.println(command);
        }
    }
}
