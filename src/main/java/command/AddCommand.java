package command;

import exception.DukeException;
import storage.Storage;
import ui.Ui;
import task.Task;
import tasklist.TaskList;
import task.ToDo;
import task.Deadline;
import task.Event;
import java.time.DateTimeException;


import static task.Deadline.parseDateTime;

public class AddCommand extends Command {
    private String taskInput;

    public AddCommand(String taskInput) {
        this.taskInput = taskInput;
    }

    Task createTask(String taskInput) throws DukeException {
        String[] inputParts = taskInput.split(" ", 2);
        String taskType = inputParts[0];

        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new DukeException("Error: Task description cannot be empty.");
        }

        String description = inputParts[1];

        switch (taskType) {
            case "todo":
                return new ToDo(description);
            case "deadline":
                String[] deadlineParts = description.split(" /by ");
                if (deadlineParts.length != 2) {
                    throw new DukeException("Error: Deadline should have a description and a date separated by '/by'.");
                }
                try {
                    return new Deadline(deadlineParts[0], parseDateTime(deadlineParts[1]));
                } catch (DateTimeException e) {
                    throw new DukeException("Error: Invalid date format. Use 'd/m/yyyy HHmm' format, e.g., 12/2/2019 1800.");
                }

            case "event":
                String[] eventParts = description.split(" /from ");
                if (eventParts.length != 2) {
                    throw new DukeException("Error: Event should have a description and '/from' and '/to' dates.");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length != 2) {
                    throw new DukeException("Error: Event should have a description and '/from' and '/to' dates.");
                }
                try {
                    return new Event(eventParts[0], parseDateTime(timeParts[0]), parseDateTime(timeParts[1]));
                } catch (DateTimeException e) {
                    throw new DukeException("Error: Invalid date format. Use Use 'm/d/yyyy HHmm' format, e.g., 12/2/2019 1800.");
                }

            default:
                throw new DukeException("Error: Unknown task type.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTask(taskInput);
            tasks.add(task);
            storage.saveTasks(tasks);
            ui.showTaskAdded(task, tasks.size());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}