import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a storage class that creates/saves duke's file
 */
public class Storage {
    private String filename;
    private final File duke_save;

    /**
     * Constructor for Storage class
     * @param filename the name of the file to be created if it doesn't exist
     */
    public Storage(String filename) {
        this.filename = filename;
        this.duke_save = new File(filename);

        if (!duke_save.exists()) {
            try {
                //create a new save file
               boolean isFileCreated = duke_save.createNewFile();

               if (isFileCreated){
                   System.out.println("New save file created: " + duke_save.getAbsoluteFile());
               }else {
                   throw new DukeException("☹ OOPS!!! Failed to create file, Please check the path and folder permission");
               }
            } catch (IOException | DukeException io) {
                System.out.println(io.getMessage());
            }
        }
    }

    /**
     * Save user's task list into a file.
     * @param userTask the user's task list
     */
    public void save(TaskList userTask) {

        try {
            FileWriter myWriter = new FileWriter("data\\duke.txt");
            for (int i = 0; i < userTask.getTaskSize(); i++) {
                int status = userTask.getTask(i).getStatus().equals("X") ? 1 : 0;
                String priority = userTask.getTask(i).getPriority();
                String output = userTask.getTask(i).getTaskType() + " | " + status + " | " +  priority + " | " + userTask.getTask(i).getDescription();

                String finalOutput = output + userTask.getTaskDetails(i);

                try {
                    myWriter.write(finalOutput + "\n");
                } catch (IOException io) {
                    System.out.println("An error occurred while writing to the save file.");
                    io.printStackTrace();
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while accessing the save file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads user's task from a file.
     * @return a list of task loaded from the file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        Task t;
        try {

            System.out.println("File exist, loading data.");
            //Try to load the file
            Scanner myReader = new Scanner(duke_save);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                String[] formatSaveDataSplit = data.split("\\|");
                //Remove all blank spaces
                for (int i = 0; i < formatSaveDataSplit.length; i++) {
                    formatSaveDataSplit[i] = formatSaveDataSplit[i].trim();
                }

                String description = formatSaveDataSplit[3];

                if (formatSaveDataSplit.length == 4) {
                    t = new ToDo(description, "T");
                    t.setPriority(formatSaveDataSplit[2]);
                    if (formatSaveDataSplit[1].equals("1")) {
                        t.setStatusAsMarked();
                    }

                    tasks.add(t);
                } else if (formatSaveDataSplit.length == 5) {
                    t = new Deadline(description, "D", formatSaveDataSplit[4]);
                    t.setPriority(formatSaveDataSplit[2]);
                    if (formatSaveDataSplit[1].equals("1")) {
                        t.setStatusAsMarked();
                    }

                    tasks.add(t);
                } else if (formatSaveDataSplit.length == 6) {
                    t = new Event(description, "E", formatSaveDataSplit[4], formatSaveDataSplit[5]);
                    t.setPriority(formatSaveDataSplit[2]);
                    if (formatSaveDataSplit[1].equals("1")) {
                        t.setStatusAsMarked();
                    }
                    tasks.add(t);
                }


            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! an error occurred while reading the save file.");
        }
        return tasks;
    }


}