import java.util.HashMap;

public class Duke {
    public static void main(String[] args) {
        DukeInit.duke();

        HashMap<String, enumCommand> newMap = new HashMap<>();
        newMap.put("bye", enumCommand.BYE);
        newMap.put("list", enumCommand.LIST);
        newMap.put("mark", enumCommand.MARK);
        newMap.put("unmark", enumCommand.UNMARK);
        newMap.put("delete", enumCommand.DELETE);
        newMap.put("todo", enumCommand.TODO);
        newMap.put("deadline", enumCommand.DEADLINE);
        newMap.put("event", enumCommand.EVENT);

        String inputCommand, key;
        enumCommand enumCommand;
        TasksList tasksList = new TasksList();

        whileLoop:
        while (true) {
            inputCommand = DukeInit.inputCommand();
            key = DukeInit.keyCommand(inputCommand);
            enumCommand = newMap.get(key);

            try {
                if (enumCommand == null) {
                    enumCommand = enumCommand.UNKNOWN;
                }

                if (enumCommand.equals(enumCommand.BYE)) {
                    DukeInit.Echo("Bye. Hope to see you again soon!");
                    break whileLoop;
                }
                enumCommand.command(tasksList, inputCommand);
            } catch (DukeException e) {
                DukeInit.Echo(e.getMessage());
            }
        }
    }
}

