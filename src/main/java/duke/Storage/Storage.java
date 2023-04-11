package duke.Storage;

import duke.Command.Command;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import duke.Exception.DukeException;
import duke.TasksType.Deadline;
import duke.TasksType.Event;
import duke.TasksType.Task;
import duke.TasksType.Todo;
import duke.Utility.Util;


public class Storage {
    private static final String FILE_PATH = "data/saved.txt";
    //path to save and load the list, final as it should not be changed
    //absolute path for Jar "C:/Users/ixxed/IdeaProjects/duke/data/saved.txt"
    /***
     * This method is called by execute() to try to load a file,
     * if file is found, call executeLoad() to load in the text lines into an ArrayList of different tasks
     * if the file is not found, create a new file using the path
     * @return an ArrayList of Tasks
     */
    public static ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {
                throw new DukeException("File not found. A new file will be created.");
            }
            FileReader fr = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                Task t = Command.executeLoad(line);
                list.add(t);
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Error while loading file: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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