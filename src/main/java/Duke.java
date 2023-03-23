import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) throws DukeException{
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
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<Task>();
        //Create int count
        int countTasks = 0;
        boolean toRun = true;
        //Take an input/command
        while(toRun) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine().trim().toLowerCase();
            String[] givenCommand = command.split(" ");
            String firstCommand = givenCommand[0];

            //check for any errors
           try {

               switch(firstCommand) {
                   case "help":
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
                       int digit = Integer.parseInt(positionNo)-1; // from string to integer
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
                       String deadLineTask = givenBy[0].replace("deadline", "").trim(); //remove deadline to get the task
                       Task newTask = new Deadline(deadLineTask, lastDay);
                       storeTask(tasks, newTask, countTasks); //stores the new task into the list of tasks
                       ++countTasks;
                       break;
                   case "todo" :
                       //String todoTask = command.replace("todo",""); //remove todo to get the task
                       String[] splitTask = command.split(" ",2); //split by min 2 array
                       String todoTask = splitTask[1]; //get description
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
                   case "delete" :
                       String[] splitDelete = command.split(" ",2); //split by min 2 array
                       String deleteTask = splitDelete[1].trim(); //get the position
                       int selectedNum = Integer.parseInt(deleteTask)-1; // from string to integer
                       deleteTask(tasks, selectedNum, countTasks); //deletes the selected task
                       --countTasks;
                       break;
                   default:
                       checkCommand(command);
               }
           } catch (DukeException e) {
               System.out.println("☹ OOPS!!! The description of a "  + firstCommand +" cannot be empty!");
               continue;
           } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a "  + firstCommand +" cannot be empty.");
                continue;
           } catch (IndexOutOfBoundsException e) {
               System.out.println("☹ OOPS!!! The number is either greater than the number of tasks in the list.");
               continue;
           }

        }

    }
    //store into array
    public static void storeTask(ArrayList<Task> yourTasks, Task newTask, int countTasks) {
        //yourTasks[countTasks] = newTask;
        yourTasks.add(newTask);
        //print message
        addedTask(newTask, countTasks);
    }
    //end of storing into array

    //error message START
    public static void checkCommand(String givenCommand) throws DukeException {
        String[] splitCommand = givenCommand.split(" ");

        //check if description is empty
        if (givenCommand.contains("deadline")) {
                //throw new DukeException("☹ OOPS!!! The description of a " + givenCommand+ " cannot be empty.");
                System.out.println("☹ OOPS!!! The description of a "  + givenCommand +" cannot be empty.");
        } else {
            //throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    //error message END

    //messages START
    public static void help(){
        System.out.println("Hi, it looks like you did not type anything.");
        System.out.println("You may try the following commands:");
        System.out.println("Enter List -> to list all the tasks");
        System.out.println("Enter Bye -> to end the conversation");
    }
    public static void lists(ArrayList<Task> listOfTasks, int countTasks){
        int listNo=0;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < countTasks; i++) {
            ++listNo;
            System.out.println(listNo + ". " + listOfTasks.get(i).toString());
        }
    }
    public static void status(ArrayList<Task> chosenTask, boolean toMark, int digit) {
        if(toMark) {
            //chosenTask[digit].mark();
            chosenTask.get(digit).mark();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            //chosenTask[digit].notMark();
            chosenTask.get(digit).notMark();
            System.out.println("Ok, I've marked this task as not done yet:");
        }
        toMark = false;
        String list = Integer.toString(digit+1);
        System.out.println(list + ". " + chosenTask.get(digit));
    }

    public static void addedTask(Task chosenTask, int count){
        System.out.println("Got it. I've add this task:");
        System.out.println(chosenTask);
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public static void deleteTask(ArrayList<Task> listTasks, int selectedNum, int totalTasks) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(listTasks.get(selectedNum).toString());
        listTasks.remove(selectedNum);
        --totalTasks;
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }
    //messages END

}
