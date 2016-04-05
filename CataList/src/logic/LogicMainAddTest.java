package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicMainAddTest {
	LogicMain logicTest;
	
	@Before
	public void init(){
		logicTest = new LogicMain();
		String userInput6 = "clear";
		String testOutput = logicTest.processCommand(userInput6);

	}
	
	@Test
	public void testAdding(){
		String userInput = "add Fire James 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
	}
	
	@Test
	public void testAddingWithStartDate(){
		String userInput = "add Fire James 2GD 10feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		assertEquals(startDateResult, startDateTest);
	}
	
	@Test
	public void testAddingWithStartAndEndDate(){
		String userInput = "add Fire James 2GD 10feb 11feb";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String endDateResult = resultOutput.get_endDate();
		String endDateTest = "11/02/16";
		assertEquals(startDateResult, startDateTest);
		assertEquals(endDateResult, endDateTest);
	}
	
	@Test
	public void testAddingWithStartDateAndStartTime(){
		String userInput = "add Fire James 2GD 10feb 1pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
	}
	
	@Test
	public void testAddingWithStartAndEnd(){
		String userInput = "add Fire James 2GD 10feb 1pm 11feb 2pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		String endDateResult = resultOutput.get_endDate();
		String endDateTest = "11/02/16";
		String endTimeResult = resultOutput.get_endTime();
		String endTimeTest = "2.00PM";
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
		assertEquals(endDateResult, endDateTest);
		assertEquals(endTimeResult, endTimeTest);
	}
	
	@Test
	public void testAddingWithStartDateAndStartTimeJumbled(){
		String userInput = "add Fire 10feb James 1pm 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
	}
	
	@Test
	public void testAddingWithStartDateAndStartTimeJumbledReverse(){
		String userInput = "add Fire 1pm James 10feb 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
	}
	
	@Test
	public void testAddingWithStartDateAndEndJumbled(){
		String userInput = "add Fire 10feb James 11FEB 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String endDateResult = resultOutput.get_endDate();
		String endDateTest = "11/02/16";
		assertEquals(startDateResult, startDateTest);
		assertEquals(endDateResult, endDateTest);
	}
	
	@Test
	public void testAddingWithStartDateAndEndTimeJumbled(){
		String userInput = "add Fire 10feb 1pm James 11FEB 2pm 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String endDateResult = resultOutput.get_endDate();
		String endDateTest = "11/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		String endTimeResult = resultOutput.get_endTime();
		String endTimeTest = "2.00PM";
		
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
		assertEquals(endTimeResult, endTimeTest);
		assertEquals(endDateResult, endDateTest);
	}
	
	@Test
	public void testAddingWithStartDateAndEndJumbled2(){
		String userInput = "add Fire 10feb James 1pm 11FEB 2GD 2pm";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
		
		String startDateResult = resultOutput.get_startDate();
		String startDateTest = "10/02/16";
		String endDateResult = resultOutput.get_endDate();
		String endDateTest = "11/02/16";
		String startTimeResult = resultOutput.get_startTime();
		String startTimeTest = "1.00PM";
		String endTimeResult = resultOutput.get_endTime();
		String endTimeTest = "2.00PM";
		
		assertEquals(startDateResult, startDateTest);
		assertEquals(startTimeResult, startTimeTest);
		assertEquals(endTimeResult, endTimeTest);
		assertEquals(endDateResult, endDateTest);
	}
	
	@Test
	public void testAddWithNumbersOnly(){
		String userInput = "add 123456789";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "123456789";
		assertEquals(testEventResult, expectedEventResult);
	}
	
	@Test
	public void testAddingWithWhitespaceBefore(){
		String userInput = " add Fire James 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Invalid command";
		
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void testAddingWithWhitespaceAfter(){
		String userInput = "add  Fire James 2GD";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		System.out.println(testResult);
		assertEquals(testResult, expectedResult);
		
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		Task resultOutput = testList.get(0);
		String testEventResult = resultOutput.get_task();
		String expectedEventResult = "Fire James 2GD";
		assertEquals(testEventResult, expectedEventResult);
	}
	
	@Test
	public void testAddingWithNothing(){
		String userInput = "add";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Invalid command";
		
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void testAddWithWhitespacesOnly(){
		String userInput = "add                  ";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Invalid command";
		
		assertEquals(testResult, expectedResult);	
	}

}
