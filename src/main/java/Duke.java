import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Logo
        String logo = " \t .ᕱ⠀⠀⠀ᕱ⠀ ⠀➶ ⠀➴        \n"
                + "\t \u200E(๑◕ܫ◕๑) ➶⠀⠀⠀ ⠀➴ \n"
                + "\t \u200E૮⠀⠀⑅ ⠀づ ⠀⠀⠀⠀⠀⠀⠀➵ Let's Chat with me ♡\n";
        //Break line
        String breakLine = "\t♡____________________________________________________________♡\n";
        // Greeting
        String GreetingMsg = "\t ᑋᵉᑊᑊᵒ ᵕ̈ ᑋᵉᑊᑊᵒ I'm Dululu\n" + "\t What can I do for you? (°◡°♡).:｡\n";

        //Output of Header part
        System.out.println("\t Hello from Lily \u200E|•'-'•) ✧\n" + logo);
        System.out.println(breakLine+ GreetingMsg+ breakLine);

        boolean isRunning = true;
        String text;
        TaskList taskList = new TaskList();

        while(isRunning){
            Scanner input = new Scanner(System.in);
            text = input.nextLine();
            String[] inputWords = text.split("\\s+", 2);
            String command = inputWords[0].toLowerCase();
            int taskNum = -1;
            if(inputWords.length >1){
                try {
                    taskNum = Integer.parseInt(inputWords[1]) - 1;
                } catch (NumberFormatException e) {
                }
            }
            switch(command){
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    System.out.println(breakLine);
                    if(taskList.isEmpty()){
                        System.out.println("\tNo task is pending now! Please add task first Ծ‸Ծ\n" + breakLine);
                    }
                    else{
                        for(int i=0; i< taskList.getLength();i++){
                            int count = i+1;
                            System.out.println("    "+count + ". " + taskList.getTask(i));
                        }
                        System.out.println(breakLine);
                    }
                    break;
                case "mark":
                    if (taskNum < 0 || taskNum >= taskList.getLength()) {
                        System.out.println(breakLine + "\t (·•᷄ࡇ•᷅ ）Invalid task number! Please double Check");
                    } else {
                        taskList.markDone(taskNum);
                        System.out.println(breakLine + "\tNice (•̀ᴗ• )! I've marked this task as done:" + "\n\t  " + taskList.getTask(taskNum));
                    }
                    System.out.println(breakLine);
                    break;
                case "unmark":
                    if (taskNum < 0 || taskNum >= taskList.getLength()) {
                        System.out.println(breakLine + "\t (·•᷄ࡇ•᷅ ）Invalid task number! Please double Check");
                    } else {
                        taskList.unMark(taskNum);
                        System.out.println(breakLine + "\tOK ⚆_⚆, I've marked this task as not done yet:" + "\n\t  " + taskList.getTask(taskNum));
                    }
                    System.out.println(breakLine);
                    break;
                default:
                    taskList.addTask(text);
                    System.out.println(breakLine+ "\t๐•ᴗ•๐ added: "+ text);
                    System.out.println(breakLine);
                    break;
            }
        }
        System.out.println(breakLine + "\t Bye( '༥' ). Hope to see you again soon!\n"+breakLine);
    }
}
