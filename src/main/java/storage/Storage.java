package storage;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import task.Deadline;
import task.ToDo;
import task.Event;
import task.Task;
import exception.DukeException;
import ui.Ui;

/**
 * Storage class
 * -> stores the list into a file and load task from file
 */
public class Storage {
    protected static File f;
    protected static String filePath;
    protected static FileWriter fileWriter;
    protected static BufferedWriter bW;

    /**
     *
     * @param filePath -> gets the specific file path
     */
    //Create File START
    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }
    //Create File END

    //Save tasks to file START
    public static void saveToFile(ArrayList<Task> list) {

        try {
            File f = new File(filePath);
            if (f.exists()) {
                Ui.printMsg("File is already created");
            } else {
                f.createNewFile();
                Ui.printMsg("File created successfully");
            }

            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bW = new BufferedWriter(fileWriter);
            String toSaveList = "";

            for (int i = 0; i < list.size(); i++) {
                toSaveList = list.get(i).toSave();
                bW.write(toSaveList);
                bW.newLine();
            }
            bW.close();
        } catch (IOException e) {
            Ui.printMsg("Save to file Error");
            Ui.showLine();
        }
        Ui.printMsg("All tasks saved successfully! ^V^");
        Ui.showLine();
    }
    //Save tasks to file END

    //Load tasks START
    public static ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadList = new ArrayList<Task>();
        try {
            FileReader fR = new FileReader(filePath);
            BufferedReader bR = new BufferedReader(fR);

            int count =0;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] aLine = input.split("\\Q|\\E");

                if (aLine[0].trim().equals("D")) {
                    loadList.add(count, new Deadline(aLine[2].trim(), aLine[3].trim()));
                    //check status
                    if (aLine[1].trim().equals("1")) {
                        loadList.get(count).mark();
                    }
                    ++count;
                } else if (aLine[0].trim().equals("T")) {
                    loadList.add(count, new ToDo(aLine[2].trim()));
                    //check status
                    if (aLine[1].trim().equals("1")) {
                        loadList.get(count).mark();
                    }
                    ++count;
                } else if (aLine[0].trim().equals("E")) {
                    String[] time = aLine[3].split("-");
                    loadList.add(count, new Event(aLine[2].trim(), time[0].trim(), time[1].trim()));
                    //check status
                    if (aLine[1].trim().equals("1")) {
                        loadList.get(count).mark();
                    }
                    ++count;
                }
            }
            bR.close();
        } catch (IOException e) {
            Ui.printMsg("Load saved tasks ERROR");
            Ui.showLine();
        }
        return loadList;
    }
    //load tasks END
}
