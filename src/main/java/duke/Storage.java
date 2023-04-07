package duke;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import duke.Task;
//import java.time.format.DateTimeFormatter;
//public class Storage
//{
//
//    private File saved;
//
//    public Storage() {
//        saved = new File("data/saved.txt");
//
//    }
//    public void load() throws DukeException{
//        if (!saved.exists() && saved.isDirectory()) {
//
//            throw new DukeException("New file created!");
//
//        }
//    }
//}


public class Storage {

    private static final String FILE_PATH = "data/saved.txt";

    public static ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task description = new Task(line);
                    list.add(description);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void saveToFile(ArrayList<Task> lines) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
