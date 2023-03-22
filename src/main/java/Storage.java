import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// *************************
// level 7 SAVE
// *************************
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

    public ArrayList load() throws DukeException, IOException, ParseException {
        Scanner s = new Scanner(filename);
        ArrayList a = new ArrayList<Task>();
        //TaskList a = new TaskList();
/*
        while (s.hasNext()) {
            Task t = new Task(s.nextLine());
            a.add(t);
        }
*/
            while (s.hasNext()) {
                String task = s.nextLine();
                String taskString = task.substring(7);
                String taskType = String.valueOf(task.charAt(1));


                switch (taskType) {
                    case "T":
                        if (taskString != null) {
                            ToDo td = new ToDo(taskString);
                            if (task.charAt(4) == 'X') {
                                td.markAsDone();
                            }
                            a.add(td);
                        }
                        break;
                    case "D":
                        if (taskString != null) {
                            String[] partInString = taskString.split("by:");
                            String date = partInString[1].substring(0,partInString[1].length()-1);
                            String taskJob = partInString[0].substring(0,partInString[0].length()-1);
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy 'at' hh:mm");
                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                            Date d = sdf.parse(date);
                            String formattedTime = output.format(d);
                            Deadline dl = new Deadline(taskJob, formattedTime);
                            if (task.charAt(4) == 'X') {
                                dl.markAsDone();
                            }
                            a.add(dl);
                        }
                        break;
                    case "E":
                        if (taskString != null) {
                            String[]partInString = taskString.split("from:|to:");
                            String taskJob = partInString[0].substring(0,partInString[0].length()-1);
                            String startDate = partInString[1].substring(0,partInString[1].length()-1);
                            String endDate = partInString[2].substring(0,partInString[2].length()-1);
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy 'at' hh:mm");
                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                            Date start = sdf.parse(startDate);
                            Date end = sdf.parse(endDate);
                            String formattedStartTime = output.format(start);
                            String formattedEndTime = output.format(end);
                            Event e = new Event(taskJob, formattedStartTime, formattedEndTime);
                            if (task.charAt(4) == 'X') {
                                e.markAsDone();
                            }
                            a.add(e);
                        }
                        break;
                    default:
                }
            }
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
