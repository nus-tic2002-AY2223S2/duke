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
                    System.out.println(i+1 + ".[" + lists.get(i).getTaskStatus() + "] " + lists.get(i).description);
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
                System.out.println("  [" + lists.get(indexInList).getTaskStatus() + "] " + lists.get(indexInList).description);

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
                System.out.println("  [" + lists.get(indexInList).getTaskStatus() + "] " + lists.get(indexInList).description);
            }
            else
            {
                Task newTask = new Task(input);
                lists.add(newTask);
                System.out.println("added: " + input);
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
