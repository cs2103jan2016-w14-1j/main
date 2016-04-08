//@@author a0124946
package logic;

import java.util.ArrayList;

public class AddTask extends Task {
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	private static final String MESSAGE_ADD_FAILURE = "Unable to add event.";
	private static final String COMMAND_TYPE = "add";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public AddTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS
				, MESSAGE_ADD_FAILURE);
	}
	
	public AddTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS
				, MESSAGE_ADD_FAILURE, dateTimeArgs);
	}
	/*
	public AddTask(String userInput, String date, String time){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_ADD_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_ADD_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_ADD_EMPTY);
	}
	
	public static String getSuccessMessage(){
		return MESSAGE_ADD_SUCCESS;
	}
	*/
}
