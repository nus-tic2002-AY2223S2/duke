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
        Task[] tasks = new Task[100];
        //Create int count
        int countTasks = 0;
        boolean toRun = true;
        //Take an input/command
        while(toRun) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine().trim();

            if (command.isEmpty()) {
                //if command is empty, call for other suggestions
                help();
            } else if (command.equalsIgnoreCase("List")) {
                //Print out all the tasks in the list
                lists(tasks, countTasks);
            //To exit the program
            } else if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                toRun = false;
            } else if (command.toLowerCase().startsWith("mark") || command.toLowerCase().startsWith("unmark")) {
                // to mark or not mark a certain task
                String[] splitCommand = command.split(" ");
                String statusName = splitCommand[0].trim().toLowerCase();
                String positionNo = splitCommand[1].trim();
                int digit = Integer.parseInt(positionNo)-1;
                boolean toMark = false;
                try {
                    if (statusName.equals("mark")) {
                        toMark = true;
                        status(tasks, toMark, digit);
                    } else if (statusName.equals("unmark")) {
                        status(tasks, toMark, digit);
                    }
                } catch (IndexOutOfBoundsException e) { //if number given> countTasks
                    System.out.println("Please give a valid task number that is within the list");
                } catch (NumberFormatException e) {
                    System.out.println("Please give a task number to edit");
                }
            } else {
                //store into array
                Task newtask = new Task(command);
                tasks[countTasks] = newtask;
                ++countTasks;
                System.out.println("Added: " + command);
            }
        }

    }

    //messages START
    public static void help(){
        System.out.println("Hi, it looks like you did not type anything.");
        System.out.println("You may try the following commands:");
        System.out.println("Enter List -> to list all the tasks");
        System.out.println("Enter Bye -> to end the conversation");
    }
    public static void lists(Task[] listOfTasks, int countTasks){
        int listNo=0;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < countTasks; i++) {
            ++listNo;
            System.out.println(listNo + ". " + listOfTasks[i].toString());
        }
    }
    public static void status(Task[] chosenTask, boolean toMark, int digit) {
        if(toMark) {
            chosenTask[digit].mark();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            chosenTask[digit].notMark();
            System.out.println("Ok, I've marked this task as not done yet:");
        }
        toMark = false;
        String list = Integer.toString(digit+1);
        System.out.println(list + ". " + chosenTask[digit]);
    }

    //messages END
}
