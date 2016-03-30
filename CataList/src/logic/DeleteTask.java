package logic;

public class DeleteTask extends Task{
	
	private static final String MESSAGE_DELETE_SUCCESS = "The event has been deleted.";
	private static final String MESSAGE_DELETE_FAILURE = "Unable to delete event.";
	private static final String MESSAGE_DELETE_EMPTY = "No event to be deleted.";	
	private static final String COMMAND_TYPE = "delete";
	
	private static final boolean IS_MUTATOR = true;

	//Constructor
	public DeleteTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS);
	}
	
	public DeleteTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS, date);
	}
	public DeleteTask(String userInput, String date, String time){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_DELETE_SUCCESS, time, date);
	}
	
	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_DELETE_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_DELETE_EMPTY);
	}

}
