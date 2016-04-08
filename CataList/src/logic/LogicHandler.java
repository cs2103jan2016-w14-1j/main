//@@author a0124946
package logic;

import java.util.ArrayList;

public class LogicHandler {
	
	/**
	 * 	LogicHandler
	 * 	The role of LogicHandler is to operate on a formatted String and create Task Objects accordingly.
	 *  It is called by LogicMain to create Task Objects, which will be returned to LogicMain
	 *  
	 */
	
	private static final String PARSER_UNSUPPORTED_ERROR = "Command not recognized by Logic.";
	private static final String SYMBOL_SPACE = " ";
	private static final String SYMBOL_EMPTY = "";
	
	private static final String[] COMMANDS_REQUIRING_INDEX = {"delete"
															,"display"
															,"edit"
															,"markcomplete"
															,"markincomplete"};
	
	private static final int INPUT_COMMAND_INDEX = 0;
	private static final int INPUT_EVENT_INDEX = 1;
	
	private static final int EVENT_INDEX_NUMBER = 0;
	
	private static final int LIST_DATE_INDEX = 0; 
	private static final int DATE_TIME_SIZE_EMPTY = 0;
	private static final int INPUT_INDEX_CORRECTION = 1;
	
	/**
	 * Public method for the creation of task objects.
	 * @param userInputArray formatted String array that contains all required parameters for Task object creation.
	 * @param dateTimeArgs formatted Date and Time arguments that are to be added to Task Object.
	 * @return Task Object
	 */
	public static Task processCommand(String[] userInputArray
				, ArrayList<ArrayList<String>>dateTimeArgs) {
		
		Task newTask;
		if(isDateTimeEmpty(dateTimeArgs)) {
			newTask = createTaskNoDateTime(userInputArray);
		} else {
			newTask = createTaskWithDateTime(userInputArray, dateTimeArgs);
		}
		updateTaskWithIndex(newTask);
		return newTask;
	}
	
	/**
	 *  Method checks whether a Task object requires an index,
	 *  and if so - calls registerIndex to find and register the index.
	 * @param Task object to be checked.
	 */
	private static void updateTaskWithIndex(Task indexTask) {
		String commandWord = indexTask.get_cmd();
		for(String eachCommand : COMMANDS_REQUIRING_INDEX) {
			if(commandWord.equalsIgnoreCase(eachCommand)) {
				registerIndex(indexTask);
				break;
			}
		}
	}
	
	/**
	 *  Method updates the task object with an Index, if required.
	 * @param Task object to be checked.
	 */
	private static void registerIndex(Task indexTask) {
		String eventPhrase = indexTask.get_task();
		String eventPhraseWithoutIndex = removeFirstWord(eventPhrase);
		indexTask.set_task(eventPhraseWithoutIndex);
		int indexOfTask = eventPhraseIndexParse(eventPhrase);
		indexTask.set_index(indexOfTask);
	}
	
	/**
	 * Method removes the first word of the input
	 * @param eventPhrase
	 * @return String of input with original first word removed.
	 */
	private static String removeFirstWord(String eventPhrase) {
		String[] eventArray = eventPhrase.split(SYMBOL_SPACE);
		String secondWordOnwards = SYMBOL_EMPTY;
		int lastWordIndex = eventArray.length - INPUT_INDEX_CORRECTION;
		for(int i = 1 ; i <= lastWordIndex ; i++) {
			secondWordOnwards += eventArray[i];
			if(i != lastWordIndex) {
				secondWordOnwards += SYMBOL_SPACE;
			}
		}
		return secondWordOnwards;
	}
	
	/**
	 * Method parses the index from userInput, and returns it.
	 * @param eventPhrase
	 * @return int Index
	 */
	private static int eventPhraseIndexParse(String eventPhrase) {
		String[] eventArray = eventPhrase.split(SYMBOL_SPACE);
		String indexWord = eventArray[EVENT_INDEX_NUMBER];
		int parsedIndex = Integer.parseInt(indexWord);
		return parsedIndex;
	}
	
