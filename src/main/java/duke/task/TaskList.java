package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> task) {
        this.tasks = task;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task into the current list of tasks.
     *
     * @param tasks a task class that stores the description, task type, priority of the task to a list of tasks.
     */
    public void addTask(Task tasks) {
        this.tasks.add(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println(this.tasks.get(this.tasks.size() - 1));
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }


    /**
     * Retrieves all task in the list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {

            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Retrieves a list of task that matches the user input keyword.
     *
     * @param userInput a string representing the user input to be processed.
     */
    public void listFindTasks(String userInput) throws DukeException {
        userInput = userInput.replace("find", "").trim();

        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(userInput)) {
                counter = 1;
                break;
            }
        }

        if (counter > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(userInput)) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        } else {
            throw new DukeException("OOPS!! No matching task found in your list.");
        }

    }

    /**
     * Updates the task list based on user input.
     * Takes in a string userInput, splits it into separate elements, and check based on the first element of the split string.
     * If the first element is "mark", marks a task as completed and/or updates the priority of the task.
     * If the first element is "unmark", unmarks a task as completed.
     * If the first element is "delete", deletes a task from the task list.
     *
     * @param userInput a string representing the user input to be processed.
     * @throws DukeException if the input is invalid.
     */
    public void updateTask(String userInput) throws DukeException {
        int itemNumber;
        String[] stringSplit;
        try {
            stringSplit = userInput.split(" ");
            int input_value = Integer.parseInt(stringSplit[1]);
            itemNumber = input_value - 1;
            String status = stringSplit[0];

            switch (status) {
                case "mark":
                    if (stringSplit.length == 2) { //E.g. Mark 1
                        tasks.get(itemNumber).setStatusAsMarked();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(itemNumber));
                    } else if (stringSplit.length == 4) { //E.g. Mark 1 as HIGH
                        String priority = stringSplit[3];
                        tasks.get(itemNumber).setPriority(priority);
                        System.out.println("OK, Priority set as " + priority);
                        System.out.println(tasks.get(itemNumber));
                    }
                    break;
                case "unmark":
                    tasks.get(itemNumber).setStatusAsUnmarked();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(itemNumber));
                    break;
                case "delete":

                    //Check if item number exist or not.
                    try {
                        tasks.get(itemNumber);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Task number not found, please try again.");
                    }

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(itemNumber));
                    tasks.remove(itemNumber);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Task number not found, please try again.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter only numbers.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Getters method
    public Integer getTaskSize() {
        return tasks.size();
    }

    public Task getTask(Integer taskNum) {
        return tasks.get(taskNum);
    }

    /**
     * Returns a string containing details of a task with the specified task number.
     *
     * @param taskNum the task number of the task to retrieve details for.
     * @return a string containing details of the specified task.
     */
    public String getTaskDetails(Integer taskNum) {

        if (tasks.get(taskNum) instanceof Deadline) {
            Deadline deadlineTask = (Deadline) tasks.get(taskNum);
            return " | " + deadlineTask.getBy();
        } else if (tasks.get(taskNum) instanceof Event) {
            Event eventTask = (Event) tasks.get(taskNum);
            return " | " + eventTask.getFrom() + " | " + eventTask.getTo();
        } else {
            return "";
        }

    }
}
