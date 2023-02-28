public enum enumCommand {
    BYE {
        public void command(TasksList list, String inputCommand){

        }
    },
    LIST{
        public void command(TasksList list, String inputCommand){
            DukeInit.printList(list.lists);
        }
    },
    MARK{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! Mark number can not be empty.");
            }
            int markNum = Integer.parseInt(DukeInit.restCommand(inputCommand)) - 1;
            if(markNum >= list.lists.size()){
                throw new DukeException("☹ OOPS!!! Please key in correct number!");
            }
            list.mark(markNum);
        }
    },
    UNMARK{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! Unmark number can not be empty.");
            }
            int markNum = Integer.parseInt(DukeInit.restCommand(inputCommand)) - 1;
            if(markNum >= list.lists.size()){
                throw new DukeException("☹ OOPS!!! Please key in correct number!");
            }
            list.unmark(markNum);
        }
    },
    DELETE{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! Deleted number can not be empty.");
            }
            int deleteNum = Integer.parseInt(DukeInit.restCommand(inputCommand)) - 1;
            if(deleteNum >= list.lists.size()){
                throw new DukeException("☹ OOPS!!! Please key in correct number!");
            }
            list.deleteTask(deleteNum);
        }
    },
    TODO{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task todo = new Todo(DukeInit.restCommand(inputCommand));
            list.taskAdd(todo);
        }
    },
    DEADLINE{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String beforeBy,afterBy;
            beforeBy = DukeInit.restCommand(inputCommand).split("/by")[0];
            afterBy = DukeInit.restCommand(inputCommand).split("/by")[1];
            Task deadline = new Deadline(beforeBy,afterBy);
            list.taskAdd(deadline);
        }
    },
    EVENT{
        public void command(TasksList list, String inputCommand) throws DukeException{
            if(DukeInit.commandLength(inputCommand) < 2){
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            String beforeFrom,afterFrom;
            beforeFrom = DukeInit.restCommand(inputCommand).split("/from",2)[0];
            afterFrom = DukeInit.restCommand(inputCommand).split("/from",2)[1];
            afterFrom = afterFrom.replace("/to","to:");
            Task event = new Event(beforeFrom,afterFrom);
            list.taskAdd(event);
        }
    },
    UNKNOWN{
        public void command(TasksList list, String inputCommand) throws DukeException{
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    };
    public abstract void command(TasksList list, String inputCommand) throws DukeException;
}
