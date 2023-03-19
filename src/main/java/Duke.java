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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Helper method                                                                                    //
    //  identifyFunctionsValidateInput() method will perform 2 actions:                                  //
    //  1) return Functions and the parameters                                                           //
    //  functions: "Deadline" "Event" "To-Do" "Mark" "Unmark"                                            //
    //  Outcome: separatedInput[0] = Functions                                                           //
    //           separatedInput[1] = Parameters                                                          //
    //                                                                                                   //
    //  2) validates the input to catch all kinds of error such as                                       //
    //  - Empty Parameters                                                                               //
    //  - Invalid functions                                                                              //
    //  Deadline functions:                                                                              //
    //  - Missing /by parameters                                                                         //
    //  - Missing deadline                                                                               //
    //  Events functions:                                                                                //
    //  - Missing /from & /to parameters                                                                 //
    //  - Missing duration                                                                               //
    //  Mark / Unmark / Delete functions:                                                                //
    //  - Missing value for mark & unmark                                                                //
    //  - Too much values for mark & unmark                                                              //
    //  - Index over the List size                                                                       //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String[] identifyFunctionsValidateInput(String stringInput, ArrayList currList) throws DukeException
    {
        String[] separatedInput = stringInput.split(" ", 2);
        if(separatedInput[0].equalsIgnoreCase("Deadline"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else if (!separatedInput[1].contains("/by"))
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a /by for deadline");
            }
            else if(separatedInput[1].split("/by").length < 2)
            {
                throw new DukeException("☹ OOPS!!! " + separatedInput[0] + " is missing a deadline");
            }
            else
            {
                return separatedInput;
            }
        }
        else if(separatedInput[0].equalsIgnoreCase("Event"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else
            {
                return separatedInput;
            }
        }
        else if(separatedInput[0].equalsIgnoreCase("Todo"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The description of a " + separatedInput[0] + " cannot be empty.");
            }
            else
            {
                return separatedInput;
            }
        }
        else if (separatedInput[0].equalsIgnoreCase("Mark") || separatedInput[0].equalsIgnoreCase("Unmark") || separatedInput[0].equalsIgnoreCase("Delete"))
        {
            if (separatedInput.length < 2)
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0] + " cannot be empty.");
            }
            else if (separatedInput.length > 2)
            {
                throw new DukeException("☹ OOPS!!! There can only by 1 value after " + separatedInput[0] + ".");
            }
            else if (!isNumber(separatedInput[1]))
            {
                throw new DukeException("☹ OOPS!!! The value after " + separatedInput[0] + " must be a number.");
            }
            else if (Integer.parseInt(separatedInput[1]) > currList.size())
            {
                throw new DukeException("☹ OOPS!!! The index after command " + separatedInput[0] + " is over the list.");
            }
            else
            {
                return separatedInput;
            }
        }
        else
        {
            //any other starting words besides the functions list, throws error
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
                try{
                    String[] separatedReturn = identifyFunctionsValidateInput(input, lists);
                    int indexInList = Integer.parseInt(separatedReturn[1]) - 1 ;

                    //set Status of Task to Done
                    lists.get(indexInList).markAsDone();

                    //Print output as expected
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(lists.get(indexInList).toString());

                }
                catch (DukeException de)
                {
                    System.out.println("Caught: " + de);
                }

            }
            else if (checkInputForUnmarkAction(input)) {
                //This condition checks if the Unmark Action needs to be executed

                //retrieve the index to be unmarked
                try
                {
                    String[] separatedReturn = identifyFunctionsValidateInput(input, lists);
                    int indexInList = Integer.parseInt(separatedReturn[1]) - 1;

                    //set Status of Task to Undone
                    lists.get(indexInList).markAsUndone();

                    //Print output as expected
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(lists.get(indexInList).toString());
                }
                catch (DukeException de)
                {
                    System.out.println("Caught: " + de);
                }
            }
            else
            {
                //time to start adding DATELINES / TODOS / EVENTS
                try{
                    //this method [identifyFunctionsValidateInput] will validate most commonly seen error and handle it within the method
                    String[] separatedString = identifyFunctionsValidateInput(input, lists);
                    //by doing so, everything after the first space will be stored in the last index of the array
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
                    else if (separatedString[0].equalsIgnoreCase("todo"))
                    {
                        Todo newTodo = new Todo(separatedString[1]);
                        lists.add(newTodo);
                        System.out.println("got it. I've added this task:\n" + newTodo.toString());
                    }
                    else if (separatedString[0].equalsIgnoreCase("delete"))
                    {
                        int indexInList = Integer.parseInt(separatedString[1]) - 1 ;
                        Task item = lists.get(indexInList);
                        lists.remove(indexInList);
                        System.out.println("Noted. I've removed this task:\n" + item.toString());
                    }
                    else
                    {
                        System.out.println(separatedString[0] + " is unknown!");
                    }
                    System.out.println("Now you have " + lists.size() + " task(s) in the list");
                }
                catch (DukeException de)
                {
                    System.out.println("Caught: " + de);
                }
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
