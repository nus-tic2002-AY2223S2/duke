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
    public void save(TaskList userTask){

        try{
            FileWriter myWriter = new FileWriter("data\\duke.txt");
            for (int i = 0; i < userTask.getTaskSize(); i++) {
                int result = userTask.getTask(i).getStatus().equals("X") ? 1 : 0;

                String testing = userTask.getTask(i).getTaskType() + " | " + result + " | " +  userTask.getTask(i).getDescription();

                String finaltesting = testing+ userTask.getTaskDetails(testing,i);


                System.out.println(finaltesting);

                try {

                  myWriter.write(finaltesting + "\n");

                    // System.out.println("Successfully wrote to the file.");
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

/*
        public TaskList load () {

            TaskList tasks;
            Task t;
            try {

                System.out.println("File exist, loading data.");
                //Try to load the file
                Scanner myReader = new Scanner(duke_save);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    tasks.addTask(t);
                }
                myReader.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();


            }

            return tasks;
        }
*/



}