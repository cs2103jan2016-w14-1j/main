package logic;

import java.util.ArrayList;

public class DeleteTask extends Task{
	
	private static final String MESSAGE_DELETE_SUCCESS = "The event has been deleted.";
	private static final String MESSAGE_DELETE_FAILURE = "Unable to delete event.";
	private static final String COMMAND_TYPE = "delete";
	
	private static final boolean IS_MUTATOR = true;

	//Constructor
	public DeleteTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS
				,MESSAGE_DELETE_FAILURE);
	}
	
	public DeleteTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS
				,MESSAGE_DELETE_FAILURE, dateTimeArgs);
	}
}
