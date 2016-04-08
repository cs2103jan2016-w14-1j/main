//@@author a0124946

package logic;

import java.util.ArrayList;

public class SearchTask extends Task {

	private static final String MESSAGE_SEARCH_SUCCESS = "Your search query has returned:";
	private static final String MESSAGE_SEARCH_FAILURE = "Unable to perform search.";
	private static final String COMMAND_TYPE = "search";
	
	private static final boolean IS_MUTATOR = true;
	//constructor
	public SearchTask(String userInput){
		super(IS_MUTATOR, userInput, COMMAND_TYPE, MESSAGE_SEARCH_SUCCESS
				,MESSAGE_SEARCH_FAILURE);
	}
	
	public SearchTask(String userInput, ArrayList<ArrayList<String>> dateTimeArgs){
		super(IS_MUTATOR,userInput, COMMAND_TYPE, MESSAGE_SEARCH_SUCCESS
				,MESSAGE_SEARCH_FAILURE, dateTimeArgs);
	}
}
