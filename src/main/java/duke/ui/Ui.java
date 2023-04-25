package duke.ui;

import java.util.Scanner;

import duke.util.TaskList;

import java.time.LocalDateTime;

/**
 * UI class represents the user interface of duke
 * It provides methods for displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }


    /**
     * This method represents the welcome logo and Greeting of duke
     * @return string of logo and greeting
     */
    public String showWelcome() {
        return "\t Hello from Lily \u200E|•'-'•) ✧\n"
                + " \t .ᕱ⠀⠀⠀ᕱ⠀ ⠀➶ ⠀➴        \n"
                + "\t \u200E(๑◕ܫ◕๑) ➶⠀⠀⠀ ⠀➴ \n"
                + "\t \u200E૮⠀⠀⑅ ⠀づ ⠀⠀⠀⠀⠀⠀⠀➵ Let's Chat with me ♡\n"
                + "\t ᑋᵉᑊᑊᵒ ᵕ̈ ᑋᵉᑊᑊᵒ I'm Dululu\n"
                + "\t♡____________________________________________________________♡\n"
                + "\t What can I do for you? (°◡°♡).:｡\n"
                + "\t♡____________________________________________________________♡\n";
    }

    /**
     * The reply message when user input bye
     * @return Bye( '༥' ). Hope to see you again soon!
     */
    public String showBye() {
        return "\t Bye( '༥' ). Hope to see you again soon!";
    }

    /**
     * The Ui of break line for evey chat block
     * @return the break Line
     */
    public String showLine() {
        return "\t♡____________________________________________________________♡\n";
    }

    /**
     * The message when cannot load the tasks from the file
     * @return ☹ OOPS!!! I'm sorry, but I couldn't load your tasks from the file.
     */
    public String showLoadingError() {
        return "\t (>w<) OOPS!!! I'm sorry, but I couldn't load your tasks from the file.";
    }


    /**
     * The message when error occurred
     * @param message string show the error message
     * @return message string
     */
    public String showError(String message) {
        return message;
    }


    /**
     * Reads a command from the user input.
     * @return a String representing the command entered by the user.
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    /**
     * Returns a formatted string containing the tasks in the given TaskList object.
     * If the TaskList is empty, returns a message indicating that there are no pending tasks.
     * @param tasks the TaskList object containing the tasks to be displayed
     * @return a formatted string containing the tasks in the given TaskList object, or a message indicating that there are no pending tasks
     */
    public String showList(TaskList tasks) {
        if (tasks.getLength() == 0) {
            return "\t No task is pending now! Please add task first Ծ‸Ծ\n";
        }
        StringBuilder result = new StringBuilder("\t Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getLength(); i++) {
            String taskString = tasks.getTask(i).toString();
            if (taskString != null) {
                result.append("\t ").append((i + 1)).append(".").append(taskString).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Return the massage when the task is done
     * @param tasks the tasks
     * @param item the task details
     * @return the message and the task details
     */
    public String showDone(TaskList tasks, String item) {
        return "\t Nice (•̀ᴗ• )! I've marked this task as done:\n" + item;
    }


    /**
     * Return the massage when the task is undone
     * @param tasks the tasks
     * @param item the task details
     * @return the message and the task details
     */
    public String showUndone(TaskList tasks, String item) {
        return "\t OK ⚆_⚆, I've marked this task as not done yet:\n" + item;
    }

    /**
     * Return message when user delete a task
     * @param tasks the tasks
     * @param item the task details
     * @return confirm message and details of deleted tasks, and the message about how many tasks left now
     */
    public String showDelete(TaskList tasks, String item) {
        return "\t Noted･◡･. I've removed this task:\n" + item + "\n\t Now you have " + (tasks.getLength()) + " tasks in the list *⸜( •ᴗ• )⸝*";
    }

    /**
     * The message when user successfully add a task
     * @param tasks the tasks
     * @param item the task details
     * @return the confirmation of successfully added task, the task details and the amount of tasks left
     */
    public String showAdd(TaskList tasks, String item) {
        return "\t ๐•ᴗ•๐ added:\n" + item + "\n\t Now you have " + tasks.getLength() + " tasks in the list *⸜( •ᴗ• )⸝*";

    }

    /**
     * Returns a formatted string of the tasks in the given TaskList that
     * match the provided keyword.
     * @param tasks the tasks object
     * @param keyword the keyword object
     * @return matched tasks by list
     */
    public String showFind(TaskList tasks, String keyword) {
        TaskList resultTaskList = tasks.findTasks(keyword);
        if (resultTaskList.getLength()==0) {
            return "\t(>_<) Sorry, no matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("\t Here are the matching tasks in your list:\n");
            for (int i = 0; i < resultTaskList.getLength(); i++) {
                result.append("\t ").append((i + 1)).append(".").append(resultTaskList.getTask(i)).append("\n");
            }
            return result.toString();
        }
    }


    /**
     * Returns a string representation of the tasks that are due before the given end time.
     * @param taskList the task list
     * @param endTime the due time
     * @return the tasks which deadline before the due date and time input
     */
    public String showDue(TaskList taskList, LocalDateTime endTime) {
        StringBuilder result = new StringBuilder("\t Here are the tasks due by " + endTime + " in your list:\n");
        TaskList dueTaskList = taskList.tasksDueBefore(endTime);
        for (int i = 0; i < dueTaskList.getLength(); i++) {
            result.append("\t ").append((i + 1)).append(".").append(dueTaskList.getTask(i)).append("\n");
        }
        return result.toString();
    }


    /**
     * The string of help information
     * @return the information about the commands that can use
     */
    public String showHelp() {
        return "\t Here are the commands you can use:\n" +
                "\t 1. todo <description>\n" +
                "\t 2. deadline <description> /by <date>\n" +
                "\t 3. event <description> /from <date> /to <date>\n" +
                "\t 4. list\n" +
                "\t 5. mark <index>\n" +
                "\t 6. unmark <index>\n" +
                "\t 7. delete <index>\n" +
                "\t 8. find <keyword>\n" +
                "\t 9. due <date>\n" +
                "\t 10. bye";
    }


    /**
     * The return message when user input invalid command
     * @return the notification message and guid user to use correct command
     */
    public String showInvalid() {
        return "\t Ծ‸Ծ OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n\t You can entre keyword 'help/ for more details!･◡･";
    }
}
