//@@author a0124946

package parser;

import java.util.ArrayList;

public class ParserMain {
	private static final String INVALID_COMMAND_WORD = "INVALID";
	private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";
	private static final String INVALID_EVENT_MESSAGE = "Invalid entry,";
	private static final String INVALID_DATETIME_MESSAGE = "Invalid DateTime Entry";
	private static final String INVALID_INDEX_MESSAGE = "Invalid index. Please try again.";
	
	private static final String SYMBOL_EMPTY = "";
	private static final int TIME_LIST_INDEX = 1;
	private static final int DATE_LIST_INDEX= 0;
	private static final int EVENT_INDEX = 1;
	private static final int COMMAND_INDEX = 0;
	
	private static final int SIZE_EMPTY = 0;
	private static final int SIZE_WITH_START_ONLY = 1;
	private static final int SIZE_WITH_START_AND_END = 2;
	
	
	private static final String[] COMMANDS_REQUIRING_EVENT = {"add"
																,"delete"
																,"edit"};
	
	private static final String[] COMMANDS_REQUIRING_INDEX = {"delete"
																,"edit"
																,"markcomplete"
																,"markuncomplete"};
	
	protected ArrayList<String> formattedCommand;
	protected ArrayList<ArrayList<String>> parsedDateTime;
	
	public ParserMain(){
		formattedCommand = new ArrayList<String>();
	}
	
	public String[] processInput(String userInput){
		String commandWords = CommandParser.parseCommand(userInput);
		String eventWords = EventParser.parseEvent(userInput);
		formattedCommand.add(commandWords);
		formattedCommand.add(eventWords);

		checkForErrors();
		String[] returnArray = formattedCommand.toArray(new String[0]);
		formattedCommand.clear();
		return returnArray;
	}
	
	public ArrayList<ArrayList<String>> processDateTime(String userInput){
		ArrayList<ArrayList<String>> dateTimeWords = DateTimeParser.parseDateTime(userInput);
		parsedDateTime = dateTimeWords;
		checkForErrorsDateTime();
		return dateTimeWords;
	}
	
	private boolean isRequireIndex(String inputCommand){
		boolean result = false;
		for(String eachCommand : COMMANDS_REQUIRING_INDEX){
			if(inputCommand.equalsIgnoreCase(eachCommand)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	private int parseInt(String inputString){
		return IndexParser.parseIndex(inputString);
	}
	
	private void checkForErrors(){
		if(isCommandInvalid()){
			setInputInvalid(INVALID_COMMAND_MESSAGE);
		} else if(isIndexInvalid()){
			setInputInvalid(INVALID_INDEX_MESSAGE);
		} else if(isEventInvalid()){
			setInputInvalid(INVALID_EVENT_MESSAGE);
		}
	}
	
	private void checkForErrorsDateTime(){
		if(isDateTimeInvalid()){
			setInputInvalid(INVALID_DATETIME_MESSAGE);
		 }
	}
	
	
	private void setInputInvalid(String invalidMessage){
		formattedCommand.clear();
		formattedCommand.add(INVALID_COMMAND_WORD);
		formattedCommand.add(invalidMessage);
	}
	
	private boolean isCommandInvalid(){
		String receivedCommand = formattedCommand.get(COMMAND_INDEX);
		return receivedCommand.equalsIgnoreCase(INVALID_COMMAND_WORD);
	}
	
	private boolean isIndexInvalid(){
		String receivedCommand = formattedCommand.get(COMMAND_INDEX);
		boolean result = true;
		int receivedIndex = 0;
		if(isRequireIndex(receivedCommand)){
			receivedIndex = parseInt(receivedCommand + " " 
							+ formattedCommand.get(EVENT_INDEX));
		}
		
		if(receivedIndex >=0){
			result = false;
		}
		
		return result;
	}
	
	private boolean isEventInvalid(){
		String receivedEvent = formattedCommand.get(EVENT_INDEX);
		String receivedCommand = formattedCommand.get(COMMAND_INDEX);
		
		boolean isCommandRequiringEvent = false;
		boolean isEventEmpty = receivedEvent.equalsIgnoreCase(SYMBOL_EMPTY);
		for(String eachCommand : COMMANDS_REQUIRING_EVENT){
			if(receivedCommand.equalsIgnoreCase(eachCommand)){
				isCommandRequiringEvent = true;
				break;
			}
		}
		return isCommandRequiringEvent&&isEventEmpty;
	}
	
	private boolean isDateTimeInvalid(){
		ArrayList<String> dateArgs = parsedDateTime.get(DATE_LIST_INDEX);
		ArrayList<String> timeArgs = parsedDateTime.get(TIME_LIST_INDEX);
		if(dateArgs.size() != SIZE_EMPTY
			|| dateArgs.size() != SIZE_WITH_START_ONLY
			|| dateArgs.size() != SIZE_WITH_START_AND_END){
			return false;
		}
		
		if(timeArgs.size() != SIZE_EMPTY
			|| timeArgs.size() != SIZE_WITH_START_ONLY
			|| timeArgs.size() != SIZE_WITH_START_AND_END){
				return false;
		}
		return true;
	}
}
