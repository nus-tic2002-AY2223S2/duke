package duke.TasksType;

public class Todo extends Task
{
    /**
     * Constructor for Todo task type, has description (inherited) member
     * @param description takes in a string input description to describe the Todo
     */
    public Todo(String description) {
        super(description);
     }

    /**
     * This method is called to format the display for Todo tasks
     * @return a string used for display
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " [Priority " + getPriorityLevel() + "]";
    }
}
