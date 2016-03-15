package logic;

public class MarkComplete extends Task{

	private static final String MESSAGE_MARK_COMPLETE_SUCCESS = "Event marked completed.";
	private static final String MESSAGE_MARK_COMPLETE_FAILURE = "Unable to mark task as complete.";
	private static final String MESSAGE_MARK_COMPLETE_EMPTY = "Nothing to mark complete.";
	private static final String COMMAND_TYPE = "markcomplete";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public MarkComplete(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_COMPLETE_SUCCESS);
	}
	
	public MarkComplete(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_MARK_COMPLETE_SUCCESS, date);
	}
	public MarkComplete(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_MARK_COMPLETE_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_MARK_COMPLETE_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_MARK_COMPLETE_EMPTY);
	}


}
