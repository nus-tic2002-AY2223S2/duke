/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.storage;

import duke.DukeException;
import duke.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     *  Attribute
     */
    private static String filePath;
    private static File fileItem;
    private static ArrayList<Task> listOfTask;

    /**
     *  Constructor
     */
    public Storage(String pathFile) {
        this.filePath = pathFile;
        fileItem = new File(pathFile);
    }

    public static ArrayList<Task> load() throws DukeException {
        if (!fileItem.exists() || !fileItem.isFile()) {
            throw new DukeException("☹ OOPS!!! File is empty");
        }
        else {
            listOfTask = new ArrayList<Task>(); //string initialize Task arraylist

            try {
                Scanner scan = new Scanner(fileItem);
                while (scan.hasNext()) {
                    Task newTask = new Task(scan.nextLine());
                    listOfTask.add(newTask);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("☹ OOPS!!! Error caught: " + e.getMessage());
            }

            return listOfTask;
        }
    }

    public static void saveFile(TaskList listOfTask) throws DukeException {
        try {
            FileWriter newFW = new FileWriter(filePath);
            for(int i = 0; i < listOfTask.getSizeOfList(); i++)
            {
                newFW.write(listOfTask.getElementFromList(i).toString() + System.getProperty( "line.separator" ));
            }
            newFW.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error caught: " + e.getMessage());
        }

    }

}
