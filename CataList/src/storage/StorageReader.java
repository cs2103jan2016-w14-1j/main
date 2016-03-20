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
	
	private static final String ELEMENT_TIME = "Time";
	private static final String ELEMENT_DATE = "Date";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ATTRIBUTE_NUM = "ID";
	private static final String ATTRIBUTE_COMPLETE = "Complete";
	private static final String ATTRIBUTE_INCOMPLETE = "Incomplete";
	private static final String ATTRIBUTE_STATE = "State";
	
	private static int completeIndex = 0;
	private static int incompleteIndex = 0;
	
	public static ArrayList<Task> readFromStorage() throws IOException, JDOMException{
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(STORAGE_PATH);
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement(); //rootnode is a tasklist
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		ArrayList<Task> listOfTask = new ArrayList<Task>(list.size());
		
		
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			
			Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), "display", "",
					node.getChildText(ELEMENT_DATE), node.getChildText(ELEMENT_TIME));
			taskObj.set_index(i+1);
			listOfTask.add(taskObj);
			
		}
		
		return listOfTask;
	}
		
	//new method
	public static ArrayList<Task> readCompletedTasks() throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(STORAGE_PATH);
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement();
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		ArrayList<Task> listOfTask = new ArrayList<Task>(list.size());
		
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			String completion = node.getAttributeValue(ATTRIBUTE_STATE);
			if(completion.equals(ATTRIBUTE_COMPLETE)){
				Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), "display", "",
						node.getChildText(ELEMENT_DATE), node.getChildText(ELEMENT_TIME));
				taskObj.set_index(completeIndex+1);
				listOfTask.add(taskObj);
			}
		}
		
		return listOfTask;
	}
	
	public static ArrayList<Task> readUncompletedTasks() throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(STORAGE_PATH);
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement();
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		ArrayList<Task> listOfTask = new ArrayList<Task>(list.size());
		
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			String completion = node.getAttributeValue(ATTRIBUTE_STATE);
			if(completion.equals(ATTRIBUTE_INCOMPLETE)){
				Task taskObj = new Task(true, node.getChildText(ELEMENT_EVENT), "display", "",
						node.getChildText(ELEMENT_DATE), node.getChildText(ELEMENT_TIME));
				taskObj.set_index(incompleteIndex+1);
				listOfTask.add(taskObj);
			}
		}
		
		return listOfTask;
	}
}