import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " \t .ᕱ⠀⠀⠀ᕱ⠀ ⠀➶ ⠀➴        \n"
                + "\t \u200E(๑◕ܫ◕๑) ➶⠀⠀⠀ ⠀➴ \n"
                + "\t \u200E૮⠀⠀⑅ ⠀づ ⠀⠀⠀⠀⠀⠀⠀➵ Let's Chat with me ♡\n";

        String breakLine = "\t♡____________________________________________________________♡\n";
        String text;
        Scanner input = new Scanner(System.in);
        System.out.println("\t Hello from Lily \u200E|•'-'•) ✧\n" + logo);
        System.out.println(breakLine+"\t ᑋᵉᑊᑊᵒ ᵕ̈ ᑋᵉᑊᑊᵒ I'm Dululu\n" + "\t What can I do for you? (°◡°♡).:｡\n" + breakLine);
        text = input.nextLine();
        //System.out.println(breakLine + text + breakLine);
        while(!text.equalsIgnoreCase("bye")) {
            System.out.printf("%s\t %s\n%s", breakLine, text, breakLine);
            text = input.nextLine();
        }
        System.out.println(breakLine + "\t Bye( '༥' ). Hope to see you again soon!\n"+breakLine);
    }
}
