package Parser;

import enumCommand.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Exception.DukeException;

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

    public static String restCommand(String inputCommand)throws DukeException {
        String restCommand;
        try{
            restCommand = inputCommand.split(" ",2)[1].trim();
        }catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Please key in keyword again!");
        }
        return restCommand;
    }

    public static String[] getDeadline(String inputCommand){
        String[] arrayDeadline;
        List<String> list = new ArrayList<>();
        for (String s : inputCommand.split("/by", 2)) {
            String trim = s.trim();
            list.add(trim);
        }
        arrayDeadline = list.toArray(new String[2]);
        return arrayDeadline;
    }

    public static String[] getEvent(String inputCommand){
        String[] arrayEvent;
        List<String> list = new ArrayList<>();
        for (String s : inputCommand.split("/from", 2)) {
            String trim = s.trim();
            list.add(trim);
        }
        arrayEvent = list.toArray(new String[2]);
        return arrayEvent;
    }

    public static int commandLength(String inputCommand){
        return inputCommand.split(" ",2).length;
    }
}
