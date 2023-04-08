package storage;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import task.Deadline;
import task.ToDo;
import task.Event;
import task.Task;

/**
 * Storage class
 * -> stores the list into a file
 */
public class Storage {
    private static File f;
    private static String filePath;
    private static FileWriter fileWriter;
    private static BufferedWriter bW;

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
                System.out.println("File is already created");
            } else {
                f.createNewFile();
                System.out.println("File created successfully");
            }

            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bW = new BufferedWriter(fileWriter);
            String toSaveList = "";

            for (int i = 0; i < list.size(); i++) {
                toSaveList = list.get(i).toSave();
                bW.write(toSaveList);
                bW.newLine();
                System.out.println("Task saved successfully");
            }
            bW.close();
        } catch (IOException e) {
            System.out.println("Save to file Error");
        }
    }
    //Save tasks to file END

    //Load tasks START
    public static ArrayList<Task> load() {
        ArrayList<Task> loadList = new ArrayList<Task>();
        String storedPath = "data\\duke.txt";
        try {
            FileReader fR = new FileReader(storedPath);
            BufferedReader bR = new BufferedReader(fR);

            int count =0;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] aLine = input.split("\\Q|\\E");

                if (aLine[0].trim().equals("D")) {
                    loadList.add(count, new Deadline(aLine[2]trim(), aLine[3].trim()));
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
            System.out.println("Load saved tasks ERROR");
        }
        return loadList;
    }
    //load tasks END
}
