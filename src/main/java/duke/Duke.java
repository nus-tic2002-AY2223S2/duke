package duke;

import java.util.Scanner;
import java.util.ArrayList;
import duke.Ui.Ui;
import duke.Storage;

/*
**Helper function to check is mark/unmark is a single word or with something behind
**/
public class Duke {
    private Ui ui;
    //private TaskList tasks;
    private duke.Storage storage;

    public static boolean checkMark(String input) {
        String[] splitted = input.split(" ");
        return splitted.length == 2;
    }


    public static void validateQuestion(String input, ArrayList list) throws DukeException {

        String[] splitted = input.split(" ", 2);
        if (!splitted[0].equalsIgnoreCase("bye") && !splitted[0].equalsIgnoreCase("todo") && !splitted[0].equalsIgnoreCase("deadline") && !splitted[0].equalsIgnoreCase("event") && !splitted[0].equalsIgnoreCase("list") && !splitted[0].equalsIgnoreCase("mark") && !splitted[0].equalsIgnoreCase("unmark") && !splitted[0].equalsIgnoreCase("delete")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (splitted.length < 2 && !splitted[0].equalsIgnoreCase("list"))
        {
            throw new DukeException("☹ OOPS!!! The description of a " + splitted[0] + " cannot be empty.");
        }
        if (splitted[0].equalsIgnoreCase("mark") || splitted[0].equalsIgnoreCase("unmark"))
        {
            if ( Integer.parseInt(splitted[1]) > list.size() )
            {
                throw new DukeException("☹ OOPS!!! Index is over the size of items in the list");
            }
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new duke.Storage();
//        try {
//            tasks = new duke.TaskList(storage.load());
//        } catch (duke.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }


    public void run() {
        ui.showWelcome();

        boolean isBye = false;
        Scanner Obj = new Scanner(System.in);
        //ArrayList<Task> list = new ArrayList<Task>();
        Storage loader = new Storage(); //new
        ArrayList<Task> list = loader.loadFile(); //new

        while (!isBye) {
            String question = Obj.nextLine();

            if (question.equalsIgnoreCase("bye")) { //if the input is bye then end the program
                isBye = true;
                System.out.println("Bye. Hope to see you again soon!");
                continue;
            }

            if (question.equalsIgnoreCase("list")) { //loop to print out the items in list[]
                System.out.println("________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i).toString());
                }
                System.out.println("________________________________________________");
                continue;
            }

            try {
                validateQuestion(question, list);
            }
            catch(DukeException error) {
                System.out.println(error);
                continue;
            }
            if (question.contains("mark")) {
                if (checkMark(question)) {
                    String[] splitted = question.split(" ");
                    int num = Integer.parseInt(splitted[1]) - 1;
                    if (question.contains("unmark")) {

                        list.get(num).markAsNotDone();
                        System.out.println("Item " + (num + 1) + " is not done.");
                        continue;
                    }
                    else {
                        list.get(num).markAsDone();
                        System.out.println("Item " + (num + 1) + " is done.");
                        continue;
                    }
                }
            }

            else {
                String[] splitted = question.split(" ", 2);

                //catch for "to-do"
                if (splitted[0].equalsIgnoreCase("todo")) {
                    Todo td  = new Todo(splitted[1]);
                    list.add(td);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(td);
                }

                //catch for "deadline"
                else if (splitted[0].equalsIgnoreCase("deadline")) {
                    String[] deadlineSplit = splitted[1].split("/by");
                    Deadline dl = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    list.add(dl);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(dl);
                }

                //catch for "event"
                else if (splitted[0].equalsIgnoreCase("event")) {
                    String[] eventSplit = splitted[1].split("/from");
                    String[] eventSplitAgain = eventSplit[1].split("/to");
                    Event ev = new Event(eventSplit[0], eventSplitAgain[0], eventSplitAgain[1]);
                    list.add(ev);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(ev);
                }
                //catch for delete
                else if (splitted[0].equalsIgnoreCase("delete")) {
                    Task toDelete = list.get(Integer.parseInt(splitted[1]) - 1);
                    list.remove(Integer.parseInt(splitted[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(toDelete);
                }
                else {
                    System.out.println("duke.Task not recognised");
                }
            }
            loader.saveToFile(list);
        }



//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (duke.DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
    }
    public static void main(String[] args)
    {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println("Hello from\n" + logo);
//        System.out.println("Hello! I'm duke.Duke");
//        System.out.println("What can I do for you?\n");



        new Duke().run();

//        boolean isBye = false;
//        Scanner Obj = new Scanner(System.in);
//        ArrayList<duke.Task> list = new ArrayList<duke.Task>();
//
//        while (!isBye)
//        {
//            String question = Obj.nextLine();
//
//            if (question.equalsIgnoreCase("bye"))
//            { //if the input is bye then end the program
//                isBye = true;
//                System.out.println("Bye. Hope to see you again soon!");
//                continue;
//            }
//
//            if (question.equalsIgnoreCase("list"))
//            { //loop to print out the items in list[]
//                System.out.println("________________________________________________");
//                System.out.println("Here are the tasks in your list:");
//                for (int i = 0; i < list.size(); i++) {
//                    System.out.println(i + 1 + ". " + list.get(i).toString());
//                }
//                System.out.println("________________________________________________");
//                continue;
//            }
//
//            try
//            {
//                validateQuestion(question, list);
//            }
//            catch(duke.DukeException error)
//            {
//                System.out.println(error);
//                continue;
//            }
//            if (question.contains("mark"))
//            {
//                if (checkMark(question))
//                {
//                    String[] splitted = question.split(" ");
//
//                    if (question.contains("unmark"))
//                    {
//                        list.get(Integer.parseInt(splitted[1]) - 1).markAsNotDone();
//                        continue;
//                    }
//                    else
//                    {
//                        list.get(Integer.parseInt(splitted[1]) - 1).markAsDone();
//                        continue;
//                    }
//                }
//            }
//
//            else
//            {
//                String[] splitted = question.split(" ", 2);
//
//                //catch for "to-do"
//                if (splitted[0].equalsIgnoreCase("todo"))
//                {
//                    duke.Todo td  = new duke.Todo(splitted[1]);
//                    list.add(td);
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(td);
//                }
//
//                //catch for "deadline"
//                else if (splitted[0].equalsIgnoreCase("deadline"))
//                {
//                    String[] deadlineSplit = splitted[1].split("/by");
//                    duke.Deadline dl = new duke.Deadline(deadlineSplit[0], deadlineSplit[1]);
//                    list.add(dl);
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(dl);
//                }
//
//                //catch for "event"
//                else if (splitted[0].equalsIgnoreCase("event"))
//                {
//                    String[] eventSplit = splitted[1].split("/from");
//                    String[] eventSplitAgain = eventSplit[1].split("/to");
//                    duke.Event ev = new duke.Event(eventSplit[0], eventSplitAgain[0], eventSplitAgain[1]);
//                    list.add(ev);
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(ev);
//                }
//                //catch for delete
//                else if (splitted[0].equalsIgnoreCase("delete"))
//                {
//                    duke.Task toDelete = list.get(Integer.parseInt(splitted[1]) - 1);
//                    list.remove(Integer.parseInt(splitted[1]) - 1);
//                    System.out.println("Noted. I've removed this task:");
//                    System.out.println(toDelete);
//                }
//                else
//                {
//                    System.out.println("duke.Task not recognised");
//                }
//            }
//        }
    }
}
