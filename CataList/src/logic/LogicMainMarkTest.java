//@@author a0124946

package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicMainMarkTest {
	LogicMain logicTest;
	
	@Before
	public void init(){
		logicTest = new LogicMain();
		String userInput6 = "clear";
		String testOutput = logicTest.processCommand(userInput6);
	}
	
	@Test
	public void testSimpleMark(){
		String userInputAdd = "add markhisCase1";
		logicTest.processCommand(userInputAdd);

		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		System.out.println("Size= " +  testList.size());
		
		String userInput = "mark 1";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Event marked completed.";
		assertEquals(testResult, expectedResult);
		
		Task resultOutput = testList.get(0);
		boolean testEventResult = resultOutput.get_completionState();
		boolean expectedEventResult = true;
		assertEquals(testEventResult, expectedEventResult);
	}
	
	@Test
	public void testSimpleUnMark(){
		String userInputAdd = "add markhisCase1";
		logicTest.processCommand(userInputAdd);

		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		System.out.println("Size= " +  testList.size());
		
		String userInput = "mark 1";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Event marked completed.";
		assertEquals(testResult, expectedResult);
		
		Task resultOutput = testList.get(0);
		boolean testEventResult = resultOutput.get_completionState();
		boolean expectedEventResult = true;
		assertEquals(testEventResult, expectedEventResult);
		
		String userInputDisplay = "display 1";
		logicTest.processCommand(userInputDisplay);
		
		String userInput2 = "unmark 1";
		String testResult2 = logicTest.processCommand(userInput2);
		String expectedResult2 = "Event marked as incomplete.";
		assertEquals(testResult2, expectedResult2);
		
		String userInputDisplay2 = "display 2";
		logicTest.processCommand(userInputDisplay2);
		testList = logicTest.getOperatingTasksForUI();
		Task resultOutput2 = testList.get(0);
		boolean testEventResult2 = resultOutput2.get_completionState();
		boolean expectedEventResult2 = false;
		assertEquals(testEventResult2, expectedEventResult2);
	}
	
	@Test
	public void testSimpleMarkWhitespaceBefore(){
		String userInputAddToEditNoDateTime = "add markhisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "     mark 1";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Invalid command";
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void testSimpleMarkOutOfBounds(){
		String userInputAddToEditNoDateTime = "add markhisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "mark 3";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Unable to mark task as complete.";
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void testSimpleUnmarkOutOfBounds(){
		String userInputAddToEditNoDateTime = "add markhisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		String userInput = "unmark 3";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Unable to mark event as incomplete.";
		assertEquals(testResult, expectedResult);	
	}
	
	@Test
	public void testSimpleMarkWhitespaceAfter(){
		String userInputAddToEditNoDateTime = "add markhisCase1";
		logicTest.processCommand(userInputAddToEditNoDateTime);

		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		System.out.println("Size= " +  testList.size());
		
		String userInput = "mark     1";
		String testResult = logicTest.processCommand(userInput);
		String expectedResult = "Event marked completed.";
		assertEquals(testResult, expectedResult);
		
		Task resultOutput = testList.get(0);
		boolean testEventResult = resultOutput.get_completionState();
		boolean expectedEventResult = true;
		assertEquals(testEventResult, expectedEventResult);
	}

}
