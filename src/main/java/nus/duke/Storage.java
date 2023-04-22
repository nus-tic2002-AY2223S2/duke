package nus.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

// *************************
// level 7 Save
// *************************
public class Storage {
    protected File filename;

    // create a data file in filePath
    public Storage(String filePath) {
        this.filename = new File(filePath);
        if (!filename.exists()) {
            try{
                boolean isFileCreated = filename.createNewFile();

                if(isFileCreated) {
                    System.out.println("File created");
                } else {
                    throw new DukeException("Unable to create file!");
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadTodoInStorage (String taskString,String task, ArrayList a) {
        if (taskString != null) {
            ToDo td = new ToDo(taskString);
            markStorageTaskIfDone(task, td);
            a.add(td);
        }
    }

    public void markStorageTaskIfDone (String task, Task t) {
        if (task.charAt(4) == 'X') {
            t.markAsDone();
        }
    }
    public void loadDeadlineInStorage  (String taskString, String task, ArrayList a) throws ParseException {
        if (taskString != null) {
            String[] partInString = taskString.split("by:");
            String date = partInString[1].substring(0, partInString[1].length() - 1);
            String taskJob = partInString[0].substring(0, partInString[0].length() - 1);

            SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy 'at' hh:mm");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
            Date d = sdf.parse(date);
            String formattedTime = output.format(d);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadLine = LocalDateTime.parse(formattedTime,formatter);

            Deadline dl = new Deadline(taskJob, deadLine);
            markStorageTaskIfDone(task, dl);
            a.add(dl);
        }
    }

    public void loadEventInStorage  (String taskString, String task, ArrayList a) throws ParseException {
        if (taskString != null) {
            String[] partInString = taskString.split("from:|to:");
            String taskJob = partInString[0].substring(0, partInString[0].length() - 1);
            String startDate = partInString[1].substring(0, partInString[1].length() - 1);
            String endDate = partInString[2].substring(0, partInString[2].length() - 1);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d yyyy 'at' hh:mm");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            String formattedStartTime = output.format(start);
            String formattedEndTime = output.format(end);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime startLDT = LocalDateTime.parse(formattedStartTime,formatter);
            LocalDateTime endLDT = LocalDateTime.parse(formattedEndTime,formatter);

            Event e = new Event(taskJob, startLDT, endLDT);
            markStorageTaskIfDone(task, e);
            a.add(e);
        }
    }

    public ArrayList load() throws DukeException, IOException, ParseException {
        Scanner s = new Scanner(filename);
        ArrayList a = new ArrayList<Task>();

        while (s.hasNext()) {
            String task = s.nextLine();
            String taskString = task.substring(7);
            String taskType = String.valueOf(task.charAt(1));

            switch (taskType) {
                case "T":
                    loadTodoInStorage(taskString,task, a);
                    break;
                case "D":
                    loadDeadlineInStorage(taskString, task, a);
                    break;
                case "E":
                    loadEventInStorage(taskString, task, a);
                    break;
                default:
            }
        }
        return a;
    }

    public static void save(TaskList tl, String filePath) throws DukeException, IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i < tl.getSize() + 1; i++) {
            fw.write(tl.tasksArray.get(i - 1).toString() + System.lineSeparator());
        }
        fw.close();
    }
}
