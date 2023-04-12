import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;


public class Duke {


    //Read from Existing File and update list
    private static int printFileContents(String filePath, ArrayList<Task>tasks) throws FileNotFoundException {

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int count=0;
        while (s.hasNext()) {
            String re= s.nextLine();
            //System.out.println("reading lines");
            if(re.substring(0,1).equals("[")){

                if(re.substring(1,2).equals("T")){


                    tasks.add(new Todo(re.substring(7)));
                    if(re.charAt(4)=='X'){
                        tasks.get(count).markAsDone();
                    }
                    count++;
                }

                else if(re.substring(1,2).equals("D")){

                    //System.out.println("Task deadline found, adding in");
                    int Stindex = 7;
                    int index= re.indexOf("by")+4;
                    int endex= re.indexOf(')');

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy");
                    String date = re.substring(index,endex);

                    LocalDate localDate = LocalDate.parse(date, formatter);
                    date=localDate.toString();

                    tasks.add(new Deadline(re.substring(Stindex, index-6), date));
                    if(re.charAt(4)=='X'){
                        tasks.get(count).markAsDone();
                    }
                    count++;
                }

                else if(re.substring(1,2).equals("E")){

                    //System.out.println("Task event found, adding in");
                    int Stindex = 7;
                    int index= re.indexOf("at")+4;
                    int endex= re.indexOf(')');

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy");
                    String date = re.substring(index,endex);

                    LocalDate localDate = LocalDate.parse(date, formatter);
                    date=localDate.toString();

                    tasks.add(new Event(re.substring(Stindex, index-6), date));

                    if(re.charAt(4)=='X'){
                        tasks.get(count).markAsDone();
                    }

                    count++;
                }
            }
        }
        System.out.println("Saved Tasks add to the list successfully!!!");
        return count;
    }


    public static int readt(ArrayList<Task>tasks) {
        int count=0;
        try {
            String homePath = System.getProperty("user.home");
            String fileName = "DukeText.txt";
            String filePath = homePath + File.separator + fileName;
            System.out.println("filePath = " + filePath);

            count= printFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return count;
    }



    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
        System.out.println("\nTasks saved Successfully!!");
    }

    //checking if the task has description
    static void validate(String s, String tas) throws DukeException {
        if (s.equals(tas)) {

            // throw an object of user defined exception
            throw new DukeException(); //tas+ " cannot be empty. Please try again");
        }

    }

    //checking if a data has been given
    static void validateDate(int ind) throws DukeException2 {
        if (ind==-1) {throw new DukeException2();}

    }

