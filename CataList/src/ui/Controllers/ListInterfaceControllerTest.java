package ui.Controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import logic.Task;

public class ListInterfaceControllerTest {

	private MainGUIController main = new MainGUIController();

	public static final String ADD_COMMAND = "add";
	public static final String DELETE_COMMAND = "delete";
	public static final String CLEAR_COMMAND = "clear";
	public static final String DISPLAY_COMMAND = "display";
	public static final String EDIT_COMMAND = "edit";
	public static final String REDO_COMMAND = "redo";
	public static final String UNDO_COMMAND = "undo";
	public static final String MARK_COMMAND = "markcomplete";
	public static final String MARK_INCOMPLETE_COMMAND = "markincomplete";
	public static final String HELP_COMMAND = "help";
	public static final String TUTORIAL_COMMAND = "tutorial";
	public static final String CALENDAR_COMMAND = "calendar";
	public static final String EXIT_COMMAND = "exit";
	public static final String INVALID_COMMAND = "invalid";
	public static final String SEARCH_COMMAND = "search";
	public static final String SAVE_COMMAND = "save";
	public static final String SAVEAS_COMMAND = "saveas";

	static int expectedResults;
	static String input;

	ArrayList<Task> operatingTasksFromLogic = new ArrayList<Task>();
	ArrayList<Task> completedTasksFromLogic = new ArrayList<Task>();

	@Test
	public void testClear() {
		input = CLEAR_COMMAND;
		expectedResults = 0;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
	}

	@Test
	public void testAdd() {
		main.passInputToLogic(CLEAR_COMMAND);
		input = "add watch Transformer 3";
		expectedResults = 1;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "add watch Transformer 3 8pm";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "add watch Transformer 3 8pm 10pm";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "add watch Transformer 3 8pm 10pm 25/6 25/7";
		expectedResults = 4;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "add watch Transformer 3 8.0002pm";
		expectedResults = 5;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		assertEquals("watch Transformer 3 8.0002pm", operatingTasksFromLogic.get(4).get_task());
	}
	
	@Test
	public void testDelete() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add water sunflower");
		main.passInputToLogic("add water moonflower");
		main.passInputToLogic("add water earthflower");
		
		input = "delete 5";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "delete 3";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "delete -3";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
	}
	
	@Test
	public void testEdit() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add water sunflower");
		main.passInputToLogic("add water moonflower");
		main.passInputToLogic("add water earthflower");
		
		input = "edit -5";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "edit 10000";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "edit 1 8pm";
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals("8.00PM", operatingTasksFromLogic.get(0).get_startTime());
	}
	
	@Test
	public void testUndo() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add watch Captain America");
		main.passInputToLogic("add watch Captain Africa");
		main.passInputToLogic("add watch Captain Singapore");
		main.passInputToLogic("delete 3");
		
		input = "undo";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "undo";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
	}
	
	@Test
	public void testRedo() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add watch Captain America");
		main.passInputToLogic("add watch Captain Africa");
		main.passInputToLogic("add watch Captain Singapore");
		main.passInputToLogic("delete 3");
		
		input = "undo";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "redo";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "redo";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
	}
	
	@Test
	public void testSearch() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add eat ayam panggang");
		main.passInputToLogic("add go pangsai");
		main.passInputToLogic("add pangkang");
		
		input = "search pang";
		expectedResults = 3;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "search pray";
		expectedResults = 0;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		
		input = "search eat";
		expectedResults = 1;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
	}
	
	@Test
	public void testMarkCompleted() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add go for CS2103T class");
		main.passInputToLogic("add go for CS2101 class");
		main.passInputToLogic("add go for ZoukOut");
		
		input = "mark 1";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		assertEquals(1, completedTasksFromLogic.size());
		
		input = "mark 1";
		expectedResults = 1;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		assertEquals(2, completedTasksFromLogic.size());
	}
	
	@Test
	public void testMarkIncomplete() {
		main.passInputToLogic(CLEAR_COMMAND);
		main.passInputToLogic("add go for CS2103T class");
		main.passInputToLogic("add go for CS2101 class");
		main.passInputToLogic("add go for ZoukOut");
		main.passInputToLogic("mark 1");
		main.passInputToLogic("mark 1");
		main.passInputToLogic("mark 1");
		
		main.setOperatingListAsComplete();
		input = "unmark 1";
		expectedResults = 1;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		assertEquals(2, completedTasksFromLogic.size());
		
		main.setOperatingListAsComplete();
		input = "unmark 1";
		expectedResults = 2;
		main.passInputToLogic(input);
		operatingTasksFromLogic = main.getIncompleteTasksFromLogic();
		completedTasksFromLogic = main.getCompletedTasksFromLogic();
		assertEquals(expectedResults, operatingTasksFromLogic.size());
		assertEquals(1, completedTasksFromLogic.size());
	}
}