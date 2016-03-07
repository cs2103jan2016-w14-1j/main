package storage;

import logic.Task;

public class StorageParser {
	
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_CLEAR= "clear";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_SORT = "sort";
	private static final String COMMAND_INVALID = "invalid";
	
	public static String StorageParse (Task taskObj){
		
		String commandType = taskObj.get_cmd();
		
		if(commandType == COMMAND_ADD){
			TaskFormatToStorage.addToStorage(taskObj);
		} else if(commandType == COMMAND_DELETE) {
			TaskFormatToStorage.deleteFromStorage(taskObj);
		} else if(commandType == COMMAND_DISPLAY) {
			TaskFormatToStorage.displayFromStorage(taskObj);
		} else if(commandType == COMMAND_CLEAR) {
			TaskFormatToStorage.clearFromStorage(taskObj);
		} else if(commandType == COMMAND_INVALID) {
			TaskFormatToStorage.invalidTaskToStorage(taskObj);
		} else {
			TaskFormatToStorage.invalidTaskToStorage(taskObj);
		}
		
	}
}
