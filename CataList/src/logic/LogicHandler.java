package logic;

import java.util.Stack;
import parser.Parser;



public class LogicHandler {
	private static final int INPUT_COMMAND_INDEX = 0;
	private static final int INPUT_EVENT_INDEX = 1;
	
	private static final int INPUT_LENGTH_NODATETIME = 1;  
	private static final int INPUT_LENGTH_NO_TIME_WITH_DATE = 2;  
	private static final int INPUT_LENGTH_WITH_DATE_TIME = 3;
	
	public void processCommand(String userInput){
	//TODO: parser
		Parser commandParser = new Parser();
		String[] formattedString = commandParser(userInput);
		createTask(formattedString);
	}
	
	private Task createTask(String[] userInput){
		int numberOfFields = userInput.length;
		if(numberOfFields == 1){
			createTaskNoDateTime(userInput);
		} else if ( numberOfFields == 2){
			
		} else if ( numberOfFields == 3){
			
		} else {
			return;
		}
	}
	
	
	private Task createTaskNoDateTime(String[] checkString){
		String commandType = checkString[INPUT_COMMAND_INDEX];
		String userInputEvent = checkString[1];
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
			case "invalid":
				return new InvalidTask(userInputEvent);
		}
	}
	
}
