package logic;

public class InvalidTask extends Task{
	private static final String MESSAGE_INVALID_DEFAULT = "Invalid command";
	private static final String COMMAND_TYPE = "invalid";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public InvalidTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_INVALID_DEFAULT
				,MESSAGE_INVALID_DEFAULT);
	}

}
