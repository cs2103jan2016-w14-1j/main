package logic;

import java.util.ArrayList;
import java.util.Collections;

import parser.ParserMain;
import storage.StorageMain;

public class LogicMain {
	
	private ArrayList<Task> completeTasks;
	private ArrayList<Task> incompleteTasks;
	private ArrayList<Task> masterListTasks;
	
	private ArrayList<Task> operatingTasks;
	
	private ArrayList<ArrayList<Task>> state;
	private int pointingAt;
	
	private static final int INCOMPLETE_LIST_INDEX = 0;
	private static final int COMPLETE_LIST_INDEX = 1;
	private static final int MASTER_LIST_INDEX = 2;
	private static final int INPUT_INDEX_TO_ARRAY_CORRECTION = 1;
	ParserMain inputParser;
	StorageMain storageSystem;
	
	public LogicMain(){
		inputParser = new ParserMain();
		storageSystem = new StorageMain();
		
		masterListTasks = new ArrayList<Task>();
		completeTasks = new ArrayList<Task>();
		incompleteTasks = new ArrayList<Task>();
		state = new ArrayList<ArrayList<Task>>();
		
		masterListTasks = new ArrayList<Task>(storageSystem.loadTask());
		regenerateSubListsFromMasterList();
		operatingTasks = new ArrayList<Task>(masterListTasks);
		state.add(new ArrayList<Task>(masterListTasks));
		pointingAt = 0;
	}
	
	public String processCommand(String userInput){
		String[] formattedInput = inputParser.processInput(userInput);
		ArrayList<ArrayList<String>> dateTimeArgs = inputParser.processDateTime(userInput);
		Task newCreatedTask = LogicHandler.processCommand(formattedInput, dateTimeArgs);
		String feedbackToUI = operateOnTask(newCreatedTask);

		if(isMutatorAndNotUndoRedo(newCreatedTask)){
			ArrayList<Task> addToState = new ArrayList<Task>(masterListTasks);
			state.add(addToState);
			pointingAt++;
		}
		
		regenerateSubListsFromMasterList();
		if(!isSearchOrSort(newCreatedTask)){
			operatingTasks = new ArrayList<Task>(masterListTasks);
		}
		//sortList();
		storageSystem.storageWrite(masterListTasks);
		return feedbackToUI;
	}
	
	public ArrayList<Task> getOperatingTasksForUI(){
		return operatingTasks;
	}
	
	public ArrayList<Task> getCompleteTasksForUI(){

		return completeTasks;
	}
	
	public ArrayList<Task> getIncompleteTasksForUI(){
		return incompleteTasks;
	}
	
	private boolean isSearchOrSort(Task taskInput){
		boolean result = false;
		if(taskInput.get_cmd().equalsIgnoreCase("search")){
			result = true;
		}
		return result;
	}
	private String operateOnTask(Task requestedTask){
		String commandType = requestedTask.get_cmd();
		switch(commandType){
			case "add":
				return doAdd(requestedTask);
			case "delete":
				return doDelete(requestedTask);
			case "clear" :
				return doClear(requestedTask);
			case "display" :
				return doDisplay(requestedTask);
			case "edit" :
				return doEdit(requestedTask);
			case "redo" : 
				return doRedo(requestedTask);
			case "undo" :
				return doUndo(requestedTask);
			case "search" :
				return doSearch(requestedTask);
			case "markcomplete" :
				return doMarkComplete(requestedTask);
			case "markincomplete" :
				return doMarkIncomplete(requestedTask);
			case "help" :
			case "tutorial" :
			case "calendar" :
			case "exit" :
				return opForUI(requestedTask);
			case "invalid" :
				return doInvalid(requestedTask);
			default: 
				return "UNKNOWN OPERATION";
		}
	}
	
	private void regenerateSubListsFromMasterList(){
		incompleteTasks.clear();
		completeTasks.clear();
		for(Task eachTask : masterListTasks){
			if(eachTask.get_completionState()){
				completeTasks.add(eachTask);
			} else {
				incompleteTasks.add(eachTask);
			}
		}
	}
	
	private ArrayList<Task> sortList(){
		Collections.sort(masterListTasks);
		return masterListTasks;
	}
	/*
	private void saveBackToOriginalList(){
		if(operatingOn == MASTER_LIST_INDEX){
			masterListTasks = operatingTasks;
		} else if (operatingOn == COMPLETE_LIST_INDEX){
			completeTasks = operatingTasks;
		} else if (operatingOn == INCOMPLETE_LIST_INDEX){
			incompleteTasks = operatingTasks;
		}
	}
	*/
	
	private boolean isMutatorAndNotUndoRedo(Task inputTask){
		boolean result = false;
		if(inputTask.get_cmd().equalsIgnoreCase("undo") ||inputTask.get_cmd().equalsIgnoreCase("redo")){
			 result = false;
		} else if(inputTask.is_changed()){
			result = true;
		} 
		return result;
	}
	
