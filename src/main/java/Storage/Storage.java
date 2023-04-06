package storage;

import exception.DukeException;
import ui.*;
import taskslist.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage extends TasksList {
    private final String filePath;
    private static final Storage storage = new Storage();

    /* make Storage constructor , initialise filepath and create new file */
    public Storage() {
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, "data", "duke.txt");
        this.filePath = path.toString();
        fileCreate();
    }

    /**
     * @return instance storage
     */
    public static Storage getStorage() {
        return storage;
    }

    /**
     * Creates file and see whether file create successful or not.
     *
     * IOException in RuntimeException
     */
    public void fileCreate() {
        try {
            File file = new File(this.filePath);
            boolean isCreateNewFile = file.createNewFile();
            if (isCreateNewFile) {
                System.out.println("File created,file path: " + file.getAbsolutePath());
            }else{
                System.out.println("File already exist,file path: " + file.getAbsolutePath());
            }
            Ui.showLine();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! An error for file create occurred.");
        }
    }

    /**
     * Writes all Tasks to disk file
     *
     * @param textToAdd each line of tasks
     *
     * IOException in RuntimeException
     */
    public void writeToFile(String textToAdd) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File write get problem!");
        }
    }

    /**
     * Read Tasks follow one line by one line from disk file
     *
     * FileNotFoundException in RuntimeException
     */
    public void readFile() {
        try {
            File file = new File(this.filePath);
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                storage.loadTask(taskByLine(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File read get problem!");
        }
    }
}
