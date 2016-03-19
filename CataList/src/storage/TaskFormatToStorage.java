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
	private static final String ATTRIBUTE_COMPLETE = "Complete";
	private static final String ATTRIBUTE_INCOMPLETE = "Incomplete";
	private static final String ATTRIBUTE_STATE = "State";
	private static final String ATTRIBUTE_NUM = "ID";
	
	private static final String MESSAGE_DEFAULT_ERROR = "You've got error bitch!";
	
	private static Element task;
	private static Element completed;
	private static Element incompleted;
	private static int index = 0;
	private static int listPointer = 0;
	
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
			"/src/storage/test.xml";
	
	private static ArrayList<ArrayList<Task>> states = new ArrayList<ArrayList<Task>>();
	
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
		
		//19th march 2016 code
		
		task = new Element(ELEMENT_TASK);
		List<Element> taskList = toDoListDocument.getRootElement().getChildren();

		index = taskList.size() + 1;		
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.setAttribute(new Attribute(ATTRIBUTE_STATE, ATTRIBUTE_INCOMPLETE));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
        
		taskObj.set_index(index);

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
	    Iterator<Element> itr = taskChildren.iterator();
	        
	    while(itr.hasNext()){
	    	
	    		Element child = (Element) itr.next();
	    		String att = child.getAttributeValue(ATTRIBUTE_NUM);
	    		int attributeValue = Integer.parseInt(att);
	          
	    		if(attributeValue == testIndex) {	    		
	        	   itr.remove();
	           }
	    }
	    
	    for(int i=0; i<taskChildren.size(); i++){
	    	Element child = taskChildren.get(i);
	    	child.getAttribute(ATTRIBUTE_NUM).setValue(Integer.toString(i+1));
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
		String completionString;
				
		int testIndex = taskObj.get_index();
		boolean completion = taskObj.get_completionState();
		if(completion == true){
			completionString = ATTRIBUTE_COMPLETE;
		} else {
			completionString = ATTRIBUTE_INCOMPLETE;
		}
		
		List<Element> taskChildren = rootElement.getChildren();
	    Iterator<Element> itr = taskChildren.iterator();
	        
	    while(itr.hasNext()){
	    		
	    		Element child = (Element) itr.next();	    		
	    		String att = child.getAttributeValue(ATTRIBUTE_NUM);
	    		//from storage attribute
	    		String storageCompletionAttribute = child.getAttributeValue(ATTRIBUTE_STATE);
	    		int attributeValue = Integer.parseInt(att);
	    		
	    		if(attributeValue == testIndex) {
	       			
	       			child.getChild(ELEMENT_EVENT).setText(taskObj.get_task());
	    			child.getChild(ELEMENT_DATE).setText(taskObj.get_time());
	    			child.getChild(ELEMENT_TIME).setText(taskObj.get_date());
	    			if(completionString != storageCompletionAttribute){
	    				child.getAttribute(ATTRIBUTE_STATE).setValue(completionString);
	    			}
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
			listPointer = listPointer - 1;
		} catch(NullPointerException npe){
			npe.printStackTrace();
		}
	
		return undoneState;
	}
	
	public static ArrayList<Task> redoFromStorage(Task taskObj) throws IOException, JDOMException{
		
		ArrayList<Task> redoneState = new ArrayList<Task>();
		try{
			redoneState = states.get(listPointer + 1);
			listPointer = listPointer + 1;
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
	
	public ArrayList<ArrayList<Task>> stateReader() {
		
		return states;
	}
	
}