    //checking if the date is given in correct format
    static void validateDate2(String date) throws DateTimeParseException {

        LocalDate d1= LocalDate.parse(date);

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nPlease wait while I check for saved tasks.\n");

        Task[] t= new Task[10];
        ArrayList<Task>tasks= new ArrayList<>();
        String temp;
        String ts="null";
        int i=1;
        int j=0;
        //Read the existing text file here using a function. Bring back the edited string here to be added into Task
        System.out.println("Checking for saved data.................\n...............\n.............");
        j=readt(tasks);
        System.out.println("\nYou're all set! \nSo, What's on your mind today?\n");
        System.out.println("Below are the list of things you can do\n");
        System.out.println("1.Add Tasks:\n i.todo Task Name\n ii.deadline Task Name/Date\n iii.event Task_Name/Date\n2.Display List of Tasks- list\n3.Find Task- find keyword\n4.Delete Task- delete number\n5.Mark/Unmark Task- mark/unmark number\n");
        while(i==1){
            System.out.println("Waiting for your command....\n");
            Scanner in = new Scanner(System.in);
            temp = in.nextLine();

            try {                                           //try new


                //display list content
                if (temp.equals("list")) {
                    System.out.println("\nContent of your List\n");



                    // below new array list***************************************
                    //System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    int k=0;
                    while(k<j){

                        System.out.println(k + 1 + ". " + tasks.get(k));
                        k++;
                    }
                    System.out.println("\nEnd of list\n");

                }

                //end loop and close program
                else if (temp.equals("bye")) {
                    i = 0;
                }

                else if (temp.substring(0, 3).equals("unm")) {
                    //unmark
                    int num = Integer.parseInt(temp.substring(7));


                    //below array list****************************************

                    tasks.get(num-1).markAsUndone();
                    System.out.println(tasks.get(num - 1) + "\n");
                }

                else if (temp.substring(0, 3).equals("mar")) {
                    //mark
                    int num = Integer.parseInt(temp.substring(5));


                    //below array list****************************************

                    tasks.get(num-1).markAsDone();
                    System.out.println("Awesome! The task has been marked done!\n");
                    System.out.println(tasks.get(num - 1) + "\n");

                }

                else if (temp.substring(0, 4).equals("todo")) {

                    ts="todo";
                    validate(temp, temp.substring(0, 4)); //Check if todo is empty


                    j++;




                    //below array list****************************************

                    tasks.add(new Todo(temp.substring(5)));
                    System.out.println("----------~~~~~~~~-----------");
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");
                    System.out.println("----------~~~~~~~~-----------");

                }

                else if (temp.substring(0, 5).equals("event")) {

                    ts="event";
                    validate(temp, temp.substring(0, 5));

                    int index = temp.indexOf('/');
                    validateDate(index);
                    validateDate2(temp.substring(index+1));

                    j++;

                    //below array list****************************************


                    tasks.add(new Event(temp.substring(6, index), temp.substring(index + 1)));
                    System.out.println("----------~~~~~~~~-----------");
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");
                    System.out.println("----------~~~~~~~~-----------");
                }

                else if (temp.substring(0, 6).equals("delete")) {

                    int num = Integer.parseInt(temp.substring(7));
                    Task g= tasks.get(num-1);
                    tasks.remove(num-1);
                    j--;
                    System.out.println("Noted. The task has been removed");
                    System.out.println(g);
                    System.out.println("Now you have " + j + " tasks in your list");
                    System.out.println("BELOW IS THE NEW LIST after delete\n");
                    int k=0;
                    while(k<j){

                        System.out.println(k + 1 + ". " + tasks.get(k));
                        k++;
                    }
                    System.out.println("\nEnd of list\n");

                }


                else if (temp.substring(0, 8).equals("deadline")) {

                    ts="deadline";
                    validate(temp, temp.substring(0, 8));

                    int index = temp.indexOf('/');
                    validateDate(index);
                    validateDate2(temp.substring(index+1));


                    j++;


                    //below array list****************************************
                    //    System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    tasks.add(new Deadline(temp.substring(9, index), temp.substring(index + 1)));
                    System.out.println("----------~~~~~~~~-----------");
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");
                    System.out.println("----------~~~~~~~~-----------");
                }

                else if(temp.substring(0, 4).equals("find")){

                    String find= temp.substring(5);
                    int l=0;
                    System.out.println("Here are the results of your search:");
                    while(l<j){

                        if(tasks.get(l).description.contains(find)){
                            System.out.println(l + 1 + ". " + tasks.get(l));
                        }
                        l++;
                    }
                    System.out.println("End of result");
                }

                //
                else {
                    System.out.println("Type bye to exit or check your input and try again.\n");
                }
                //exceptions
            } catch (IndexOutOfBoundsException e){              //Catch
                System.out.println("oopss!! Please check your input and try again or type bye to exit.\n");
            } catch (DukeException e){
                System.out.println("Error : "+ ts + " cannot be empty. Please try again\n");//+ e);
            }catch (DukeException2 e){
                System.out.println("Error : For "+ ts + " Please enter date (Format:yyyy-mm-dd) after '/' and try again\n");//+ e);
            }catch (DateTimeParseException e){
                System.out.println("Error : For "+ ts + " Please enter date using correct Format:yyyy-mm-dd and try again\n");
            }

        }

        //write to file
        System.out.println("Saving your tasks........\n");
        //String file1= "/Users/nikhilshankar/Duke Test/DukeText.txt";
        String homePath = System.getProperty("user.home");
        String fileName = "DukeText.txt";
        String filePath = homePath + File.separator + fileName;
        System.out.println("filePath = " + filePath);
        int k=0;
        String line2="Duke Task List:";
        while(k<j) {
            String line1;
            System.out.println(k + 1 + ". " + tasks.get(k));

            line1= tasks.get(k).toString();
            line2= line2+"\n"+line1;



            k++;
        }
        try {
            writeToFile(filePath,line2 );
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println("Byeee!! See you Again!!");
    }
}
