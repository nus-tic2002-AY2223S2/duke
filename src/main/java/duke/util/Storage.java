package duke.util;

import duke.exceptions.DukeException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Storage class will storage the tasks data in sql
 */
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load methods is used to low the existing tasks data from sql
     * @return the database of task list
     * @throws DukeException Invalid date format in database
     */
    public TaskList load() throws DukeException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.filePath);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");
            TaskList tasks = new TaskList();
            while (rs.next()) {
                String taskType = rs.getString("type");
                String taskDescription = rs.getString("description");
                String taskStatus = rs.getString("done");
                String taskStartDate = rs.getString("start_time");
                String taskEndDate = rs.getString("end_time");
                switch (taskType) {
                    case "T":
                        Todo todo = new Todo(taskDescription);
                        if (taskStatus.equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.addTask(todo);
                        break;
                    case "D":
                        try {
                            Deadline deadline = new Deadline(taskDescription, LocalDateTime.parse(taskEndDate));
                            if (taskStatus.equals("1")) {
                                deadline.markAsDone();
                            }
                            tasks.addTask(deadline);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date format in database");
                        }
                        break;
                    case "E":
                        try {
                            Event event = new Event(taskDescription, LocalDateTime.parse(taskStartDate), LocalDateTime.parse(taskEndDate));
                            if (taskStatus.equals("1")) {
                                event.markAsDone();
                            }
                            tasks.addTask(event);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date format in database");
                        }
                        break;
                }

            }
            return tasks;
        } catch (SQLException e) {
            throw new DukeException(e.getMessage());
        }
    }
    public void save(TaskList tasks) throws DukeException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.filePath);
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS tasks");
            stmt.execute("CREATE TABLE tasks(id INTEGER PRIMARY KEY, type TEXT NOT NULL, description TEXT NOT NULL, start_time TEXT, end_time TEXT, done INTEGER NOT NULL)");
            for (int i = 0; i < tasks.getLength(); i++) {
                Task task = tasks.getTask(i);
                String taskType = task.getTaskType();
                String taskDescription = task.getDescription();
                String taskStatus = task.getTaskStatusInString();
                String taskStartDate = task.getTaskStartTime();
                String taskEndDate = task.getTaskEndTime();
                String sql = "INSERT INTO tasks(id, type, description, start_time, end_time, done) \n"
                        + "VALUES (null, '" + taskType + "', '" + taskDescription + "', '" + taskStartDate + "', '" + taskEndDate + "', " + taskStatus + ")";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
