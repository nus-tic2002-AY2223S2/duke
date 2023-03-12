import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileReader {

    public static final String FILE_PATH = "data/duke.txt";

    public static void createFile() throws IOException {
        File file = new File(DukeFileReader.FILE_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile(); // if file already exists will do nothing
    }

    public static ArrayList<Task> getTasklistInFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            createFile();
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                Task task = Duke.getTask(s.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return taskList;
    }

}
