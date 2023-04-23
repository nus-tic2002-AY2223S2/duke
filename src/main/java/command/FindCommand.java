package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        String[] findParts = keyword.split(" ", 2);
        this.keyword = findParts[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
