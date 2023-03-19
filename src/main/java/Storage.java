import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    /*
    String root = System.getProperty("Users");
    java.nio.file.Path path = java.nio.file.Paths.get(root, "weipinglim", "duke", "data");
    boolean directoryExists = java.nio.file.Files.exists(path);
    */

    protected File filename;

    // create a data file in filePath
    public Storage(String filePath) {
        this.filename = new File(filePath);
    }

    public ArrayList load() throws DukeException, FileNotFoundException {

        Scanner s = new Scanner(filename);
        ArrayList a = new ArrayList<>();
        while (s.hasNext()) {
            Task t = new Task(s.nextLine());
            a.add(t);
        }
        return a;
    }
}
