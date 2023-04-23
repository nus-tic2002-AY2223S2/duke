package command;

import tasklist.TaskList;
import task.Task;
import ui.Ui;
import storage.Storage;


public class UpdatePriorityCommand extends Command {
    private int taskIndex;
    private int priority;

    public UpdatePriorityCommand(String input) {
        String[] inputParts = input.split(" ", 3);
        int taskIndex = Integer.parseInt(inputParts[1]);
        int priority = Integer.parseInt(inputParts[2]);
        this.taskIndex = taskIndex;
        this.priority = priority;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(taskIndex - 1);
            task.setPriority(priority);
            storage.saveTasks(tasks);
            ui.showPriorityUpdated(task, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Error: Task number " + taskIndex + " not found.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
