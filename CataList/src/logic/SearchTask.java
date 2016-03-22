package logic;

public class SearchTask extends Task {

	private static final String MESSAGE_SEARCH_SUCCESS = "Your search query has returned:";
	private static final String MESSAGE_SEARCH_FAILURE = "Unable to perform search.";
	private static final String MESSAGE_SEARCH_EMPTY = "Nothing to return.";
	private static final String COMMAND_TYPE = "search";
	
	private static final boolean IS_MUTATOR = true;
	
	//Constructor
	public SearchTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SEARCH_SUCCESS);
	}
	
	public SearchTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SEARCH_SUCCESS, date);
	}
	public SearchTask(String userInput, String date, String time){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_SEARCH_SUCCESS, time, date);
	}

	public void setErrorMessageDefault(){
		this.set_messageToUser(MESSAGE_SEARCH_FAILURE);
	}
	
	public void setErrorMessageEmpty(){
		this.set_messageToUser(MESSAGE_SEARCH_EMPTY);
	}

}
