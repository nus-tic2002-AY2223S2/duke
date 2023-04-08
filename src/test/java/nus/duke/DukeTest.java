package nus.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void testAddTask_shouldIncrementedTotalCount() {

        ArrayList a =  new ArrayList<>();
        final TaskList list = new TaskList(a);

        final int initialNoOfTasks = list.getSize();
        String description = "abc";
        list.add(new ToDo (description));
        final int newNoOfTasks = list.getSize();
        assertEquals(initialNoOfTasks + 1, newNoOfTasks );
    }

    @Test
    public void testRemoveTask_shouldDecrementedTotalCount() {

        ArrayList a =  new ArrayList<>();
        final TaskList list = new TaskList(a);
        String description = "abc";

        final Task taskToRemove = new ToDo(description);
        list.add((ToDo) taskToRemove);
        final int initialNoOfTasks = list.getSize();
        list.remove(0);
        final int newNoOfTasks = list.getSize();
        assertEquals(initialNoOfTasks - 1, newNoOfTasks );
    }

    @Test
    public void testMarkCommand_shouldShowIsDone() {
        final TaskList list = new TaskList();
        String description = "abc";
        final Task taskToAdd = new ToDo(description);
        taskToAdd.markAsDone();
        assertTrue(taskToAdd.isDone);
    }

    @Test
    public void testUnmarkCommand_shouldShowFalse() {
        final TaskList list = new TaskList();
        String description = "abc";
        final Task taskToAdd = new ToDo(description);
        taskToAdd.markAsDone();
        taskToAdd.markAsNotDone();
        assertFalse(taskToAdd.isDone);
    }
    
}