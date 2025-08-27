package luke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void listTasksTest() {
        TaskList tasklist = new TaskList("list", new ArrayList<>());
        assertEquals(0, tasklist.tasks.size());
    }

    @Test
    public void addTaskTest() {
        TaskList tasklist = new TaskList("list", new ArrayList<>());
        tasklist.addTask("todo buy bread");
        assertEquals(1, tasklist.tasks.size());
        assertEquals("buy bread", tasklist.tasks.get(0).description);
    }

    @Test
    public void markTaskTest() {
        TaskList tasklist = new TaskList("list", new ArrayList<>());
        tasklist.addTask("todo buy bread");
        tasklist.markTask("mark 1");
        assertTrue(tasklist.tasks.get(0).isCompleted);
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasklist = new TaskList("list", new ArrayList<>());
        tasklist.addTask("todo buy bread");
        tasklist.deleteTask("delete 1");
        assertEquals(0, tasklist.tasks.size());
    }
}
