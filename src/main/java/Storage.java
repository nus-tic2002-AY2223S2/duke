import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<String> ReadFile(String datapath) throws FileNotFoundException {
        try {
            File myObj = new File(datapath);
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> list = new ArrayList<String>();

            while (myReader.hasNextLine()){
                list.add(myReader.nextLine());
            }

            myReader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    private static final String newLine = System.getProperty("line.separator");

    public static void WriteFile(String msg) throws IOException {
        String fileName = "src/main/task.txt";
        PrintWriter printWriter = null;
        File file = new File(fileName);

        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(newLine + msg);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public static void RewriteFile (String stringToReplace, String replaceWith) throws IOException {
        {
            String fileName = "src/main/task.txt";
            String oldContent = "";
            BufferedReader reader = null;
            FileWriter writer = null;

            try {
                reader = new BufferedReader(new FileReader(fileName));
                //Reading all the lines of input text file into oldContent
                String line = reader.readLine();

                while (line != null) {
                    oldContent = oldContent + line + System.lineSeparator();
                    line = reader.readLine();
                }

                //Replacing oldString with newString in the oldContent
                String newContent = oldContent.replaceAll(stringToReplace, replaceWith);
                //Rewriting the input text file with newContent
                writer = new FileWriter(fileName);
                writer.write(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    }
