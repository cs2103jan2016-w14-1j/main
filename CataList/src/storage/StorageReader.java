package storage;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import logic.Task;

public class StorageReader {
	
	private static final String ELEMENT_TASK = "Task";
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
            "/src/storage/test.xml";
	
	private static final String ELEMENT_START_TIME = "StartTime";
	private static final String ELEMENT_END_TIME = "EndTime";
	private static final String ELEMENT_START_DATE = "StartDate";
	private static final String ELEMENT_END_DATE = "EndDate";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ATTRIBUTE_NUM = "ID";
	private static final String ATTRIBUTE_COMPLETE = "Complete";
	private static final String ATTRIBUTE_INCOMPLETE = "Incomplete";
	private static final String ATTRIBUTE_STATE = "State";
	
	private static ArrayList<ArrayList<String>> dateTimeArgs;
	private static ArrayList<String> dateList;
	private static ArrayList<String> timeList;
	private static int completeIndex = 0;
	private static int incompleteIndex = 0;
	
	public static ArrayList<Task> readFromStorage(String path) throws IOException, JDOMException{
		
		dateList = new ArrayList<String>();
		timeList = new ArrayList<String>();
		dateTimeArgs = new ArrayList<ArrayList<String>>();
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(path);
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement(); //rootnode is a tasklist
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		ArrayList<Task> listOfTask = new ArrayList<Task>(list.size());
		
		
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			
			/*Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), "display", "",
					node.getChildText(ELEMENT_DATE), node.getChildText(ELEMENT_TIME));*/
			String startDate = node.getChildText(ELEMENT_START_DATE);
			String endDate = node.getChildText(ELEMENT_END_DATE);
			String startTime = node.getChildText(ELEMENT_START_TIME);
			String endTime = node.getChildText(ELEMENT_END_TIME);
			
			dateList.add(startDate);
			dateList.add(endDate);
			
			timeList.add(startTime);
			timeList.add(endTime);
			
			dateTimeArgs.add(dateList);
			dateTimeArgs.add(timeList);

			Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), "display", 
									"success", "fail", dateTimeArgs);
			String attribute = node.getAttributeValue(ATTRIBUTE_STATE);
			if(attribute.equals(ATTRIBUTE_COMPLETE)){
				taskObj.set_Complete();
			} else {
				taskObj.set_Incomplete();
			}
			taskObj.set_index(i+1);
			listOfTask.add(taskObj);
			
		}
		
		return listOfTask;
	}
}