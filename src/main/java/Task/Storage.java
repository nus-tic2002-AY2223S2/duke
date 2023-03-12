package Task;
import Exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage() throws DukeException {
//        String home = System.getProperty("user.home");
//        java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir");
//        boolean directoryExists = java.nio.file.Files.exists(path);
        this.filePath = System.getProperty("user.dir") + "/data/duke.txt";
        fileCreate();
    }

    private void fileCreate() throws DukeException{
        try{
            File f = new File(this.filePath);
            if(!f.exists()){
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void writeToFile(String textToAdd) throws DukeException{
        try{
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToAdd);
            fw.close();
        }catch (IOException e){
            throw new DukeException("File write get problem!");
        }
    }

    public void readFileContents(TasksList lists) throws DukeException{
        try{
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                lists.taskAdd(taskByLine(s.nextLine()));
            }
        }catch (FileNotFoundException e){
            throw new DukeException("File read get problem!");
        }
    }
    public static Task taskByLine(String nextLine) {
        String[] body = nextLine.split(" | ");
        Task task = null;
        switch (body[0]){
            case "T":
                if(body[1].equals("1")){
                    task.Mark();
                }
                task = new Todo(body[2]);
                break;
            case "D":
                if(body[1].equals("1")){
                    task.Mark();
                }
                task = new Deadline(body[2],body[3]);
                break;
            case "E":
                if(body[1].equals("1")){
                    task.Mark();
                }
                task = new Event(body[2],body[3]);
                break;
        }
        return task;
    }

}
