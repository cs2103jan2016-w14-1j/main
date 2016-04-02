package logic;

public class TutorialTask extends Task {
	private static final String MESSAGE_TUTORIAL_DEFAULT = "Displaying Tutorial";
	private static final String COMMAND_TYPE = "tutorial";
	
	private static final boolean IS_MUTATOR = false;
	
	//Constructor
	public TutorialTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_TUTORIAL_DEFAULT
				,MESSAGE_TUTORIAL_DEFAULT);
	}
}
