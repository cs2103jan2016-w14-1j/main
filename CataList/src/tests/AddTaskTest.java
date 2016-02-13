package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.Test;

import logic.addTask;
import storage.Output;

public class AddTaskTest {
	
	@Test
	public void addTaskWithoutDateTime(){
		String addToDo = "Do CE2";
	
		addTask at = new addTask();
		at.setEvent(addToDo);
		
		Output expectedOutput = new Output(true, "Do CE2", "add", "The event has been added.");
		Output resultedOutput = at.perform();
		assertEquals(expectedOutput, resultedOutput);
	}
}
