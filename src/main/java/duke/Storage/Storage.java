package duke.Storage;

import duke.Command.Command;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import duke.TasksType.Deadline;
import duke.TasksType.Event;
import duke.TasksType.Task;
import duke.TasksType.Todo;
import duke.Utility.Util;


public class Storage {
    private static final String FILE_PATH = "data/saved.txt";
    //path to save and load the list, final as it should not be changed

    /***
     * This method is called by execute() to try to load a file,
     * if file is found, call executeLoad() to load in the text lines into an ArrayList of different tasks
     * if the file is not found, create a new file using the path
     * @return an ArrayList of Tasks
     * @thows an exception if found cannot be created
     */
    public static ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(Command.executeLoad(line));
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * This method is called after every while loop in the Command class.
     * To save the file each time a change is made to the list
     * every item is saved in according to format for easier loading in subsequent runs
     * * loop through the ArrayList and check each item's instanceOf to know which task it is
     * and save accordingly
     * @param list takes in an ArrayList of task
     */
    public static void saveToFile(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task line : list) {
                String item = new String();
                item += line.toString().substring(0, 7);
                if (line instanceof Todo) {
                    item += line.toString().substring(7);
                }
                else if (line instanceof Deadline) {
                    item += line.getDescription() + " /by " + Util.dateTimeToString(((Deadline) line).getBy()) + " [Priority " + line.getPriorityLevel() + "]";
                }
                else {
                    item += line.getDescription() + " /from " + Util.dateTimeToString(((Event) line).getFrom())+ " /to " + Util.dateTimeToString(((Event) line).getTo()) + " [Priority " + line.getPriorityLevel() + "]";
                }

                writer.write(item + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
