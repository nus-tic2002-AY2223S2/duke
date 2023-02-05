
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        List<String> lists = new ArrayList<>();
        while (true){
            String content;
            System.out.print("Please key in command: ");
            Scanner command = new Scanner(System.in);
            content = command.nextLine();

            /*---------------level-2 : ----------------------*/
            if(!content.equals("list") && !content.equals("bye")){
                System.out.println("    ____________________________________________________________");
                lists.add(content);
                System.out.println( "    " + "added: " + content);
                System.out.println("    ____________________________________________________________");

            }
            else if(content.equals("list")){
                System.out.println("    ____________________________________________________________");
                for(String list : lists){
                    int listNum = lists.indexOf(list) + 1;
                    System.out.println("    " + listNum + "." + list);
                }
                System.out.println("    ____________________________________________________________");
            }

            else if(content.equals("bye")){
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
    }
}
