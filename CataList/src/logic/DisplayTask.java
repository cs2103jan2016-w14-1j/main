package logic;

import java.util.ArrayList;

public class DisplayTask extends Task {

	private static final String MESSAGE_DISPLAY_SUCCESS = "DISPLAYing Event";
	private static final String MESSAGE_DISPLAY_FAILURE = "Unable to DISPLAY.";
	private static final String COMMAND_TYPE = "DISPLAY";
	
	private static final boolean IS_MUTATOR = false;

	//Constructor
	//Constructor
	public DisplayTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS
				,MESSAGE_DISPLAY_FAILURE);
	}
	
	public DisplayTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_DISPLAY_SUCCESS
				,MESSAGE_DISPLAY_FAILURE, dateTimeArgs);
	}	
}
