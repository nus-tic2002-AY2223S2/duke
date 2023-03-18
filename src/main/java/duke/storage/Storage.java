package duke.storage;

import java.util.Scanner;

/**
 * A <code>duke.storage.Storage</code> class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    public Scanner load(){
        return DukeFileReader.getTasklistInFile();
    }
}
