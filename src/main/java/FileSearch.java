import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {

    public void parseFile(String searchStr) throws FileNotFoundException{
        String fileName = "src/main/task.txt";
        Scanner scan = new Scanner(new File(fileName));
        while(scan.hasNext()){
            String line = scan.nextLine().toLowerCase().toString();
            if(line.contains(searchStr)){
                System.out.println(line);
            }
        }
    }

}