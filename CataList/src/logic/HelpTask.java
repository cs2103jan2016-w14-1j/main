package logic;

import java.util.ArrayList;

public class HelpTask extends Task{
	private static final String MESSAGE_HELP_SUCCESS = "Displaying Help";
	private static final String MESSAGE_HELP_FAILURE = "Unable to display Help";
	private static final String COMMAND_TYPE = "help";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public HelpTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_HELP_SUCCESS
				,MESSAGE_HELP_FAILURE);
	}
	
	public HelpTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_HELP_SUCCESS
				,MESSAGE_HELP_FAILURE);
	}
}
