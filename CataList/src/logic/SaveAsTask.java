//@@author a0124946
package logic;

import java.util.ArrayList;

public class SaveAsTask extends Task{
	private static final String MESSAGE_SAVEAS_SUCCESS = "Displaying file explorer";
	private static final String MESSAGE_SAVEAS_FAILURE = "Unable to display file explorer";
	private static final String COMMAND_TYPE = "saveas";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public SaveAsTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SAVEAS_SUCCESS
				,MESSAGE_SAVEAS_FAILURE);
	}
	
	public SaveAsTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_SAVEAS_SUCCESS
				,MESSAGE_SAVEAS_FAILURE);
	}
}
