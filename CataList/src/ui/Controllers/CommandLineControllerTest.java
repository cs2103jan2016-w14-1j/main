//@@author A0112204E
package ui.Controllers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Rule;
import org.junit.Test;

public class CommandLineControllerTest {

	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add event.";
	private static final String MESSAGE_ADD_EMPTY = "Unable to add empty message";
	
	private static final String MESSAGE_CLEAR_SUCCESS = "Your dashboard has been cleared.";
	private static final String MESSAGE_CLEAR_FAILURE = "Unable to clear dashboard.";
	private static final String MESSAGE_EMPTY_FAILURE = "Nothing to clear!";
	
	private static final String MESSAGE_DELETE_SUCCESS = "The event has been deleted.";
	private static final String MESSAGE_DELETE_FAILURE = "Unable to delete event.";
	private static final String MESSAGE_DELETE_EMPTY = "No event to be deleted.";	
	
	private static final String MESSAGE_EDIT_SUCCESS = "The event has been edited.";
	private static final String MESSAGE_EDIT_FAILURE = "Unable to edit event.";
	private static final String MESSAGE_EDIT_EMPTY = "No event to be editted.";
	
	private static final String MESSAGE_INVALID = "Invalid command";
	
	private MainGUIController main = new MainGUIController();
	
	static String expectedResults;
	static String input;
	
	@Test
	public void testAdd() {
		
		input = "add machine number 500";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 -t 1700 -d 23/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add add";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 -t 1700";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = " add delete 0";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "dont add";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
	}
	
	@Test
	public void testDelete() {
		
		input = "delete 1";
		expectedResults = MESSAGE_DELETE_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "delete all";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "dont delete";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	public void testClear() {
		input = "clear";
		expectedResults = MESSAGE_CLEAR_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "clear";
		expectedResults = MESSAGE_EMPTY_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "clear none";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
}
