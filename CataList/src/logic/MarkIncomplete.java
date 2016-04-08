//@@author a0124946

package logic;

import java.util.ArrayList;

public class MarkIncomplete extends Task{

	private static final String MESSAGE_MARK_INCOMPLETE_SUCCESS = "Event marked as incomplete.";
	private static final String MESSAGE_MARK_INCOMPLETE_FAILURE = "Unable to mark event as incomplete.";
	private static final String COMMAND_TYPE = "markincomplete";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public MarkIncomplete(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_INCOMPLETE_SUCCESS
				,MESSAGE_MARK_INCOMPLETE_FAILURE);
	}
	
	public MarkIncomplete(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_MARK_INCOMPLETE_SUCCESS
				,MESSAGE_MARK_INCOMPLETE_FAILURE);
	}
}