	private String doAdd(Task taskToOp){
		String feedBack;
		try{
			masterListTasks.add(taskToOp);
			feedBack = taskToOp.get_messageToUserSuccess();
		} catch (Exception e) {
			feedBack = taskToOp.get_messageToUserFail();
		}
		return feedBack;
	}
	
	private String doDelete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		String feedBack;
		try{
			Task toDelete = operatingTasks.get(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION);

			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(toDelete)){
					masterListTasks.remove(i);
				}
			}
			operatingTasks.remove(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION);
			feedBack = taskToOp.get_messageToUserSuccess();
		} catch (IndexOutOfBoundsException e){
			feedBack = taskToOp.get_messageToUserFail();
		}
		return feedBack;
	}
	
	private String doClear(Task taskToOp){
		String feedBack = taskToOp.get_messageToUserSuccess();
		
		incompleteTasks.clear();
		completeTasks.clear();
		masterListTasks.clear();
		
		operatingTasks.clear();
		return feedBack;
	}
	
	private String doDisplay(Task taskToOp){
		int listToDisplay = taskToOp.get_index();
		String feedBack = taskToOp.get_messageToUserSuccess();		
		if(listToDisplay == COMPLETE_LIST_INDEX){
			operatingTasks = new ArrayList<Task>(completeTasks);
		} else if (listToDisplay == INCOMPLETE_LIST_INDEX){
			operatingTasks = new ArrayList<Task>(incompleteTasks);
		} else if(listToDisplay == MASTER_LIST_INDEX){
			operatingTasks = new ArrayList<Task>(masterListTasks);
		} else {
			return taskToOp.get_messageToUserFail();
		}
		
		return feedBack;
	}
	
	private String doEdit(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		String feedBack;
		try{
			//find and change inside masterList 
			Task toEdit = operatingTasks.get(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION);
			Task cloneTask = (Task) toEdit.cloneOf();
			cloneTask.editWith(taskToOp);
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(toEdit)){
					masterListTasks.set(i, cloneTask);
				}
			}
			operatingTasks.set(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION, cloneTask);	
		} catch (IndexOutOfBoundsException e){
			feedBack = taskToOp.get_messageToUserFail();
		}
		feedBack = taskToOp.get_messageToUserSuccess();
		return feedBack;
	}
	
	
	private String doUndo(Task taskToOp){
		String feedBack;
		try{
			pointingAt--;
			masterListTasks = new ArrayList<Task>(state.get(pointingAt));
		} catch (IndexOutOfBoundsException e){
			feedBack = taskToOp.get_messageToUserFail();
		}
		feedBack = taskToOp.get_messageToUserSuccess();
		return feedBack;
	}
	
	private String doRedo(Task taskToOp){
		String feedBack;
		try{
			pointingAt++;
			masterListTasks =  new ArrayList<Task>(state.get(pointingAt));
		} catch (IndexOutOfBoundsException e){
			feedBack = taskToOp.get_messageToUserFail();
		}
		feedBack = taskToOp.get_messageToUserSuccess();
		return feedBack;
	}
	
	
	private String doSearch(Task taskToOp){
		String toFind = taskToOp.get_task();
		ArrayList<Task> foundList = new ArrayList<Task>();
		
		for(Task eachTask : masterListTasks){
			if(eachTask.get_task().contains(toFind)){
				foundList.add(eachTask);
			}
		}
		operatingTasks = new ArrayList<Task>(foundList);
		return taskToOp.get_messageToUserSuccess();
	}
	
	private String doMarkComplete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		String feedBack;
		try{
			Task operateOn = operatingTasks.get(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION);
			Task cloneTask = (Task) operateOn.cloneOf();
			cloneTask.set_Complete();
			
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(operateOn)){
					masterListTasks.set(i, cloneTask);
				}
			}
			operatingTasks.set(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION, cloneTask);
			feedBack = taskToOp.get_messageToUserSuccess();
		} catch(IndexOutOfBoundsException e) {
			feedBack = taskToOp.get_messageToUserFail();
		}
		
		return feedBack;
	}
	
	private String doMarkIncomplete(Task taskToOp){
		int operateIndex = taskToOp.get_index();
		String feedBack;
		try{
			Task operateOn = operatingTasks.get(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION);
			Task cloneTask = (Task) operateOn.cloneOf();
			cloneTask.set_Incomplete();
			
			for(int i = 0 ; i < masterListTasks.size() ; i++){
				if(masterListTasks.get(i).equals(operateOn)){
					masterListTasks.set(i, cloneTask);
				}
			}
			
			operatingTasks.set(operateIndex - INPUT_INDEX_TO_ARRAY_CORRECTION, cloneTask);
			feedBack = taskToOp.get_messageToUserSuccess();
		} catch(IndexOutOfBoundsException e) {
			feedBack = taskToOp.get_messageToUserFail();
		}
		
		return feedBack;
	}
	
	private String doInvalid(Task taskToOp){
		String feedback = taskToOp.get_messageToUserSuccess();
		return feedback;
	}
	
	private String opForUI(Task taskToOp){
		String feedBack = taskToOp.get_messageToUserSuccess();
		return feedBack;
	}
}