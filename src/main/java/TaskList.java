import java.util.ArrayList;

public class TaskList {
    protected boolean isEmpty;
    protected int length;
    protected ArrayList<Task> taskList;

    public TaskList(){
        this.isEmpty = true;
        this.length = 0;
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.isEmpty = false;
        this.length = taskList.size();
        this.taskList = taskList;
    }

    public int getLength() {
        return this.length;
    }

    public void addTask(String item){
        Task task = new Task(item);
        this.taskList.add(task);
        this.length++;
        this.isEmpty = false;
    }

    public String getTask(int i){
        return this.taskList.get(i).toString();
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }

    public Task getTaskObject(int i){
        return this.taskList.get(i);
    }

    public void markDone(int i) {
        Task task = this.taskList.get(i);
        task.setDone(true);
    }

    public void unMark(int i) {
        Task task = this.taskList.get(i);
        task.setDone(false);
    }

}