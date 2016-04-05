package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicMainDeleteTest {
	LogicMain logicTest;
	
	@Before
	public void init(){
		logicTest = new LogicMain();
		String userInput6 = "clear";
		String testOutput = logicTest.processCommand(userInput6);
	}
	
	@Test
	public void testDeleting(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = "delete 1";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "The event has been deleted.";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 0);
			
	}
	
	@Test
	public void testDeletingWithWhiteSpaceBefore(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = " delete 1";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "Invalid command";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 1);
	}
	
	@Test
	public void testDeletingWithWhitespaceAfter(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = "delete      1";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "The event has been deleted.";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 0);
	}
	
	@Test
	public void testDeletingWithIndexOutOfBounds(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = "delete 2";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "Unable to delete event.";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 1);
	}
	
	@Test
	public void testDeletingWithNotNumber(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = "delete two";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "Invalid command";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 1);
	}
	
	@Test
	public void testDeletingWithNotSymbols(){
		String userInput = "add Shanghai Major stream delay pls";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "The event has been added.";
		assertEquals(testResult, expectedResult);
		
		String userInput2 = "delete 1!";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "Invalid command";
		assertEquals(testResult2, expectedResult2);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 1);
	}
	
}
