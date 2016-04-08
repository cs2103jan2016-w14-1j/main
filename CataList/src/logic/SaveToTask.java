//@@author a0124946
package logic;

import java.util.ArrayList;

public class SaveToTask extends Task{
	private static final String MESSAGE_SAVETO_SUCCESS = "Displaying file explorer";
	private static final String MESSAGE_SAVETO_FAILURE = "Unable to display file explorer";
	private static final String COMMAND_TYPE = "saveto";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public SaveToTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SAVETO_SUCCESS
				,MESSAGE_SAVETO_FAILURE);
	}
	
	public SaveToTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_SAVETO_SUCCESS
				,MESSAGE_SAVETO_FAILURE);
	}
}
