import java.util.Scanner;
import java.util.Arrays;

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
            String command = in.nextLine().trim().toLowerCase();
            String[] givenCommand = command.split(" ");
            String firstCommand = givenCommand[0];

            switch(firstCommand) {
                case "":
                    //if command is empty, call for other suggestions
                    help();
                    break;
                case "list": /*Level 2*/
                    //Print out all the tasks in the list
                    lists(tasks, countTasks);
                    break;
                case "bye":
                    //To exit the program
                    System.out.println("Bye! Hope to see you again soon!");
                    toRun = false;
                    break;
                case "mark": /* Level 3*/
                case "unmark":
                    // to mark or not mark a certain task
                    String positionNo = givenCommand[1].trim();
                    int digit = Integer.parseInt(positionNo)-1;
                    boolean toMark = false;
                    //catch any errors
                    try {
                        if (firstCommand.equals("mark")) {
                            toMark = true;
                            status(tasks, toMark, digit);
                        } else if (firstCommand.equals("unmark")) {
                            status(tasks, toMark, digit);
                        }
                    } catch (IndexOutOfBoundsException e) { //if number given> countTasks
                        System.out.println("Please give a valid task number that is within the list");
                    } catch (NumberFormatException e) {
                        System.out.println("Please give a task number to edit");
                    }
                    break;
                case "deadline" :
                    String[] givenBy = command.split("/by"); // split the command at /by
                    String lastDay = givenBy[1]; // get the due date/day
                    String deadlineTask = givenBy[0].replace("deadline","").trim(); //remove deadline to get the task
                    Task newTask = new Deadline(deadlineTask, lastDay);
                    storeTask(tasks, newTask, countTasks); //stores the new task into the list of tasks
                    ++countTasks;
                    break;
                case "todo" :
                    String todoTask = command.replace("deadline",""); //remove deadline to get the task
                    Task newtodoTask = new ToDo(todoTask);
                    storeTask(tasks, newtodoTask, countTasks); //stores the new task into the list of tasks
                    ++countTasks;
                    break;
                case "event" :
                    String[] givenFrom = command.split("/from"); // split the command at /from
                    String eventTask = givenFrom[0].replace("event", "").trim();//remove event to get the task
                    String[] fromTo = givenFrom[1].split("/to"); //split at /to to get from and to
                    String fromEvent = fromTo[0].trim(); //get the from
                    String toEvent = fromTo[1].trim(); // get the to
                    Task newEventTask = new Event(eventTask, fromEvent, toEvent);
                    storeTask(tasks, newEventTask, countTasks); //stores the new task into the list of tasks
                    ++countTasks;
                    break;

            }
        }

    }
    //store into array
    public static void storeTask(Task[] yourTasks, Task newTask, int countTasks){
        yourTasks[countTasks] = newTask;
        //print message
        addedTask(newTask, countTasks);
    }
    //end of storing into array

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

    public static void addedTask(Task chosenTask, int count){
        System.out.println("Got it. I've add this task:");
        System.out.println(chosenTask);
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    //messages END


}
