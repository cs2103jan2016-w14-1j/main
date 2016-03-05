package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.LogicHandler;

public class LogicHandlerTest {

	private final String ADD = "add";
	private final String DELETE = "delete";
	private final String EDIT = "edit";
	private final String CLEAR = "add";
	private final String DISPLAY = "add";
	private final String REDO = "add";
	private final String UNDO = "add";
	
	private final String TEST_CASE_1 = " pang gang by 6pm";
	private final String TEST_CASE_2 = " hackathon compt from 10 March to 11 March at 11pm";
	private final String TEST_CASE_3 = " wasd123";
	
	private String expectedResults;
	private String userInput;
	
	@Test
	public void testAdd() {
		userInput = ADD + TEST_CASE_1;
		expectedResults = ADD;
		assertEquals(expectedResults, LogicHandler.processCommand(userInput));
	}
	
	@Test
	public void testDelete() {
		userInput = DELETE + TEST_CASE_2;
		expectedResults = DELETE;
		assertEquals(expectedResults, LogicHandler.processCommand(userInput));
	}
	
	@Test
	public void testEdit() {
		userInput = EDIT + TEST_CASE_1;
		expectedResults = EDIT;
		assertEquals(expectedResults, LogicHandler.processCommand(userInput));
	}
}
