public class Parser {

    public boolean isListCommand(String userInput){
        return userInput.equals("list");
    }
    public boolean isDeleteCommand(String userInput){
        return userInput.startsWith("delete");
    }
    public boolean isMarkCommand(String userInput){
        return userInput.startsWith("mark");
    }
    public boolean isUnmarkCommand(String userInput){
        return userInput.startsWith("unmark");
    }
    public boolean isToDoCommand(String userInput){
        return userInput.startsWith("todo");
    }
    public boolean isDeadlineCommand(String userInput){
        return userInput.startsWith("deadline");
    }
    public boolean isEventCommand(String userInput){
        return userInput.startsWith("event");
    }

    public Task createToDo(String userInput) throws DukeException {
        String[] stringSplit = new String[1];
        stringSplit[0] = userInput.replace("todo", "").trim();
        if (stringSplit[0].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }else {
            return new ToDo(stringSplit[0],"T");
        }
    }

    public Task createDeadline(String userInput) throws DukeException {
        String description;
        String[] formattedString;

        formattedString = formatString(userInput, "deadline");
        description = formattedString[0];
        String by = formattedString[1];

        return new Deadline(description,"D",by);
    }

    public Task createEvent(String userInput) throws DukeException {
        String description;
        String[] formattedString;

        formattedString = formatString(userInput, "event");
        description = formattedString[0];
        String from = formattedString[1];
        String to = formattedString[2];
        return new Event(description,"E",from,to);
    }

    public static String[] formatString(String userInput, String status) throws DukeException {
        String formatString = "";
        String[] stringSplit = new String[3];
        switch (status) {
            case "todo":
                stringSplit[0] = userInput.replace("todo", "").trim();
                if (stringSplit[0].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                formatString = userInput.replace("deadline", "").trim();
                String[]  formatDeadlineSplit = formatString.split("/");

                if (formatDeadlineSplit.length < 2){
                    throw new DukeException("deadline is missing by details, please try again");
                }else if (formatDeadlineSplit.length > 2){
                    throw new DukeException("deadline has too many parameters, please try again");
                }else {
                    System.out.println("formatDeadlineSplit.length: " + formatDeadlineSplit.length);
                    formatDeadlineSplit[1] = formatDeadlineSplit[1].replace("by", "").trim();
                    return formatDeadlineSplit;
                }

            case "event":
                formatString = userInput.replace("event", "").trim();
                String[] formatEventSplit = formatString.split("/");
                if (formatEventSplit.length <3){
                    throw new DukeException("Event is missing either from or to, please try again");
                } else  if (formatEventSplit.length >3){
                    throw new DukeException("Event has too many parameters, please try again");
                }else {
                    System.out.println(formatEventSplit[1]);
                    formatEventSplit[1] = formatEventSplit[1].replace("from", "").trim();
                    formatEventSplit[2] = formatEventSplit[2].replace("to", "").trim();
                    return formatEventSplit;

                }
            default:
                break;
        }
        return stringSplit;
    }

}
