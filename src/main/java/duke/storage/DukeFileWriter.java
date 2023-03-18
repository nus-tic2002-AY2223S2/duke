package duke.storage;

import duke.storage.DukeFileReader;

import java.io.FileWriter;
import java.io.IOException;

public class DukeFileWriter {

    public static void writeInFile(String textToAdd){
        try {
            DukeFileReader.createFile();

            FileWriter fw = new FileWriter(DukeFileReader.FILE_PATH);
            fw.write(textToAdd);
            fw.close(); //writing operation to be completed
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void appendInFile(String textToAppend){
        try {
            DukeFileReader.createFile();

            FileWriter fw = new FileWriter(DukeFileReader.FILE_PATH, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
