/**
*  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
*/

package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /********************************************/
    private Ui ui;
    private TaskList tasks;

    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isItBye = false;
        while (!isItBye) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isItBye = c.isItBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }


        /**************************to be extracted one by one******************/

/*        //variable initialization
        Scanner scanObj = new Scanner(System.in); //scanner Extracted
        var lists = new ArrayList<Task>(); //string initialize duke.Task arraylist //Extracted

        // Get input from user
        String input = scanObj.nextLine(); //Extracted

        // Main loop of the programme; exit upon "Bye" input
        while(!input.equals("bye"))
        {
            System.out.println(uiDivider);
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

                    //set Status of duke.Task to Done
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
            else if (checkInputForUnmarkAction(input))
            {
                //This condition checks if the Unmark Action needs to be executed

                //retrieve the index to be unmarked
                try
                {
                    String[] separatedReturn = identifyFunctionsValidateInput(input, lists);
                    int indexInList = Integer.parseInt(separatedReturn[1]) - 1;

                    //set Status of duke.Task to Undone
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
                        System.out.println("Got it. I've added this task:\n" + newDeadline.toString());
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
                        System.out.println("Got it. I've added this task:\n" + newEvent.toString());
                    }
                    else if (separatedString[0].equalsIgnoreCase("todo"))
                    {
                        Todo newTodo = new Todo(separatedString[1]);
                        lists.add(newTodo);
                        System.out.println("Got it. I've added this task:\n" + newTodo.toString());
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
            System.out.println(uiDivider + "\n");
            input = scanObj.nextLine();
        }

        // Motherhood Statement when bye is key-ed in
        System.out.println(uiDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(uiDivider);
        // End of programme*/


        /**************************end of extract 1 by 1******************/
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
