package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicMainEditTest {
	LogicMain logicTest;
	
	@Before
	public void init(){
		logicTest = new LogicMain();
		String userInput6 = "clear";
		String testOutput = logicTest.processCommand(userInput6);
		String userInputAddToEditNoDateTime = "add editThisCase1";
		String userInputAddToEditDate = "add editThisCase2 10feb";
		String userInputAddToEditDateTime = "add editThisCase3 10feb 1pm";
		String userInputAddToEditStartEndDates = "add editThisCase4 10feb 11feb";
		String userInputAddToEditStartEndDateTime = "add editThisCase5 10feb 1pm 11feb 2pm";
		
		logicTest.processCommand(userInputAddToEditNoDateTime);
		logicTest.processCommand(userInputAddToEditDate);
		logicTest.processCommand(userInputAddToEditDateTime);
		logicTest.processCommand(userInputAddToEditStartEndDates);
		logicTest.processCommand(userInputAddToEditStartEndDateTime);
	}
	
	@Test
	public void testSimpleEdit(){
		String userInput = "edit 1 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThatCase";
		assertEquals(testEventResult, expectedEventResult);
	}
	
	@Test
	public void testSimpleEditToDate(){
		String userInput = "edit 1 10feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "10/02/16";
		assertEquals(testEventDate, expectedEventDate);
	}
	
	@Test
	public void testSimpleEditToTime(){
		String userInput = "edit 1 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	//TODO: BUGGED
	@Test
	public void testSimpleEditToDateTime(){
		String userInput = "edit 1 10feb 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "10/02/16";
		assertEquals(testEventDate, expectedEventDate);
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testDateEdit(){
		String userInput = "edit 2 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		for(Task eachTask : testList){
			System.out.println(eachTask.get_task());
		}
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThatCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "10/02/16";
		assertEquals(testEventDate, expectedEventDate);
	}
	
	@Test
	public void testDateEditToDate(){
		String userInput = "edit 2 11feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(1);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
	}
	
	@Test
	public void testDateEditToTime(){
		String userInput = "edit 1 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "10/02/16";
		assertEquals(testEventDate, expectedEventDate);
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testDateEditToDateTime(){
		String userInput = "edit 1 11feb 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
		
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
}
