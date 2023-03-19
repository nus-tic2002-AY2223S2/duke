package Parser;

import enumCommand.Constant;
import java.util.HashMap;

public class Parser {
    private static final HashMap<String,Constant> getConstantMap = getNewMap();

    public static Constant getCommandConstant(String inputCommand){
        String key = keyCommand(inputCommand);
        return getConstantMap.getOrDefault(key,Constant.UNKNOWN);
    }

    public static HashMap<String, Constant> getNewMap() {
        HashMap<String, Constant> newMap= new HashMap<>();
        newMap.put("bye", Constant.BYE);
        newMap.put("list", Constant.LIST);
        newMap.put("mark", Constant.MARK);
        newMap.put("unmark", Constant.UNMARK);
        newMap.put("delete", Constant.DELETE);
        newMap.put("todo", Constant.TODO);
        newMap.put("deadline", Constant.DEADLINE);
        newMap.put("event", Constant.EVENT);
        newMap.put("save", Constant.SAVE);
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
