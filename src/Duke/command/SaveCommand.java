package Duke.command;

import Duke.storage.Storage;
import Duke.task.Task;
import Duke.tasklist.Tasklist;
import Duke.ui.UI;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {

    public SaveCommand() {

    }

    /**
     * To save the tasks in the list into a text file
     * @param tasks existing task in the list
     * @param ui to display out the messages to the user
     * @param storage which stores the file
     */
    @Override
    public void execute(Tasklist tasks, UI ui) {
        try{
            FileWriter myObj = new FileWriter(storage.getFile());
            for(Task t : tasks.getTasks()) {
                myObj.write(t.toFileString());
                myObj.write("\n");
            }
            myObj.close();
            System.out.println("Noted. I've saved " + tasks.getTasks().size() + " tasks");
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred upon reading file.");
        }
    }
}