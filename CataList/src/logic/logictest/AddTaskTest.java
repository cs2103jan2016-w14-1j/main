package logic.logictest;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import logic.AddTask;
import logic.LogicMain;

import java.util.ArrayList;

public class AddTaskTest {
	LogicMain testLogic;
	
	@Before
	public void init(){
		 testLogic = new LogicMain();
	}
	
	@Test
	public void AddTaskNoDateTime(){
		String userInput = "add Buy birthday present";
		String receivedReply = testLogic.processCommand(userInput);
		String expectedReply = AddTask.getSuccessMessage();
		assertSame(receivedReply, expectedReply);
	}
	
}
