package logic;

public class RedoTask extends Task {
	private static final String MESSAGE_REDO_SUCCESS = "Redo";
	private static final String MESSAGE_REDO_FAILURE = "Unable to redo.";
	private static final String MESSAGE_REDO_EMPTY = "Nothing to redo.";
	private static final String COMMAND_TYPE = "redo";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public RedoTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_REDO_SUCCESS);
	}
	
	public RedoTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_REDO_SUCCESS, date);
	}
	public RedoTask(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_REDO_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_REDO_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_REDO_EMPTY);
	}

}
