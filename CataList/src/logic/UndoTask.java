//@@author a0124946

package logic;

import java.util.ArrayList;

public class UndoTask extends Task{
	private static final String MESSAGE_UNDO_SUCCESS = "Undo";
	private static final String MESSAGE_UNDO_FAILURE = "Unable to undo.";
	private static final String COMMAND_TYPE = "undo";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public UndoTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_UNDO_SUCCESS
				,MESSAGE_UNDO_FAILURE);
	}
	
	public UndoTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_UNDO_SUCCESS
				,MESSAGE_UNDO_FAILURE, dateTimeArgs);
	}}
