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

    /**
     * save keyword and its enum in map
     * @return hashmap
     */
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

    /**
     * @param inputCommand
     * @return first keyword from input command
     */
    public static String keyCommand(String inputCommand){
        return inputCommand.split(" ",2)[0].trim();
    }

    /**
     * @param inputCommand
     * @return another command split by space from input command
     * @throws DukeException
     */
    public static String restCommand(String inputCommand)throws DukeException {
        String restCommand;
        try{
            restCommand = inputCommand.split(" ",2)[1].trim();
        }catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Please key in keyword again!");
        }
        return restCommand;
    }

    /**
     * @param restCommand add rest command in string array spilt by "/by"
     * @return string array
     */
    public static String[] getDeadline(String restCommand){
        String[] arrayDeadline;
        List<String> list = new ArrayList<>();
        for (String s : restCommand.split("/by", 2)) {
            String trim = s.trim();
            list.add(trim);
        }
        arrayDeadline = list.toArray(new String[2]);
        return arrayDeadline;
    }

    /**
     * @param restCommand add rest command in string array spilt by "/from" and "/to"
     * @return string array
     */
    public static String[] getEvent(String restCommand){
        String[] arrayEvent;
        List<String> list = new ArrayList<>();
        String beforeFrom = restCommand.split("/from", 2)[0].trim();
        list.add(beforeFrom);
        String afterFrom = restCommand.split("/from", 2)[1].trim();
        for (String s : afterFrom.split("/to", 2)) {
            String trim = s.trim();
            list.add(trim);
        }
        arrayEvent = list.toArray(new String[3]);
        return arrayEvent;
    }

    /**
     * @param inputCommand split by space
     * @return command length
     */
    public static int commandLength(String inputCommand){
        return inputCommand.split(" ",2).length;
    }
}
