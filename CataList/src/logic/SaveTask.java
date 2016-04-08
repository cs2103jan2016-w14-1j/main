//@@author a0124946

package logic;

public class SaveTask extends Task {
	private static final String MESSAGE_SAVE_SUCCESS = "New file location saved";
	private static final String MESSAGE_SAVE_FAIL = "Unable to save new file location.";
	private static final String COMMAND_TYPE = "save";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public SaveTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SAVE_SUCCESS
				,MESSAGE_SAVE_FAIL);
	}
}
