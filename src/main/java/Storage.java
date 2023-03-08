import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filename;
    public Storage(String filename){
        this.filename = filename;
        File duke_save = new File(filename);

        if(duke_save.exists()){
            try{

                System.out.println("File exist, loading data.");
                /* Try to load the file*/
                Scanner myReader = new Scanner(duke_save);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            }catch (Exception e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }else {
            try{
                /* Try to create a save file*/
                duke_save.createNewFile();
                System.out.println("New save file created: " + duke_save.getAbsoluteFile());
            }catch (IOException io){
                System.out.println(io);
            }
        }
    }
}
