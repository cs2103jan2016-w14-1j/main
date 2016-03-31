package logic;

import java.util.ArrayList;

public class RedoTask extends Task {
	private static final String MESSAGE_REDO_SUCCESS = "Redo";
	private static final String MESSAGE_REDO_FAILURE = "Unable to redo.";
	private static final String COMMAND_TYPE = "redo";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public RedoTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_REDO_SUCCESS
				,MESSAGE_REDO_FAILURE);
	}
	
	public RedoTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_REDO_SUCCESS
				,MESSAGE_REDO_FAILURE, dateTimeArgs);
	}
}
