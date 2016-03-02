package logic;

public class UndoTask extends Task{
	private static final String MESSAGE_UNDO_SUCCESS = "Undo";
	private static final String MESSAGE_UNDO_FAILURE = "Unable to undo.";
	private static final String MESSAGE_UNDO_EMPTY = "Nothing to undo.";
	private static final String COMMAND_TYPE = "undo";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public UndoTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_UNDO_SUCCESS);
	}
	
	public UndoTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_UNDO_SUCCESS, date);
	}
	public UndoTask(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_UNDO_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_UNDO_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_UNDO_EMPTY);
	}
}