	private static Task createTaskNoDateTime(String[] checkString) {
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[INPUT_EVENT_INDEX].trim();
		
		switch(commandType) {
			case Commands.ADD_COMMAND :
				return new AddTask(userInputEvent);
			case Commands.DELETE_COMMAND :
				return new DeleteTask(userInputEvent);
			case Commands.CLEAR_COMMAND :
				return new ClearTask(userInputEvent);
			case Commands.DISPLAY_COMMAND :
				return new DisplayTask(userInputEvent);
			case Commands.EDIT_COMMAND :
				return new EditTask(userInputEvent);
			case Commands.REDO_COMMAND : 
				return new RedoTask(userInputEvent);
			case Commands.UNDO_COMMAND :
				return new UndoTask(userInputEvent);
			case Commands.SEARCH_COMMAND :
				return new SearchTask(userInputEvent);
			case Commands.MARK_COMMAND :
				return new MarkComplete(userInputEvent);
			case Commands.MARK_INCOMPLETE_COMMAND :
				return new MarkIncomplete(userInputEvent);
			case Commands.HELP_COMMAND :
				return new HelpTask(userInputEvent);
			case Commands.TUTORIAL_COMMAND :
				return new TutorialTask(userInputEvent);
			case Commands.CALENDAR_COMMAND  :
				return new CalendarTask(userInputEvent);
			case Commands.EXIT_COMMAND :
				return new ExitTask(userInputEvent);
			case Commands.INVALID_COMMAND :
				return new InvalidTask(userInputEvent);
			case Commands.SAVE_COMMAND :
				return new SaveTask(userInputEvent);
			case Commands.SAVETO_COMMAND:
				return new SaveToTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithDateTime(String[] checkString
			, ArrayList<ArrayList<String>> dateTimeArgs) {
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[INPUT_EVENT_INDEX];
		
		switch(commandType) {
			case Commands.ADD_COMMAND :
				return new AddTask(userInputEvent, dateTimeArgs);
			case Commands.DELETE_COMMAND :
				return new DeleteTask(userInputEvent, dateTimeArgs);
			case Commands.CLEAR_COMMAND :
				return new ClearTask(userInputEvent, dateTimeArgs);
			case Commands.DISPLAY_COMMAND :
				return new DisplayTask(userInputEvent, dateTimeArgs);
			case Commands.EDIT_COMMAND :
				return new EditTask(userInputEvent, dateTimeArgs);
			case Commands.REDO_COMMAND : 
				return new RedoTask(userInputEvent, dateTimeArgs);
			case Commands.UNDO_COMMAND :
				return new UndoTask(userInputEvent, dateTimeArgs);
			case Commands.SEARCH_COMMAND :
				return new SearchTask(userInputEvent);
			case Commands.MARK_COMMAND :
				return new MarkComplete(userInputEvent);
			case Commands.MARK_INCOMPLETE_COMMAND :
				return new MarkIncomplete(userInputEvent);
			case Commands.HELP_COMMAND :
				return new HelpTask(userInputEvent);
			case Commands.TUTORIAL_COMMAND :
				return new TutorialTask(userInputEvent);
			case Commands.CALENDAR_COMMAND  :
				return new CalendarTask(userInputEvent);
			case Commands.EXIT_COMMAND :
				return new ExitTask(userInputEvent);
			case Commands.INVALID_COMMAND :
				return new InvalidTask(userInputEvent);
			case Commands.SAVE_COMMAND :
				return new SaveTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	/**
	 * Method used to create a Task when command is unsupported by Logic components
	 * @return Task which indicates parser error
	 */
	private static Task createTaskWithParserError() {
		Task parserErrorTask = new InvalidTask(PARSER_UNSUPPORTED_ERROR);
		return parserErrorTask;
	}
	
	private static boolean isDateTimeEmpty(ArrayList<ArrayList<String>> dateTimeArgs) {
		ArrayList<String> dateArgs = dateTimeArgs.get(LIST_DATE_INDEX);
		if(dateArgs.size() == DATE_TIME_SIZE_EMPTY) {
			return true;
		} else { 
			return false;
		}
	}
		
	
	
}