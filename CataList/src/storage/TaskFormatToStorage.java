package storage;

import logic.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TaskFormatToStorage extends StorageWriter {
	
	private static final String ELEMENT_TASKLIST = "TaskList";
	private static final String ELEMENT_TASK = "Task";
	private static final String ELEMENT_TIME = "Time";
	private static final String ELEMENT_DATE = "Date";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ATTRIBUTE_NUM = "ID";
	
	private String taskName;
	private String startTime;
	private String endTime;
	private String deadline;
	
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_EDIT = "edit";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_CLEAR= "clear";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_SORT = "sort";
	
	private static final String MESSAGE_DEFAULT_ERROR = "You've got error bitch!";
	
	private static Element task;
	private static Element event;
	private static Element time;
	private static Element date;
	private static int index = 0;
	private static int listPointer = 0;
	
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
			"/src/storage/test.xml";
	
	private static ArrayList<Task> toBeDoneList = new ArrayList<Task>();
	private static ArrayList<Task> completedList = new ArrayList<Task>();
	private static ArrayList<Task> masterList = new ArrayList<Task>();
	
	private static LinkedList<ArrayList<Task>> states = new LinkedList<ArrayList<Task>>();
	
	public static String addToStorage(Task taskObj) throws IOException, JDOMException {
		File inputFile = new File(STORAGE_PATH);
		Document toDoListDocument = null;
		
		if(inputFile.exists()) {
			SAXBuilder saxBuilder = new SAXBuilder();
			toDoListDocument = saxBuilder.build(inputFile);
		} else {
			Element taskList = new Element(ELEMENT_TASKLIST);
			toDoListDocument = new Document(taskList);
		}
		
		task = new Element(ELEMENT_TASK);
		List<Element> taskList = toDoListDocument.getRootElement().getChildren();

		index = taskList.size() + 1;		
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
        
		taskObj.set_index(index);

		toBeDoneList.add(taskObj);
		masterList.add(taskObj);
		
		toDoListDocument.getRootElement().addContent(task);
		
		try{
			StorageWriter.writeToStorage(toDoListDocument);
		} catch(IOException e) {
			taskObj.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return taskObj.get_messageToUser();
		}
		
		states.add(StorageReader.readFromStorage());
		listPointer++;
		
		return taskObj.get_messageToUser();
	}
	
	
	public static String deleteFromStorage(Task taskObj) throws JDOMException, IOException {
		
		File inputFile = new File(STORAGE_PATH);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element rootElement = document.getRootElement();
		
		int testIndex = taskObj.get_index();
		List<Element> taskChildren = rootElement.getChildren();
		//List<Element> taskChildren = rootElement.getChildren(ATTRIBUTE_NUM);
	    Iterator<Element> itr = taskChildren.iterator();
	   // List<Element> elements = new ArrayList<Element>();
	        
	    while(itr.hasNext()){
	    	
	    		Element child = (Element) itr.next();
	    		//String att = child.getChild(ELEMENT_EVENT).getText();
	    		
	    		String att = child.getAttributeValue(ATTRIBUTE_NUM);
	    		
	    		int attributeValue = Integer.parseInt(att);
	          
	    		//attribute is taskID, but task is task
	    		//System.out.println(att + ":" + taskObj.get_task());
	    		if(attributeValue == testIndex) {
	            // if((Integer.parseInt(att)).equals(taskObj.get_id())){
	    		
	        	   itr.remove();
	           }
	    }
	    
	    for(int i=0; i<taskChildren.size(); i++){
    		
	    	Element child = taskChildren.get(i);
	    	
    		//child.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(i)));
	    	child.getAttribute(ATTRIBUTE_NUM).setValue(Integer.toString(i+1));
    		//String att = child.getAttributeValue(ATTRIBUTE_NUM);
    		//attribute is taskID, but task is task
    		//System.out.println(att + ":" + taskObj.get_task());
    		
	    }
	    
	    try{
			StorageWriter.writeToStorage(document);
		} catch(IOException e) {
			taskObj.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return taskObj.get_messageToUser();
		}
	    states.add(StorageReader.readFromStorage());
	    listPointer++;
	    
	    return taskObj.get_messageToUser();
	}
	
	
	public static String editFromStorage(Task taskObj) throws JDOMException, IOException {
		
		File inputFile = new File(STORAGE_PATH);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element rootElement = document.getRootElement();
				
		int testIndex = taskObj.get_index();
		List<Element> taskChildren = rootElement.getChildren();
	    Iterator<Element> itr = taskChildren.iterator();
	        
	    while(itr.hasNext()){
	    		
	    		Element child = (Element) itr.next();
	    		
	    		String att = child.getAttributeValue(ATTRIBUTE_NUM);
	    		//System.out.println(att + ":" + taskObj.get_task());
	    		
	    		int attributeValue = Integer.parseInt(att);
	    		
	    		//attribute is taskID, but task is task
	    		//System.out.println(att + ":" + taskObj.get_task());
	            //attribute is taskID, but task is task
	    		
	    		if(attributeValue == testIndex) {
	       			//Document toDoListDocument = new Document(task);
	       			//task.setContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
	       			//task.setContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
	       			//task.setContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
	    		
	       			child.getChild(ELEMENT_EVENT).setText(taskObj.get_task());
	    			child.getChild(ELEMENT_DATE).setText(taskObj.get_time());
	    			child.getChild(ELEMENT_TIME).setText(taskObj.get_date());
	           }
	    		
	    	}
	    try{
			StorageWriter.writeToStorage(document);
		} catch(IOException e) {
			taskObj.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return taskObj.get_messageToUser();
		}
	    
	    states.add(StorageReader.readFromStorage());
	    listPointer++;
	    
	    return taskObj.get_messageToUser();
		
	} 
	
	public static ArrayList<Task> undoFromStorage(Task taskObj) throws IOException, JDOMException{
		
		ArrayList<Task> undoneState = new ArrayList<Task>();
		try{
			undoneState = states.get(listPointer - 1);
		} catch(NullPointerException npe){
			npe.printStackTrace();
		}
	
		return undoneState;
	}
	
	public static ArrayList<Task> redoFromStorage(Task taskObj) throws IOException, JDOMException{
		
		ArrayList<Task> redoneState = new ArrayList<Task>();
		try{
			redoneState = states.get(listPointer + 1);
		} catch(NullPointerException npe){
			npe.printStackTrace();
		}
	
		return redoneState;
	}
	
	public static String clearFromStorage(Task taskObj) throws IOException, JDOMException {

		File inputFile = new File(STORAGE_PATH);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element rootElement = document.getRootElement();

		List<Element> taskList = rootElement.getChildren();
		Iterator<Element> itr = taskList.iterator();
		List<Element> elements = new ArrayList<Element>();

		while(itr.hasNext()) {
			Element subchild = (Element)itr.next();
			elements.add(subchild);
		}

		for(Element e: elements) {
			e.getParent().removeContent(e);
		}

		try{
			StorageWriter.writeToStorage(document);
		} catch(IOException e) {
			taskObj.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return taskObj.get_messageToUser();
		}
		
		states.add(StorageReader.readFromStorage());
		listPointer++;
		
		return taskObj.get_messageToUser();
	} 
	
	public static String displayFromStorage(Task taskObj) throws IOException, JDOMException{
		
		try{
			StorageReader.readFromStorage();
		} catch(IOException|JDOMException e) { 
			taskObj.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return taskObj.get_messageToUser();
		}
		return taskObj.get_messageToUser();
	}
	
	public static String invalidTaskToStorage(Task taskObj) throws IOException, JDOMException{
		
		return taskObj.get_messageToUser();
	}
	
	public LinkedList<ArrayList<Task>> stateReader() {
		
		return states;
	}
	
	/*public String undoFromStorage(Task taskObj) throws IOException, JDOMException{
			
		states.removeLast();
		
		return taskObj.get_messageToUser();
		
	}*/
}