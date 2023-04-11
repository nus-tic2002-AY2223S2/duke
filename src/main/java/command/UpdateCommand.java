package command;

import storage.Storage;
import task.*;
import task.TaskList;
import ui.Ui;
import formatDateTime.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Update Command
 * -> takes in the update input, edits data into the list and returns message
 */

public class UpdateCommand extends Commands{
    protected String updateTask;
    protected String replacedTask;
    protected int updateNum;

    /**
     * @param updateTask -> update with chosen description
     * @param updateNum -> update the chosen task number
     */
    public UpdateCommand(int updateNum, String replacedTask, String updateTask){
        this.updateNum = updateNum;
        this.replacedTask = replacedTask;
        this.updateTask = updateTask;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //get the chosen task
        if(updateNum >=0 && updateNum <tasks.taskslist.size()) {
            Task task = tasks.taskslist.get(updateNum);
            Ui.printMsg("The chosen task to edit is " + task);

            Task updatedTask = task;
            //find the word to replace in the task
            if(task.getDescription().toString().toLowerCase().contains(replacedTask)) {
                //task.setDescription(updateTask);
                String[] words = task.getDescription().toString().split(" ");
                for (int i =0; i< words.length; i++) {
                    if (words[i].equals(replacedTask)) {
                        words[i] = updateTask;
                        String sentence = String.join(" ", words);
                        task.setDescription(sentence);
                    }
                }
                Ui.printMsg("Here is the updated description: " + task.getDescription().toString() );
            } else if(task.toString().contains("[D]")) {
                //find the date to replace
                String updatedDate = LocalDateTime.formatDate(updateTask);
                Deadline d = (Deadline) task;
                d.setBy(updatedDate);
            } else if (task.toString().contains("[E]")) {
                String updatedEDate = LocalDateTime.formatDate(updateTask);
                Event e = (Event) task;
                if(e.getFrom().toString().toLowerCase().contains(replacedTask)) {
                    e.setFrom(updatedEDate);
                } else if (e.getTo().toString().toLowerCase().contains(replacedTask)) {
                    e.setTo(updatedEDate);
                }
            } else {
                Ui.printMsg("-_- There is no such word to edit " + replacedTask);
            }
            //
            Ui.printMsg("We have successfully edit the task: " + updatedTask);

        }
    }
}
