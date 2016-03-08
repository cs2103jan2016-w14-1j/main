package logic;

import storage.Storage;

import java.io.IOException;

import org.jdom2.JDOMException;

import parser.CommandParser;
import parser.EventParser;

public class LogicHandler {
	private static final String PARSER_UNSUPPORTED_ERROR = "Command not recognized by Logic.";
	
	private static final int INPUT_COMMAND_INDEX = 0;
	private static final int INPUT_EVENT_INDEX = 1;
	private static final int INPUT_DATE_INDEX = 2;
	private static final int INPUT_TIME_INDEX = 3;
	
	private static final int INPUT_LENGTH_NODATETIME = 2;  
	private static final int INPUT_LENGTH_WITH_DATE_NO_TIME = 3;  
	private static final int INPUT_LENGTH_WITH_DATE_TIME = 4;
	
	public static String processCommand(String userInput) throws IOException, JDOMException{
	//TODO: parser
	// incomplete dependencies. Only returns commands now.
	//CommandParser is supposed to be a part of a bigger parser class, which combines
	// all output into a String[]. Currently we initialize the array here, and will
	// move it to Parser after.
	//REFACTOR THIS PIECE OF SHIT WHEN PARSERHANDLER IS BORN
		CommandParser commandParser = new CommandParser();
		String formattedString = commandParser.parseCommand(userInput);
		EventParser eventParser = new EventParser();
		String extractedEvent = eventParser.parseEvent(userInput);
		String[] inputArray = new String[2];
		inputArray[0] = formattedString;
		inputArray[1] = extractedEvent;
		
	// test if user input is receive. remove later	
		System.out.println(inputArray[0]);
		System.out.println(inputArray[1]);
		
		Task newTask = createTask(inputArray);
		String receivedString = Storage.formatToStorage(newTask);
		return receivedString;
	}
	
	private static Task createTask(String[] userInputArray){
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
		return newTask;
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
			case "invalid":
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
