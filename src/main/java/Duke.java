////////////////////////////////////////////////
//  DONE BY: A0227169X; ANG JIA JIN, GABRIEL  //
////////////////////////////////////////////////
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Helper method                                                                                              //
    //  isNumber() method will check input if it is a number                                                       //
    //  Returns TRUE if it is a number; FALSE if it is a String                                                    //
    //  Logic:      Integer.parseInt will throw NumberFormatException if it is unable to convert input to Integer  //
    //              Once NumberFormatException is caught, return FALSE as it is not a Number                       //
    //  Reference:  https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java        //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isNumber(String strToCheck) {
        try {
            Integer.parseInt(strToCheck);
            return true;
        }
        catch(NumberFormatException exception)
        {
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //  Helper method                                                                                //
    //  checkInputForMarkAction() method will return TRUE if needs to execute Mark function          //
    //  Assumption: Mark function will only contain input with 2 words; 'Mark' followed by a number  //
    //              Anything besides that will be treated as a Task to be added to the list          //
    //              E.G. 'Mark Dairy' - goes to List                                                 //
    //              E.G. 'Mark 2' - perform Mark function                                            //
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean checkInputForMarkAction(String load)
    {
        String[] separatedLoad = load.split(" ");
        if (separatedLoad[0].equalsIgnoreCase("mark") && separatedLoad.length == 2 && isNumber(separatedLoad[1]))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Helper method                                                                                    //
    //  checkInputForUnmarkAction() method will return TRUE if needs to execute Unmark function          //
    //  Assumption: Unmark function will only contain input with 2 Words; 'Unmark' followed by a number  //
    //              Anything besides that will be treated as a Task to be added to the list              //
    //              E.G. 'Unmark Dairy' - goes to List                                                   //
    //              E.G. 'Unmark 2' - perform Unmark function                                            //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean checkInputForUnmarkAction(String load)
    {
        String[] separatedLoad = load.split(" ");
        if (separatedLoad[0].equalsIgnoreCase("unmark") && separatedLoad.length == 2 && isNumber(separatedLoad[1]))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) {

        //variable initialization
        Scanner scanObj = new Scanner(System.in); //scanner
        var lists = new ArrayList<Task>(); //string initialize Task arraylist

/*        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");

        // Get input from user
        String input = scanObj.nextLine();

        // Main loop of the programme; exit upon "Bye" input
        while(!input.equals("bye"))
        {
            System.out.println("____________________________________________________________");
            if(input.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < lists.size(); i++)
                {
                    System.out.println(i+1 + "." + lists.get(i).toString());
                }
            }
            else if (checkInputForMarkAction(input))
            {
                //This condition checks if the Mark Action needs to be executed

                //retrieve the index to be marked
                String[] separatedReturn = input.split(" ");
                int indexInList = Integer.parseInt(separatedReturn[1]) - 1 ;

                //set Status of Task to Done
                lists.get(indexInList).markAsDone();

                //Print output as expected
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(lists.get(indexInList).toString());

            }
            else if (checkInputForUnmarkAction(input))
            {
                //This condition checks if the Unmark Action needs to be executed

                //retrieve the index to be unmarked
                String[] separatedReturn = input.split(" ");
                int indexInList = Integer.parseInt(separatedReturn[1]) - 1 ;

                //set Status of Task to Undone
                lists.get(indexInList).markAsUndone();

                //Print output as expected
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(lists.get(indexInList).toString());
            }
            else
            {
                //time to start adding DATELINES / TODOS / EVENTS
                String[] separatedString = input.split(" ", 2); //by doing so, everything after the first space will be stored in the last index of the array
                if(separatedString[0].equalsIgnoreCase("deadline"))
                {
                    //Using indexOf method to extract description & dateline
                    //nextSeparated array will store value in such index
                    //nextSeparated[0] = description;
                    //nextSeparated[1] = deadline;
                    String [] nextSeparated = separatedString[1].split("/by");

                    Deadline newDeadline = new Deadline(nextSeparated[0].trim(), nextSeparated[1].trim());
                    lists.add(newDeadline);
                    System.out.println("got it. I've added this task:\n" + newDeadline.toString());
                }
                else if(separatedString[0].equalsIgnoreCase("event"))
                {
                    //Using indexOf method to extract description & start/end timing
                    //nextSeparated array will store value in such index
                    //nextSeparated[0] = description;
                    //nextSeparated[1] = start/end timing;
                    String [] nextSeparated = separatedString[1].split("/from");

                    //Using indexOf method again to extract start & end timing
                    //nextSeparated array will store value in such index
                    //separatedTiming[0] = start timing;
                    //separatedTiming[1] = end timing;
                    String [] separatedTiming = nextSeparated[1].split("/to");

                    Event newEvent = new Event(nextSeparated[0].trim(), separatedTiming[0].trim(), separatedTiming[1].trim());
                    lists.add(newEvent);
                    System.out.println("got it. I've added this task:\n" + newEvent.toString());
                }
                else
                {
                    //Assuming not "deadline" & "event" means "to-do"
                    Todo newTodo = new Todo(input);
                    lists.add(newTodo);
                    System.out.println("got it. I've added this task:\n" + newTodo.toString());
                }
                System.out.println("Now you have " + lists.size() + " task(s) in the list");
            }
            System.out.println("____________________________________________________________\n");
            input = scanObj.nextLine();
        }

        // Motherhood Statement when bye is key-ed in
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        // End of programme

    }
}
