package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeFileReaderAndWriter {

    private static void createFile() throws IOException {
        File file = new File(Storage.FILE_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile(); // if file already exists will do nothing
    }

    public static Scanner load() {
        Scanner s = null;
        try {
            createFile();
            File f = new File(Storage.FILE_PATH); // create a File for the given file path
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return s;
    }

    public static void writeInFile(String textToAdd) {
        try {
            createFile();

            FileWriter fw = new FileWriter(Storage.FILE_PATH);
            fw.write(textToAdd);
            fw.close(); //writing operation to be completed
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void appendInFile(String textToAppend) {
        try {
            createFile();

            FileWriter fw = new FileWriter(Storage.FILE_PATH, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
