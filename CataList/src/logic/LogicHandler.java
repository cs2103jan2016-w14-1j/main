package logic;

public class LogicHandler {
	private static final String PARSER_UNSUPPORTED_ERROR = "Command not recognized by Logic.";
	
	private static final int INPUT_COMMAND_INDEX = 0;
	private static final int INPUT_EVENT_INDEX = 1;
	private static final int INPUT_DATE_INDEX = 2;
	private static final int INPUT_TIME_INDEX = 3;
	
	private static final int EVENT_INDEX_NUMBER = 0;
	
	private static final int INPUT_LENGTH_NODATETIME = 2;  
	private static final int INPUT_LENGTH_WITH_DATE_NO_TIME = 3;  
	private static final int INPUT_LENGTH_WITH_DATE_TIME = 4;
	private static final String[] COMMANDS_REQUIRING_INDEX = {"delete"
															,"edit"
															,"markcomplete"
															,"markincomplete"};


	public static Task processCommand(String[] userInputArray){
		int numberOfFields = userInputArray.length;
		Task newTask;
		if(numberOfFields == INPUT_LENGTH_NODATETIME){
			newTask = createTaskNoDateTime(userInputArray);
		} else if ( numberOfFields == INPUT_LENGTH_WITH_DATE_NO_TIME){
			newTask = createTaskWithDateNoTime(userInputArray);
		} else if ( numberOfFields == INPUT_LENGTH_WITH_DATE_TIME){
			newTask = createTaskWithDateTime(userInputArray);
		} else {
			newTask = createTaskWithParserError();
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
			case "invalid" :
				return new InvalidTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithDateNoTime(String[] checkString){
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[INPUT_EVENT_INDEX];
		String userInputDate = checkString[INPUT_DATE_INDEX];
		
		switch(commandType){
			case "add":
				return new AddTask(userInputEvent, userInputDate);
			case "delete":
				return new DeleteTask(userInputEvent, userInputDate);
			case "clear" :
				return new ClearTask(userInputEvent, userInputDate);
			case "display" :
				return new DisplayTask(userInputEvent, userInputDate);
			case "edit" :
				return new EditTask(userInputEvent, userInputDate);
			case "redo" : 
				return new RedoTask(userInputEvent, userInputDate);
			case "undo" :
				return new UndoTask(userInputEvent, userInputDate);
			case "search" :
				return new SearchTask(userInputEvent);
			case "markcomplete" :
				return new MarkComplete(userInputEvent);
			case "markincomplete" :
				return new MarkIncomplete(userInputEvent);
			case "invalid":
				return new InvalidTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithDateTime(String[] checkString){
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[INPUT_EVENT_INDEX];
		String userInputDate = checkString[INPUT_DATE_INDEX];
		String userInputTime = checkString[INPUT_TIME_INDEX];
		
		switch(commandType){
			case "add":
				return new AddTask(userInputEvent, userInputDate, userInputTime);
			case "delete":
				return new DeleteTask(userInputEvent, userInputDate, userInputTime);
			case "clear" :
				return new ClearTask(userInputEvent, userInputDate, userInputTime);
			case "display" :
				return new DisplayTask(userInputEvent, userInputDate, userInputTime);
			case "edit" :
				return new EditTask(userInputEvent, userInputDate, userInputTime);
			case "redo" : 
				return new RedoTask(userInputEvent, userInputDate, userInputTime);
			case "undo" :
				return new UndoTask(userInputEvent, userInputDate, userInputTime);
			case "search" :
				return new SearchTask(userInputEvent);
			case "markcomplete" :
				return new MarkComplete(userInputEvent);
			case "markincomplete" :
				return new MarkIncomplete(userInputEvent);
			case "invalid":
				return new InvalidTask(userInputEvent);
			default: 
				return createTaskWithParserError();
		}
	}
	
	private static Task createTaskWithParserError(){
		Task parserErrorTask = new InvalidTask(PARSER_UNSUPPORTED_ERROR);
		return parserErrorTask;
	}
	
	
}
