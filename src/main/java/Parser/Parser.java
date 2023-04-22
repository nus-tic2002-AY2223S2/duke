package parser;

import enumeration.enumConstant;

import java.util.*;

import exception.DukeException;

public class Parser {
    private static final HashMap<String, enumConstant> getConstantMap = getNewMap();

    public static enumConstant getCommandConstant(String inputCommand){
        String key = keyCommand(inputCommand);
        return getConstantMap.getOrDefault(key, enumConstant.UNKNOWN);
    }

    /**
     * Saves keyword and its enum in map
     *
     * @return hashmap
     */
    public static HashMap<String, enumConstant> getNewMap() {
        HashMap<String, enumConstant> newMap= new HashMap<>();
        newMap.put("bye", enumConstant.BYE);
        newMap.put("list", enumConstant.LIST);
        newMap.put("mark", enumConstant.MARK);
        newMap.put("unmark", enumConstant.UNMARK);
        newMap.put("delete", enumConstant.DELETE);
        newMap.put("todo", enumConstant.TODO);
        newMap.put("deadline", enumConstant.DEADLINE);
        newMap.put("event", enumConstant.EVENT);
        newMap.put("save", enumConstant.SAVE);
        newMap.put("find", enumConstant.FIND);
        newMap.put("schedule",enumConstant.SCHEDULE);
        return newMap;
    }

    /**
     * Based on the input command keyed by user, we will find first keyword.
     *
     * @param inputCommand command keyed by user
     * @return first keyword
     */
    public static String keyCommand(String inputCommand){
        return inputCommand.split(" ",2)[0].trim();
    }

    /**
     * Based on the input command keyed by user, we will find another half command.
     *
     * @param inputCommand command keyed by user
     * @return another command split by space from input command
     * @throws DukeException
     */
    public static String restCommand(String inputCommand)throws DukeException {
        String restCommand;
        restCommand = inputCommand.split(" ",2)[1].trim();
        if(restCommand.equals("")){
            throw new DukeException("The command after first word cannot be empty.");
        }
        return restCommand;
    }

    /**
     * @param restCommand add rest command in string array spilt by "/by"
     * @return string array
     */
    public static String[] getDeadline(String restCommand){
        return Arrays.stream(restCommand.split("/by", 2))
                .map(String::trim).toArray(o -> new String[2]);
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

    public static String[] getSchedule(String restCommand){
        String[] arraySchedule;
        List<String> list = new ArrayList<>();
        String trim = restCommand.trim();
        list.add(trim);
        arraySchedule = list.toArray(new String[1]);
        return arraySchedule;
    }

    /**
     * @param inputCommand split by space
     * @return command length
     */
    public static int commandLength(String inputCommand){
        return inputCommand.split(" ",2).length;
    }
}
