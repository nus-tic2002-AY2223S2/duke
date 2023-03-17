import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected File filename;
    // create a data file in filePath
    public Storage(String filePath) {
        this.filename = new File(filePath);
    }

    public ArrayList load() throws FileNotFoundException {
        Scanner s = new Scanner(filename);

        ArrayList a = new ArrayList<>();
        while (s.hasNext()) {
            Task t = new Task(s.nextLine());
            a.add(t);
        }
        return a;
    }
}
