package Storage;

import Exception.DukeException;
import Task.*;
import UI.*;
import TasksList.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage extends TasksList {
    private final String filePath;
    private static final Storage storage = new Storage();

    public static Storage getStorage() {
        return storage;
    }

    public Storage() {
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, "data", "duke.txt");
        this.filePath = path.toString();
        fileCreate();
    }

    public void printList() {
        String space = " ";
        Ui.showLine();
        System.out.println(space + "Here are the tasks in your list:");
        int taskNum = 0;
        for (Task list : tasksList) {
            taskNum++;
            System.out.println(space + taskNum + "." + list.toString());
        }
        Ui.showLine();
    }

    public void fileCreate() {
        try {
            File file = new File(this.filePath);
            //System.out.println("full path: " + file.getAbsolutePath());
            //System.out.println("file exists?: " + file.exists());
            // check source file exists, create if it does not exist
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }else{
                System.out.println("File already exist.");
            }
            Ui.showLine();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! An error for file create occurred.");
        }
    }

    /**
     * Write all Tasks to file
     *
     * @param textToAdd each line of tasks
     * @throws DukeException Duke exception
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
     * Read Tasks(one line by one line) from file
     *
     * @throws DukeException Duke exception
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
