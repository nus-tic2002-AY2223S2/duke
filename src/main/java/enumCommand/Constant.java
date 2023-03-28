package enumCommand;
import ExecuteCommand.*;
import Parser.Parser;

public enum Constant implements Execute {
    BYE{
        /**
         * create new instance and implement execute method
         * @param inputCommand
         */
        public void execute(String inputCommand){
            new BYE().execute();
        }
    },

    LIST{
        public void execute(String inputCommand){
            new LIST().execute();
        }
    },
    MARK{
        public void execute(String inputCommand){
            new MARK(inputCommand).execute();
        }
    },
    UNMARK{
        public void execute(String inputCommand){
            new UNMARK(inputCommand).execute();
        }
    },
    DELETE{
        public void execute(String inputCommand) {
            new DELETE(inputCommand).execute();
        }
    },
    TODO{
        public void execute(String inputCommand){
            new TODO(inputCommand).execute();
        }
    },
    DEADLINE{
        public void execute(String inputCommand){
            new DEADLINE(inputCommand).execute();
        }
    },
    EVENT{
        public void execute(String inputCommand){
            new EVENT(inputCommand).execute();
        }
    },
    UNKNOWN{
        public void execute(String inputCommand){
            new UNKNOWN().execute();
        }
    },
    SAVE{
        public void execute(String inputCommand){
            new SAVE().execute();
        }
    }
}
