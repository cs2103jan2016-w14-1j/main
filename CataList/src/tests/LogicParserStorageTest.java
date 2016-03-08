package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Test;

import logic.LogicHandler;

public class LogicParserStorageTest {

	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	
	String userInput;
	String expectedResult;
	
	@Test
	public void test() throws IOException, JDOMException {
		userInput = "add integrate this piece of shit";
		expectedResult = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResult, LogicHandler.processCommand(userInput));
	}

}
