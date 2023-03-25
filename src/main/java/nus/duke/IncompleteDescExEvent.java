package nus.duke;

public class IncompleteDescExEvent extends IncompleteDescriptionException{

        private String msg;
        public IncompleteDescExEvent(String msg) {
            super(msg);
        }

        public String toString() {
            return ("Events must contain 1 task, 1 /from and 1 /to");
        }

}
