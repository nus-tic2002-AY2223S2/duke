public class Command {
    public enum CommandType {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, UNDONE, FIND, HELP, INVALID
    }

    private final CommandType command;
    private final String description;
    private final String startTime;
    private final String endTime;
    private final int index;

    public Command(CommandType command, String description, String startTime, String endTime) {
        this.command = command;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.index = -1;
    }

    public Command(CommandType command, String description, String startTime) {
        this.command = command;
        this.description = description;
        this.startTime = startTime;
        this.endTime = "";
        this.index = -1;
    }

    public Command(CommandType command, String description) {
        this.command = command;
        this.description = description;
        this.startTime = "";
        this.endTime = "";
        this.index = -1;
    }

    public Command(CommandType command) {
        this.command = command;
        this.description = "";
        this.startTime = "";
        this.endTime = "";
        this.index = -1;
    }

    public Command(CommandType command, int index) {
        this.command = command;
        this.description = "";
        this.startTime = "";
        this.endTime = "";
        this.index = index;
    }

    public boolean isExit() {
        return this.command.equals(CommandType.BYE);
    }

    public void execute(TaskList tasks, Ui ui) {
        Task task;
        switch (this.command) {
            case BYE:
                System.out.println(ui.showBye());
                break;
            case LIST:
                System.out.println(ui.showList(tasks));
                break;
            case DONE:

                task = tasks.markDone(this.index - 1);
                System.out.println(ui.showDone(tasks, task.toString()));

                break;
            case UNDONE:

                task = tasks.markUndone(this.index - 1);
                System.out.println(ui.showUndone(tasks, task.toString()));

                break;
            case DELETE:

                task = tasks.deleteTask(this.index - 1);
                System.out.println(ui.showDelete(tasks, task.toString()));

                break;
            case TODO:
                Task todo = new Todo(this.description);
                tasks.addTask(todo);
                System.out.println(ui.showAdd(tasks, todo.toString()));
                break;
            case DEADLINE:
                Task deadlineTask = new Deadline(description, startTime);
                tasks.addTask(deadlineTask);
                System.out.println(ui.showAdd(tasks, deadlineTask.toString()));
                break;
            case EVENT:
                Task eventTask = new Event(description, startTime, endTime);
                tasks.addTask(eventTask);
                System.out.println(ui.showAdd(tasks, eventTask.toString()));
                break;
            case HELP:
                System.out.println(ui.showHelp());
                break;

            default:
                System.out.println(ui.showInvalid());
                break;
        }
    }
}

