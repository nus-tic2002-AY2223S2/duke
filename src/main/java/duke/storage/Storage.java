package duke.storage;

import java.util.Scanner;

/**
 * A <code>duke.storage.Storage</code> class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    public static final String FILE_PATH = "data/duke.txt";



    /**
     * This method load scanner from the file at the source.
     *
     * @return A Scanner object created using the File as the source.
     */
    public Scanner load() {
        return DukeFileReaderAndWriter.load();
    }


    /**
     * This method write text in the file at the source.
     *
     * @param textToAdd A String text representing the task to be added to the file.
     */
    public static void writeInFile(String textToAdd){
        DukeFileReaderAndWriter.writeInFile(textToAdd);
    }

    /**
     * This method write text at the end of the file at the source.
     *
     * @param textToAppend A String text representing the task to be appended to the file.
     */
    public static void appendInFile(String textToAppend){
        DukeFileReaderAndWriter.appendInFile(textToAppend);
    }

    /**
     * This method edit text at the given lineNumber of the file at the source.
     *
     * @param lineNumber An int text representing the lineNumber to be edited.
     * @param editedText A String text representing the edited text.
     */
    public static void editFile(int lineNumber, String editedText){
        DukeFileReaderAndWriter.editFile(lineNumber, editedText);
    }

}

