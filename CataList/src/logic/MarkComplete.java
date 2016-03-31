package logic;

import java.util.ArrayList;

public class MarkComplete extends Task{

	private static final String MESSAGE_MARK_COMPLETE_SUCCESS = "Event marked completed.";
	private static final String MESSAGE_MARK_COMPLETE_FAILURE = "Unable to mark task as complete.";
	private static final String COMMAND_TYPE = "markcomplete";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public MarkComplete(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_COMPLETE_SUCCESS
				,MESSAGE_MARK_COMPLETE_FAILURE);
	}
	
	public MarkComplete(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_MARK_COMPLETE_SUCCESS
				,MESSAGE_MARK_COMPLETE_FAILURE);
	}
}
