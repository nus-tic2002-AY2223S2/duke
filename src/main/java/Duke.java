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
        Task taskList = new Task();

        while(isRunning){
            Scanner input = new Scanner(System.in);
            text = input.nextLine();
            switch(text.toLowerCase()){
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    System.out.println(breakLine);
                    if(taskList.isEmpty()){
                        System.out.println("\tNo task is pending now! Please add task first ^ ^\n" + breakLine);
                    }
                    else{
                        for(int i=0; i< taskList.length;i++){
                            int count = i+1;
                            System.out.println("    "+count + ". " + taskList.getTask(i));
                        }
                        System.out.println(breakLine);
                    }
                    break;
                default:
                    taskList.addTask(text);
                    System.out.println(breakLine+ "\tadded: "+ text);
                    System.out.println(breakLine);
                    break;
            }
        }
        System.out.println(breakLine + "\t Bye( '༥' ). Hope to see you again soon!\n"+breakLine);
    }
}
