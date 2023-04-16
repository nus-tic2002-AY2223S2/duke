import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isEmpty;
    protected int length;
    protected ArrayList<String> taskList;

    public Task(){
        this.isEmpty = true;
        this.length = 0;
        taskList = new ArrayList<>();
    }

    public void addTask(String text){
        this.length++;
        this.isEmpty = false;
        taskList.add(text);
    }

    public String getTask(int i){
        return this.taskList.get(i);
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }

}