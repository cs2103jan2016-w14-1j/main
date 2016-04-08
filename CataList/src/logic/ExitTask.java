//@@author a0124946
package logic;

public class ExitTask extends Task {
	private static final String MESSAGE_EXIT_DEFAULT = "Displaying Exit";
	private static final String COMMAND_TYPE = "exit";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public ExitTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_EXIT_DEFAULT
				,MESSAGE_EXIT_DEFAULT);
	}
}