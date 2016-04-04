package logic;

import java.util.ArrayList;

public class LogicHandler {
	private static final String PARSER_UNSUPPORTED_ERROR = "Command not recognized by Logic.";
	
	private static final int INPUT_COMMAND_INDEX = 0;
	private static final int INPUT_EVENT_INDEX = 1;
	
	private static final int EVENT_INDEX_NUMBER = 0;
	
	private static final int LIST_DATE_INDEX = 0; 
	private static final int DATE_TIME_SIZE_EMPTY = 0;
	
	private static final String[] COMMANDS_REQUIRING_INDEX = {"delete"
															,"edit"
															,"markcomplete"
															,"markincomplete"};


	public static Task processCommand(String[] userInputArray
				, ArrayList<ArrayList<String>>dateTimeArgs){
		Task newTask;
		if(isDateTimeEmpty(dateTimeArgs)) {
			newTask = createTaskNoDateTime(userInputArray);
		} else {
			newTask = createTaskWithDateTime(userInputArray, dateTimeArgs);
		}
		updateTaskWithIndex(newTask);
		return newTask;
	}
	
	private static void updateTaskWithIndex(Task indexTask){
		String commandWord = indexTask.get_cmd();
		for(String eachCommand : COMMANDS_REQUIRING_INDEX){
			if(commandWord.equalsIgnoreCase(eachCommand)){
				registerIndex(indexTask);
				break;
			}
		}
	}
	
	private static void registerIndex(Task indexTask){
		String eventPhrase = indexTask.get_task();
		String eventPhraseWithoutIndex = removeFirstWord(eventPhrase);
		indexTask.set_task(eventPhraseWithoutIndex);
		int indexOfTask = eventPhraseIndexParse(eventPhrase);
		indexTask.set_index(indexOfTask);
	}
		
	private static String removeFirstWord(String eventPhrase){
		String[] eventArray = eventPhrase.split(" ");
		String secondWordOnwards = "";
		int lastWordIndex = eventArray.length-1;
		for(int i = 1 ; i <= lastWordIndex ; i++){
			secondWordOnwards += eventArray[i];
			if(i != lastWordIndex){
				secondWordOnwards += " ";
			}
		}
		return secondWordOnwards;
	}
	
	private static int eventPhraseIndexParse(String eventPhrase){
		String[] eventArray = eventPhrase.split(" ");
		String indexWord = eventArray[EVENT_INDEX_NUMBER];
		int parsedIndex = Integer.parseInt(indexWord);
		return parsedIndex;
	}
	
	private static Task createTaskNoDateTime(String[] checkString){
		String commandType = checkString[INPUT_COMMAND_INDEX];
		
		// added trim for testing purposes, remove later
		String userInputEvent = checkString[INPUT_EVENT_INDEX].trim();
		
		switch(commandType){
			case "add":
				return new AddTask(userInputEvent);
			case "delete":
				return new DeleteTask(userInputEvent);
			case "clear" :
				return new ClearTask(userInputEvent);
			case "display" :
				return new DisplayTask(userInputEvent);
			case "edit" :
				return new EditTask(userInputEvent);
			case "redo" : 
				return new RedoTask(userInputEvent);
			case "undo" :
				return new UndoTask(userInputEvent);
			case "search" :
				return new SearchTask(userInputEvent);
			case "markcomplete" :
				return new MarkComplete(userInputEvent);
			case "markincomplete" :
				return new MarkIncomplete(userInputEvent);
			case "help" :
				return new HelpTask(userInputEvent);
			case "tutorial" :
				return new TutorialTask(userInputEvent);
			case "calendar" :
				return new CalendarTask(userInputEvent);
			case "exit" :
				return new ExitTask(userInputEvent);
			case "invalid" :
				return new InvalidTask(userInputEvent);
			
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithDateTime(String[] checkString
			, ArrayList<ArrayList<String>> dateTimeArgs){
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[INPUT_EVENT_INDEX];
		
		switch(commandType){
			case "add":
				return new AddTask(userInputEvent, dateTimeArgs);
			case "delete":
				return new DeleteTask(userInputEvent, dateTimeArgs);
			case "clear" :
				return new ClearTask(userInputEvent, dateTimeArgs);
			case "display" :
				return new DisplayTask(userInputEvent, dateTimeArgs);
			case "edit" :
				return new EditTask(userInputEvent, dateTimeArgs);
			case "redo" : 
				return new RedoTask(userInputEvent, dateTimeArgs);
			case "undo" :
				return new UndoTask(userInputEvent, dateTimeArgs);
			case "search" :
				return new SearchTask(userInputEvent);
			case "markcomplete" :
				return new MarkComplete(userInputEvent);
			case "markincomplete" :
				return new MarkIncomplete(userInputEvent);
			case "invalid":
				return new InvalidTask(userInputEvent);
			case "help":
				return new HelpTask(userInputEvent);
			case "tutorial" :
				return new TutorialTask(userInputEvent);
			case "calendar" :
				return new CalendarTask(userInputEvent);
			case "exit" :
				return new ExitTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithParserError(){
		Task parserErrorTask = new InvalidTask(PARSER_UNSUPPORTED_ERROR);
		return parserErrorTask;
	}
	
	private static boolean isDateTimeEmpty(ArrayList<ArrayList<String>> dateTimeArgs){
		ArrayList<String> dateArgs = dateTimeArgs.get(LIST_DATE_INDEX);
		if(dateArgs.size() == DATE_TIME_SIZE_EMPTY) {
			return true;
		} else { 
			return false;
		}
	}
		
	
	
}