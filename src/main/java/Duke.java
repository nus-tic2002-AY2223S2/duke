import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {

            String fullCommand = ui.readCommand();
            System.out.println(ui.showLine());
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui);
            isExit = c.isExit();
        }
    }
}
