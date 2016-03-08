package logic;

public class ClearTask extends Task{
	private static final String MESSAGE_CLEAR_SUCCESS = "Your dashboard has been cleared.";
	private static final String MESSAGE_CLEAR_FAILURE = "Unable to clear dashboard.";
	private static final String MESSAGE_EMPTY_FAILURE = "Nothing to clear!";
	private static final String COMMAND_TYPE = "clear";
	
	private static final boolean IS_MUTATOR = true;

	//Constructor
	public ClearTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_CLEAR_SUCCESS);
	}
	
	public ClearTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_CLEAR_SUCCESS, date);
	}
	public ClearTask(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_CLEAR_SUCCESS, time, date);
	}
	
	public void setMessageErrorEmpty(){
		this.set_messageToUser(MESSAGE_EMPTY_FAILURE);
	}
	
	public void setMessageErrorDefault(){
		this.set_messageToUser(MESSAGE_CLEAR_FAILURE);
	}
	
}
