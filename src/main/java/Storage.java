import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filename;
    private File duke_save;

    public Storage(String filename) {
        this.filename = filename;
        this.duke_save = new File(filename);

        if (!duke_save.exists()) {
            try {
                //create a new save file
                duke_save.createNewFile();
                System.out.println("New save file created: " + duke_save.getAbsoluteFile());
            } catch (IOException io) {
                System.out.println(io);
            }
        }
    }

    public void save(TaskList userTask) {

        try {
            FileWriter myWriter = new FileWriter("data\\duke.txt");
            for (int i = 0; i < userTask.getTaskSize(); i++) {
                int status = userTask.getTask(i).getStatus().equals("X") ? 1 : 0;

                String output = userTask.getTask(i).getTaskType() + " | " + status + " | " + userTask.getTask(i).getDescription();

                String finalOutput = output + userTask.getTaskDetails(i);

                try {
                    myWriter.write(finalOutput + "\n");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

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
                for (int i = 0; i < formatSaveDataSplit.length; i++) {
                    formatSaveDataSplit[i] = formatSaveDataSplit[i].trim();
                }

                if (formatSaveDataSplit.length == 3) {
                    t = new ToDo(formatSaveDataSplit[2], "T");
                    if (formatSaveDataSplit[1].equals("1")) {
                        t.setStatusAsMarked();
                    }

                    tasks.add(t);
                } else if (formatSaveDataSplit.length == 4) {
                    t = new Deadline(formatSaveDataSplit[2], "D", formatSaveDataSplit[3]);
                    if (formatSaveDataSplit[1].equals("1")) {
                        t.setStatusAsMarked();
                    }

                    tasks.add(t);
                } else if (formatSaveDataSplit.length == 5) {
                    t = new Event(formatSaveDataSplit[2], "E", formatSaveDataSplit[3], formatSaveDataSplit[4]);
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