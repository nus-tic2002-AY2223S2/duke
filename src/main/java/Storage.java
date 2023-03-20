import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

            /*
            String taskString = task.substring(7);
            String taskType = String.valueOf(task.charAt(1));


                switch(taskType) {
                    case "T":
                        Stringt("todo " + taskString);
                        Command c = Parser.parse();
                        c.execute(tasks, ui, storage);
                        a.add(t);
                        break;
                    case "D":
                        t = new Task("deadline " + taskString);
                        a.add(t);
                        break;
                    case "E":
                        t = new Task("event " + taskString);
                        a.add(t);
                        break;
                    default:
                }
                */
        return a;
    }

    public static void save(TaskList tl, String filePath) throws DukeException, IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i < tl.getSize() + 1; i++) {
            fw.write(tl.tasksArray.get(i-1).toString() + System.lineSeparator());
        }
        fw.close();
    }
}
