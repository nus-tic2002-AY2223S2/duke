import java.util.ArrayList;

public class TasksList {
    public ArrayList<Task> lists;

    public TasksList(){
        this.lists = new ArrayList<>();
    }

    public void taskAdd(Task task){
        lists.add(task);
        DukeInit.Echo("Got it. I've added this task: \n  " + task +
                "\n Now you have " + lists.size() + " tasks in the list.");
    }

    public void mark(int index){
        Task task = lists.get(index);
        task.Mark();
        DukeInit.Echo("Nice! I've marked this task as done:\n  " + task);
    }

    public void unmark(int index){
        Task task = lists.get(index);
        task.unMark();
        DukeInit.Echo("OK, I've marked this task as not done yet:\n  " + task);
    }

    public void deleteTask(int index){
        Task task = lists.get(index);
        lists.remove(index);
        DukeInit.Echo("Noted. I've removed this task:\n  " + task +
                "\n Now you have " + lists.size() + " tasks in the list.");
    }

}
