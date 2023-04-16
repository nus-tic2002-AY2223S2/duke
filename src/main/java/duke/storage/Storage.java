package duke.storage;

import java.util.ArrayList;

/**
 * A <code>duke.storage.Storage</code> interface deals with loading tasks from the file and saving tasks in the file
 */
public interface Storage {
    String FILE_PATH = "/tmp/duke.txt";

    /**
     * This method write text in the file at the source.
     *
     * @param textToAdd A String text representing the task to be added to the file.
     */
    void writeInFile(String textToAdd);

    /**
     * This method write text at the end of the file at the source.
     *
     * @param textToAppend A String text representing the task to be appended to the file.
     */
    void appendInFile(String textToAppend);


    /**
     * This method edit text at the given lineNumber of the file at the source.
     *
     * @param lineNumber An int representing the lineNumber to be edited.
     * @param editedText A String text representing the edited text.
     */
    void editFile(int lineNumber, String editedText);

    /**
     * This method load scanner from the file at the source.
     *
     * @return An ArrayList of lines in the File at the source.
     */
    ArrayList<String> load();

    /**
     * This method read text at the given lineNumber of the file at the source.
     *
     * @param lineNumber An int text representing the lineNumber to be read.
     */

    String readLine(int lineNumber);


}

