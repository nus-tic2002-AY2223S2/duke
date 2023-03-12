package Task;

import enumCommand.enumCommand;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, enumCommand> getNewMap() {
        HashMap<String,enumCommand> newMap= new HashMap<>();
        newMap.put("bye", enumCommand.BYE);
        newMap.put("list", enumCommand.LIST);
        newMap.put("mark", enumCommand.MARK);
        newMap.put("unmark", enumCommand.UNMARK);
        newMap.put("delete", enumCommand.DELETE);
        newMap.put("todo", enumCommand.TODO);
        newMap.put("deadline", enumCommand.DEADLINE);
        newMap.put("event", enumCommand.EVENT);
        return newMap;
    }
    public static String keyCommand(String inputCommand){
        return inputCommand.split(" ",2)[0].trim();
    }

    public static String restCommand(String inputCommand){
        return inputCommand.split(" ",2)[1].trim();
    }

    public static int commandLength(String inputCommand){
        return inputCommand.split(" ",2).length;
    }
}
