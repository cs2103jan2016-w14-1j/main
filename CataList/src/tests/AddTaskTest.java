package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.Test;

import logic.AddTask;
import storage.Output;

public class AddTaskTest {
	private static String SYMBOL_SPACE = " ";
	
	@Test
	public void addTaskWithoutDateTime(){
		String addToDo = "Do CE2";
	
		AddTask at = new AddTask();
		at.setEvent(addToDo);
		
		Output expectedOutput = new Output(true,
											addToDo,
											"add", 
											"The event has been added.");
		
		Output resultedOutput = at.execute();
		assertEquals(expectedOutput, resultedOutput);
	}
	
	@Test
	public void addTaskWithDateOnly(){
		String addToDo = "Get senpai to notice me";
		String addDate = "11/1/2016";
		String combinedString = addToDo + SYMBOL_SPACE + addDate;
		
		AddTask at = new AddTask();
		at.setEvent(combinedString);
		
		Output expectedOutput = new Output(true,
											combinedString,
											"add", 
											"The event has been added.");
		Output resultedOutput = at.execute();
		assertEquals(expectedOutput, resultedOutput);
	}
	
	@Test
	public void addTaskWithDateAndTime(){
	//TODO	
	}
}
