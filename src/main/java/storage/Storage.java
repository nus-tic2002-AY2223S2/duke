package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.Task;
import tasklist.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath; File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Unable to create new file.");
            }
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : tasks) {
                String taskString = task.toFileFormat();
                fw.write(taskString + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks.");
        }
    }

    public TaskList loadTasks() throws FileNotFoundException {
        File file = new File(filePath);
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Task.fromFileFormat(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }
}

