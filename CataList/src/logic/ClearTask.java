//@@author a0124946
package logic;

import java.util.ArrayList;

public class ClearTask extends Task{
	private static final String MESSAGE_CLEAR_SUCCESS = "Your dashboard has been cleared.";
	private static final String MESSAGE_CLEAR_FAILURE = "Unable to clear dashboard.";
	private static final String COMMAND_TYPE = "clear";
	
	private static final boolean IS_MUTATOR = true;

	//Constructor
	public ClearTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_CLEAR_SUCCESS
				,MESSAGE_CLEAR_FAILURE);
	}
	
	public ClearTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_CLEAR_SUCCESS
				,MESSAGE_CLEAR_FAILURE, dateTimeArgs);
	}
	
}
