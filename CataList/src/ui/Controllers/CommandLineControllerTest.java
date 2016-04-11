//@@author A0112204E
package ui.Controllers;

import static org.junit.Assert.*;
import org.junit.Test;

public class CommandLineControllerTest {

	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	
	private static final String MESSAGE_CLEAR_SUCCESS = "Your dashboard has been cleared.";
	
	private static final String MESSAGE_DELETE_SUCCESS = "The event has been deleted.";
	
	private static final String MESSAGE_DISPLAY_SUCCESS = "Displaying Events";
	private static final String MESSAGE_DISPLAY_FAILURE = "Unable to display.";
	
	private static final String MESSAGE_MARK_COMPLETE_SUCCESS = "Event marked completed.";
	private static final String MESSAGE_MARK_COMPLETE_FAILURE = "Unable to mark task as complete.";
	
	private static final String MESSAGE_MARK_INCOMPLETE_SUCCESS = "Event marked as incomplete.";
	private static final String MESSAGE_MARK_INCOMPLETE_FAILURE = "Unable to mark event as incomplete.";
	
	private static final String MESSAGE_SEARCH_SUCCESS = "Your search query has returned:";
	
	private static final String MESSAGE_EDIT_SUCCESS = "The event has been edited.";
	private static final String MESSAGE_EDIT_FAILURE = "Unable to edit event.";
	
	private static final String MESSAGE_TUTORIAL_DEFAULT = "Displaying Tutorial";
	private static final String MESSAGE_CALENDAR_DEFAULT = "Displaying Calendar";
	private static final String MESSAGE_HELP_SUCCESS = "Displaying Help";
	
	private static final String MESSAGE_INVALID = "Invalid command";
	
	private MainGUIController main = new MainGUIController();
	
	static String expectedResults;
	static String input;
	
	public void testClear() {
		input = "clear";
		expectedResults = MESSAGE_CLEAR_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "clear none";
		expectedResults = MESSAGE_CLEAR_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testAdd() {
		
		input = "add machine number 500";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 1700  23/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 2500  23/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 -0100  23/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 0200  32/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 0200  -2/04/2016";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add add";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "add cs2103 -t 1700";
		expectedResults = MESSAGE_ADD_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = " add delete 0";
		expectedResults = MESSAGE_INVALID;
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
	
	@Test
	public void testEdit() {
		input = "edit 1 hello";
		expectedResults = MESSAGE_EDIT_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "edit -1 bye";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "edit 1000 awesome";
		expectedResults = MESSAGE_EDIT_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testSearch() {
		input = "search a";
		expectedResults = MESSAGE_SEARCH_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "search &*)*";
		expectedResults = MESSAGE_SEARCH_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testMark() {
		input = "mark 2";
		expectedResults = MESSAGE_MARK_COMPLETE_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "mark -1";
		expectedResults = MESSAGE_INVALID;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "mark 10000";
		expectedResults = MESSAGE_MARK_COMPLETE_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testUnmark() {
		input = "unmark -1";
		expectedResults = MESSAGE_MARK_INCOMPLETE_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "unmark 1";
		expectedResults = MESSAGE_MARK_INCOMPLETE_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "unmark 1000";
		expectedResults = MESSAGE_MARK_INCOMPLETE_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testDisplay() {
		input = "display 0";
		expectedResults = MESSAGE_DISPLAY_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "display 1";
		expectedResults = MESSAGE_DISPLAY_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "display 2";
		expectedResults = MESSAGE_DISPLAY_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "display 3";
		expectedResults = MESSAGE_DISPLAY_FAILURE;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
	}
	
	@Test
	public void testTutorial() {
		input = "tutorial 0";
		expectedResults = MESSAGE_TUTORIAL_DEFAULT;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "tutorial";
		expectedResults = MESSAGE_TUTORIAL_DEFAULT;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testCalendar() {
		input = "calendar 0";
		expectedResults = MESSAGE_CALENDAR_DEFAULT;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "calendar";
		expectedResults = MESSAGE_CALENDAR_DEFAULT;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
	
	@Test
	public void testHelp() {
		input = "help 0";
		expectedResults = MESSAGE_HELP_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
		
		input = "help";
		expectedResults = MESSAGE_HELP_SUCCESS;
		assertEquals(expectedResults, main.passInputToLogic(input));
	}
}
