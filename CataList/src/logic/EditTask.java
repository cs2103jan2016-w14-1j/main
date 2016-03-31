package logic;

import java.util.ArrayList;

public class EditTask extends Task{
	
	private static final String MESSAGE_EDIT_SUCCESS = "The event has been edited.";
	private static final String MESSAGE_EDIT_FAILURE = "Unable to edit event.";
	private static final String COMMAND_TYPE = "edit";
	
	private static final boolean IS_MUTATOR = true;
	//Constructor
	public EditTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_EDIT_SUCCESS
				,MESSAGE_EDIT_FAILURE);
	}
	
	public EditTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_EDIT_SUCCESS
				,MESSAGE_EDIT_FAILURE, dateTimeArgs);
	}
}
