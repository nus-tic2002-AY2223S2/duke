package storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    // Create a temporary file to test saving and loading tasks
    void saveAndLoadTasks_saveAndLoadTasksToFile_success(@TempDir Path tempDir) throws Exception {
        File tempFile = new File(tempDir.toString() + "/temp.txt");
        Storage storage = new Storage(tempFile.getAbsolutePath());

        TaskList tasksToSave = new TaskList();
        tasksToSave.add(new ToDo("buy fish"));
        tasksToSave.add(new ToDo("sell soul"));

        storage.saveTasks(tasksToSave);
        TaskList loadedTasks = storage.loadTasks();

        assertEquals(tasksToSave.size(), loadedTasks.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            Task taskToSave = tasksToSave.get(i);
            Task loadedTask = loadedTasks.get(i);
            assertEquals(taskToSave.toString(), loadedTask.toString());
            assertEquals(taskToSave.toFileFormat(), loadedTask.toFileFormat());
        }
    }
}
