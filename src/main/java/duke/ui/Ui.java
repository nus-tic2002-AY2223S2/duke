package duke.ui;

import java.util.Scanner;

import duke.util.TaskList;

import java.time.LocalDateTime;

public class Ui {
    public Ui() {
    }

    public String showWelcome() {
        return "\t Hello from Lily \u200E|•'-'•) ✧\n"
                + " \t .ᕱ⠀⠀⠀ᕱ⠀ ⠀➶ ⠀➴        \n"
                + "\t \u200E(๑◕ܫ◕๑) ➶⠀⠀⠀ ⠀➴ \n"
                + "\t \u200E૮⠀⠀⑅ ⠀づ ⠀⠀⠀⠀⠀⠀⠀➵ Let's Chat with me ♡\n"
                + "\t ᑋᵉᑊᑊᵒ ᵕ̈ ᑋᵉᑊᑊᵒ I'm Dululu\n" + "\t What can I do for you? (°◡°♡).:｡\n";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showLine() {
        return "\t♡____________________________________________________________♡\n";
    }

    public String showLoadingError() {
        return "☹ OOPS!!! I'm sorry, but I couldn't load your tasks from the file.";
    }

    public String showError(String message) {
        return message;
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public String showList(TaskList tasks) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getLength(); i++) {
            result.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
        }
        return result.toString();
    }

    public String showDone(TaskList tasks, String item) {
        return "Nice! I've marked this duke.task as done:\n" + item;
    }

    public String showUndone(TaskList tasks, String item) {
        return "Nice! I've marked this duke.task as undone:\n" + item;
    }

    public String showDelete(TaskList tasks, String item) {
        return "Noted. I've removed this duke.task:\n" + item + "\nNow you have " + (tasks.getLength()) + " tasks in the list.";
    }

    public String showAdd(TaskList tasks, String item) {
        return "Got it. I've added this duke.task:\n" + item + "\nNow you have " + tasks.getLength() + " tasks in the list.";
    }

    public String showFind(TaskList tasks, String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        TaskList resultTaskList = tasks.findTasks(keyword);
        for (int i = 0; i < resultTaskList.getLength(); i++) {
            result.append((i + 1)).append(".").append(resultTaskList.getTask(i)).append("\n");
        }
        return result.toString();
    }

    public String showDue(TaskList taskList, LocalDateTime endTime) {
        StringBuilder result = new StringBuilder("Here are the tasks due by " + endTime + " in your list:\n");
        TaskList dueTaskList = taskList.tasksDueBefore(endTime);
        for (int i = 0; i < dueTaskList.getLength(); i++) {
            result.append((i + 1)).append(".").append(dueTaskList.getTask(i)).append("\n");
        }
        return result.toString();
    }

    public String showHelp() {
        return "Here are the commands you can use:\n" +
                "1. todo <description>\n" +
                "2. deadline <description> /by <date>\n" +
                "3. event <description> /from <date> /to <date>\n" +
                "4. list\n" +
                "5. mark <index>\n" +
                "6. unmark <index>\n" +
                "7. delete <index>\n" +
                "8. find <keyword>\n" +
                "9. due <date>\n" +
                "10. bye";
    }

    public String showInvalid() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
