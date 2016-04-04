package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LogicMainTest {
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
		System.out.println(resultOutput.get_task());
		assertEquals(testEventResult, expectedEventResult);
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
	public void testClearing(){
		String userInput1 = "add machine gun noise during livestream";
		String userInput2 = "add VIP room with only plastic chairs";
		String userInput3 = "add 60hz samsung monitors instead of 144hz";
		String userInput4 = "add samsung stickers onto valve's 144hz benq monitors";
		String userInput5 = "add glue and cardboard to construct soundproof rooms";
	
		logicTest.processCommand(userInput1);
		logicTest.processCommand(userInput2);
		logicTest.processCommand(userInput3);
		logicTest.processCommand(userInput4);
		logicTest.processCommand(userInput5);
		ArrayList<Task> testList = logicTest.getOperatingTasksForUI();
		assertEquals(testList.size(), 5);
		
		String userInput6 = "clear";
		String testOutput = logicTest.processCommand(userInput6);
		String expectedOutput = "Your dashboard has been cleared.";
		System.out.println(testList.size());
		assertEquals(testOutput, expectedOutput);
		assertEquals(testList.size(), 0);
	}

}
