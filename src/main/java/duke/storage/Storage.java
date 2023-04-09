/**
 *  DONE BY: A0227169X; ANG JIA JIN, GABRIEL
 */

package duke.storage;

import duke.*;
import duke.task.TaskList;
import duke.DateValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     *  Attribute
     */
    private static String filePath;
    private static File fileItem;
    private static ArrayList<Task> listOfTask;

    /**
     *  Constructor
     */
    public Storage(String pathFile) {
        this.filePath = pathFile;
        fileItem = new File(pathFile);
    }

    public static ArrayList<Task> load() throws DukeException {
        if (!fileItem.exists() || !fileItem.isFile()) {
            throw new DukeException("☹ OOPS!!! File is empty");
        }
        else {
            listOfTask = new ArrayList<Task>(); //string initialize Task arraylist
            try {
                Scanner scan = new Scanner(fileItem);
                while (scan.hasNext()) {
                    String itemEntry = scan.nextLine();
                    Task newTask;
                    String [] splitEntryValue = itemEntry.split(";");

                    if(splitEntryValue[0].trim().equalsIgnoreCase("T")) {
                        /**
                         * @param splitEntryValue[0] = T
                         * @param splitEntryValue[1] = 1 - done, 0 - not done
                         * @param splitEntryValue[2] = Description
                         */
                        newTask = new Todo(splitEntryValue[2].trim());
                    }
                    else if(splitEntryValue[0].trim().equalsIgnoreCase("D")) {
                        /**
                         * @param splitEntryValue[0] = D
                         * @param splitEntryValue[1] = 1 - done, 0 - not done
                         * @param splitEntryValue[2] = Description
                         * @param splitEntryValue[3] = 'By' attribute
                         */
                        newTask = new Deadline(splitEntryValue[2].trim(), DateValidator.convertStringToDate(splitEntryValue[3].trim()));
                    }
                    else  {
                        /**
                         * @param splitEntryValue[0] = E
                         * @param splitEntryValue[1] = 1 - done, 0 - not done
                         * @param splitEntryValue[2] = Description
                         * @param splitEntryValue[3] = Start
                         * @param splitEntryValue[4] = End
                         */
                        newTask = new Event(splitEntryValue[2].trim(), splitEntryValue[3].trim(), splitEntryValue[4].trim());
                    }

                    //mark as done if value is 1
                    if(splitEntryValue[1].trim().equalsIgnoreCase("1"))
                    {
                        newTask.markAsDone();
                    }
                    listOfTask.add(newTask);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("☹ OOPS!!! Error caught: " + e.getMessage());
            }

            return listOfTask;
        }
    }

    public static void saveFile(TaskList listOfTask) throws DukeException {
        try {
            FileWriter newFW = new FileWriter(filePath);
            for(int i = 0; i < listOfTask.getSizeOfList(); i++)
            {
                Task itemToAdd = listOfTask.getElementFromList(i);
                String finalLine = new String();
                if(itemToAdd instanceof Todo) {
                    finalLine += "T ; ";
                    if(itemToAdd.getTaskStatus().equalsIgnoreCase("X")) {
                        finalLine += "1";
                    }
                    else {
                        finalLine += "0";
                    }
                    finalLine += " ; " + itemToAdd.getDescription();
                }
                else if(itemToAdd instanceof Deadline) {
                    finalLine += "D ; ";
                    if(itemToAdd.getTaskStatus().equalsIgnoreCase("X")) {
                        finalLine += "1";
                    }
                    else {
                        finalLine += "0";
                    }
                    finalLine += " ; " + itemToAdd.getDescription() + " ; " + DateValidator.convertDateToString(((Deadline) itemToAdd).getBy());
                }
                else {
                    finalLine += "E ; ";
                    if(itemToAdd.getTaskStatus().equalsIgnoreCase("X")) {
                        finalLine += "1";
                    }
                    else {
                        finalLine += "0";
                    }
                    finalLine += " ; " + itemToAdd.getDescription() + " ; " + ((Event) itemToAdd).getStart()+ " ; " + ((Event) itemToAdd).getEnd();

                }
                newFW.write(finalLine);
                newFW.write(System.getProperty( "line.separator" ));
            }
            newFW.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error caught: " + e.getMessage());
        }

    }

}
