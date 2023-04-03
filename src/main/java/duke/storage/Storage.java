/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.storage;

import duke.DukeException;
import duke.Task;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {

    /**
     *  Attribute
     */
    private String filePath;
    private File fileItem;

    /**
     *  Constructor
     */
    public Storage(String pathFile) {
        this.filePath = pathFile;
        fileItem = new File(pathFile);
    }

    public ArrayList<Task> load() throws DukeException {
        if (!fileItem.exists() || !fileItem.isFile())
        {
            throw new DukeException("☹ OOPS!!! File is empty");
        }
        else {
            ArrayList<Task> newList = new ArrayList<Task>(); //string initialize duke.Task arraylist
            return newList;
        }
    }

}
