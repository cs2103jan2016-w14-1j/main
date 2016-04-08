//@@author A0123977U
package storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import logic.Task;

public class StorageReader {
	
	private static final String SYMBOL_EMPTY = "";
	private static final String ELEMENT_START_TIME = "StartTime";
	private static final String ELEMENT_END_TIME = "EndTime";
	private static final String ELEMENT_START_DATE = "StartDate";
	private static final String ELEMENT_END_DATE = "EndDate";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ELEMENT_SUCCESS_MESSAGE = "success";
	private static final String ELEMENT_FAIL_MESSAGE = "fail";
	private static final String ELEMENT_DISPLAY = "display";
	private static final String ATTRIBUTE_COMPLETE = "Complete";
	private static final String ATTRIBUTE_STATE = "State";
	
	private static ArrayList<ArrayList<String>> dateTimeArgs;
	private static ArrayList<String> dateList;
	private static ArrayList<String> timeList;
	
	public ArrayList<Task> readFromStorage(String path, File xmlFile) throws IOException, JDOMException{
		
		dateList = new ArrayList<String>();
		timeList = new ArrayList<String>();
		dateTimeArgs = new ArrayList<ArrayList<String>>();
		
		SAXBuilder saxBuilder = new SAXBuilder();
	
		Document todoListDocument = (Document) saxBuilder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement(); //rootnode is a tasklist
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		ArrayList<Task> listOfTask = new ArrayList<Task>(list.size());
				
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			//TODO:
			dateList.clear();
			timeList.clear();
			dateTimeArgs.clear();
			//END HOTFIX
			String startDate = node.getChildText(ELEMENT_START_DATE);
			String endDate = node.getChildText(ELEMENT_END_DATE);
			String startTime = node.getChildText(ELEMENT_START_TIME);
			String endTime = node.getChildText(ELEMENT_END_TIME);
			//TODO
			if(startDate != SYMBOL_EMPTY){
				dateList.add(startDate);
				if(endDate != SYMBOL_EMPTY){
					dateList.add(endDate);
				}
			}
			
			if(startTime != SYMBOL_EMPTY){
				timeList.add(startTime);
				if(startTime != SYMBOL_EMPTY){
					timeList.add(endTime);
				}
			}
			//END HOTFIX
			dateTimeArgs.add(dateList);
			dateTimeArgs.add(timeList);
			System.out.println("Adding startDate: " + startDate);
			Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), ELEMENT_DISPLAY, 
						ELEMENT_SUCCESS_MESSAGE, ELEMENT_FAIL_MESSAGE, dateTimeArgs);
			String attribute = node.getAttributeValue(ATTRIBUTE_STATE);
			isComplete(taskObj, attribute);
			taskObj.set_index(i+1);
			listOfTask.add(taskObj);
			}
			return listOfTask;
		}

	private void isComplete(Task taskObj, String attribute) {
		if(attribute.equals(ATTRIBUTE_COMPLETE)){
			taskObj.set_Complete();
		} else {
			taskObj.set_Incomplete();
		}
	}
}	

