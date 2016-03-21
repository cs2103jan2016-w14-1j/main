package logic;

public class HelpTask extends Task{
	private static final String MESSAGE_GET_HELP = "help";
	private static final String COMMAND_TYPE = "add";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public HelpTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_GET_HELP);
	}
	
	public HelpTask(String userInput, String date){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_GET_HELP);
	}
	public HelpTask(String userInput, String date, String time){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_GET_HELP);
	}

	public void setErrorMessageDefault(){
		//
	}
	
	public void setErrorMessageEmpty(){
		//
	}
	
	public static String getSuccessMessage(){
		return MESSAGE_GET_HELP;
	}
}
