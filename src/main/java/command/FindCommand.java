package command;

import java.util.ArrayList;
import storage.Storage;
import task.TaskList;
import task.Task;
import ui.Ui;

/**
 * Find command
 * -> finds the keyword from the list
 */
public class FindCommand extends Commands{
    protected String keyword;

    /**
     * @param keyword -> chosen keyword
     */

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredList = new ArrayList<Task>();
        //check if there are any tasks in the list
        if (tasks.taskslist.size() == 0) {
            Ui.printMsg("Currently, there are no tasks in the list.-_-");
        } else {
            for (Task task : tasks.taskslist) {
                if (task.getDescription().contains(this.keyword)) {
                    filteredList.add(task);
                }
            }
        }

        //check if keyword exist
        if (filteredList.size() == 0) {
            Ui.printMsg("There are no tasks with this keyword: " + this.keyword);
        } else {
            Ui.printMsg("Here are the matching tasks in your list: ");
            int taskNum=0;
            for (int i =0; i<filteredList.size(); i++){
                taskNum++;
                Ui.printMsg(taskNum + ". " + filteredList.get(i).toString());
            }

        }


    }
}
