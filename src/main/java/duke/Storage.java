package duke;

import duke.Command.Command;

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
import java.time.format.DateTimeFormatter;



public class Storage {

    private static final String FILE_PATH = "data/saved.txt";

    public static ArrayList<Task> loadFile() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(Command.executeLoad(line));
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
    public static void saveToFile(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task line : list) {
                String item = new String();
                item += line.toString().substring(0, 7);
                if (line instanceof Todo) {
                    item += line.toString().substring(7);
                }
                else if (line instanceof Deadline) {
                    item += ((Deadline) line).getDescription() + " /by " + ((Deadline) line).getBy();
                }
                else {
                    item += ((Event) line).getDescription() + " /from " + ((Event) line).getFrom()+ " /to " + ((Event) line).getTo();
                }

                writer.write(item + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
