import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> task) {
        this.tasks = task;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public void addTask(Task tasks) {
        this.tasks.add(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println(this.tasks.get(this.tasks.size() - 1));
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }



    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {

            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    public void listFindTasks(String userInput) {
        userInput = userInput.replace("find","").trim();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(userInput)){
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    /**
     * Updates the task list based on user input.
     * Takes in a string userInput, splits it into separate elements, and check based on the first element of the split string.
     * If the first element is "mark", marks a task as completed and/or updates the priority of the task.
     * If the first element is "unmark", unmarks a task as completed.
     * If the first element is "delete", deletes a task from the task list.
     * @param userInput a string representing the user input to be processed.
     * @throws DukeException if the input is invalid.
     */
    public void updateTask(String userInput) {
        int listNumber;
        String[] stringSplit;
        try {
            stringSplit = userInput.split(" ");
            int input_value = Integer.parseInt(stringSplit[1]);
            listNumber = input_value - 1;
            String status = stringSplit[0];

            switch (status) {
                case "mark":
                    if (stringSplit.length == 2) {
                        this.tasks.get(listNumber).setStatusAsMarked();
                        System.out.println(this.tasks.get(listNumber));
                    } else if (stringSplit.length == 4) {
                        String priority = stringSplit[3];

                        this.tasks.get(listNumber).setPriority(priority);

                    }
                    break;
                case "unmark":
                    this.tasks.get(listNumber).setStatusAsUnmarked();
                    System.out.println(this.tasks.get(listNumber));
                    break;
                case "delete":
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(this.tasks.get(listNumber));
                    this.tasks.remove(listNumber);
                    System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number not found, please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter only numbers.");
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
