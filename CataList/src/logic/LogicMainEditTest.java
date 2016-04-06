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
	}
	
	@Test
	public void testSimpleEdit(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

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
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "edit 1 10feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase1";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "10/02/16";
		assertEquals(testEventDate, expectedEventDate);
	}
	
	@Test
	public void testSimpleEditToTime(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "edit 1 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase1";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	//TODO: BUGGED
	@Test
	public void testSimpleEditToDateTime(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "edit 1 10feb 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase1";
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
		String userInputAddToEditDate = "add editThisCase2 10feb";
		logicTest.processCommand(userInputAddToEditDate);

		String userInput = "edit 1 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
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
		String userInputAddToEditDate = "add editThisCase2 10feb";
		logicTest.processCommand(userInputAddToEditDate);

		String userInput = "edit 1 11feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase2";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
	}
	
	@Test
	public void testDateEditToTime(){
		String userInputAddToEditDate = "add editThisCase2 10feb";
		logicTest.processCommand(userInputAddToEditDate);

		String userInput = "edit 1 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase2";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testDateEditToDateTime(){
		String userInputAddToEditDate = "add editThisCase2 10feb";
		logicTest.processCommand(userInputAddToEditDate);

		String userInput = "edit 1 11feb 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase2";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "1.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}

	@Test
	public void testDateTimeEdit(){
		String userInputAddToEditDateTime = "add editThisCase3 10feb 1pm";
		logicTest.processCommand(userInputAddToEditDateTime);
		
		String userInput = "edit 1 11feb 2pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase3";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "2.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testDateTimeEditJumbled(){
		String userInputAddToEditDateTime = "add editThisCase3 10feb 1pm";
		logicTest.processCommand(userInputAddToEditDateTime);
		
		String userInput = "edit 1 2pm 11feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase3";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "2.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testDateTimeEditJumbled2(){
		String userInputAddToEditDateTime = "add editThisCase3 1pm 10feb";
		logicTest.processCommand(userInputAddToEditDateTime);
		
		String userInput = "edit 1 2pm 11feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase3";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventTime = resultOutput.get_startTime();
		String expectedEventTime = "2.00PM";
		assertEquals(testEventTime, expectedEventTime);
	}
	
	@Test
	public void testStartEndDate(){
		String userInputAddToEditStartEndDates = "add editThisCase4 10feb 11feb";
		logicTest.processCommand(userInputAddToEditStartEndDates);

		String userInput = "edit 1 11feb 12feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase4";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventEnd = resultOutput.get_endDate();
		String expectedEventEnd = "12/02/16";
		assertEquals(testEventEnd, expectedEventEnd);
	}
	
	@Test
	public void testStartEndDateTime(){
		String userInputAddToEditStartEndDates = "add editThisCase4 10feb 11feb";
		logicTest.processCommand(userInputAddToEditStartEndDates);

		String userInput = "edit 1 11feb 1pm 12feb 2pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase4";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventEnd = resultOutput.get_endDate();
		String expectedEventEnd = "12/02/16";
		assertEquals(testEventEnd, expectedEventEnd);
		
		String testEventStartTime = resultOutput.get_startTime();
		String expectedEventStartTime = "1.00PM";
		assertEquals(testEventStartTime, expectedEventStartTime);

		String testEventEndTime = resultOutput.get_endTime();
		String expectedEventEndTime = "2.00PM";
		assertEquals(testEventEndTime, expectedEventEndTime);
	}
	
	@Test
	public void testEditStartEndDateTime(){
		String userInputAddToEditStartEndDateTime = "add editThisCase5 10feb 1pm 11feb 2pm";
		logicTest.processCommand(userInputAddToEditStartEndDateTime);

		String userInput = "edit 1 11feb 2pm 12feb 3pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase5";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventEnd = resultOutput.get_endDate();
		String expectedEventEnd = "12/02/16";
		assertEquals(testEventEnd, expectedEventEnd);
		
		String testEventStartTime = resultOutput.get_startTime();
		String expectedEventStartTime = "2.00PM";
		assertEquals(testEventStartTime, expectedEventStartTime);

		String testEventEndTime = resultOutput.get_endTime();
		String expectedEventEndTime = "3.00PM";
		assertEquals(testEventEndTime, expectedEventEndTime);
	}

	@Test
	public void testEditStartEndDateOnly(){
		String userInputAddToEditStartEndDateTime = "add editThisCase5 10feb 1pm 11feb 2pm";
		logicTest.processCommand(userInputAddToEditStartEndDateTime);

		String userInput = "edit 1 11feb 12feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "editThisCase5";
		assertEquals(testEventResult, expectedEventResult);
		
		String testEventDate = resultOutput.get_startDate();
		String expectedEventDate = "11/02/16";
		assertEquals(testEventDate, expectedEventDate);
				
		String testEventEnd = resultOutput.get_endDate();
		String expectedEventEnd = "12/02/16";
		assertEquals(testEventEnd, expectedEventEnd);
		
		String testEventStartTime = resultOutput.get_startTime();
		String expectedEventStartTime = "1.00PM";
		assertEquals(testEventStartTime, expectedEventStartTime);

		String testEventEndTime = resultOutput.get_endTime();
		String expectedEventEndTime = "2.00PM";
		assertEquals(testEventEndTime, expectedEventEndTime);
	}
	
	@Test
	public void editOutOfBounds(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "edit 2 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Unable to edit event.";
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void editWithWhitespaceFront(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = " edit 1 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Invalid command";
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void editWithWhitespaceAfter(){
		String userInputAddToEditNoDateTime = "add editThisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "edit       1 editThatCase";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been edited.";
		assertEquals(testResult, expectedResult);	
	}
}
