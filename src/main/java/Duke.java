public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        //Start of chatbot
        System.out.println("Hello! I'm Duke, the chatbot");
        System.out.println("What can I do for you?");
        //Create new array of tasks
        String tasks[] = new String[100];
        //Create int count
         int countTasks = 0;
         boolean toRun = true;
        //Take an input/command
        while(toRun) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine().trim();

            if (command.isEmpty()) {
                //if command is empty, call for other suggestions
                System.out.println("Hi, it looks like you did not type anything.");
                System.out.println("You may try the following commands:");
                System.out.println("Enter List -> to list all the tasks");
                System.out.println("Enter Bye -> to end the conversation");
            } else if (command.equalsIgnoreCase("List")) {
                //Print out all the tasks in the array
                //need to check
                /*if (tasks[0].isEmpty()) {
                    System.out.println("Nothing is added to the list");
                    continue;
                } else {*/
                    for (int i = 0; i < countTasks; i++) {
                        int numbers = 1;
                        System.out.println(numbers + ". " + tasks[i]);
                    }
                //}
            } else if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                toRun = false;
            } else {
                //store into array
                tasks[countTasks] = command;
                ++countTasks;
                System.out.println("Added: " + command);
            }
        }
    }
}
