package storage;

import logic.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	private static Element task;
	private static Element event;
	private static Element time;
	private static Element date;
	private static int index = 0;
	
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
            "/CataList/src/storage/test.xml";
	
	private static ArrayList<Task> toBeDoneList = new ArrayList<Task>();
	private static ArrayList<Task> completedList = new ArrayList<Task>();
	private static ArrayList<Task> masterList = new ArrayList<Task>();
	
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
		//Document toDoListDocument = new Document(task);
		
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
        
		index = toBeDoneList.size() + 1;
		toBeDoneList.add(taskObj);
		masterList.add(taskObj);
		
		toDoListDocument.getRootElement().addContent(task);
		
		StorageWriter.writeToStorage(toDoListDocument);
		
		return taskObj.get_messageToUser();
	}
	
	public static String deleteFromStorage(Task taskObj) {
		 List<Element> taskChildren = new ArrayList<Element>();
	        for (int i = 0; i < index; i++) {
	            Element taskChild = taskChildren.get(i);
	            
	            taskChild.detach();
	        }
	        return taskObj.get_messageToUser();
	}
	
	public static void clearFromStorage() throws IOException, JDOMException {

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

		writeToStorage(document);
	} 
	
	public static boolean displayFromStorage() throws IOException, JDOMException{

		StorageReader.readFromStorage();

		return true;
	}
	
	/*
	public static boolean displayFromStorage(){
		
		  try {  
			   // converted file to document object  
			   Document document = saxBuilder.build(file);  
	        for (int i = 0; i < index; i++) {
	            Element masterTaskChild = masterList.get(i);
	            
	            if(taskObj.equals(masterTaskChild)){
	            	masterList.remove(i);
	            }
	        }
	        
	        for (int i = 0; i < index; i++) {
	            Element todoTaskChild = toBeDoneList.get(i);
	            
	            if(taskObj.equals(todoTaskChild)){
	            	toBeDoneList.remove(i);
	            }
	        }
	        
	        for (int i = 0; i < index; i++) {
	            Element completedChild = completedList.get(i);
	            
	            if(taskObj.equals(completedChild)){
	            	completedList.remove(i);
	            }
	        }
	        taskObj.detach();
	        return taskObj.get_messageToUser();
	}
	
	
	public static void main(String args[]){
		
		String command = taskObj.get_cmd();
		
		switch(command){
			case COMMAND_ADD: 
				return addToStorage(taskObj);
			case COMMAND_DELETE: 
				return deleteFromStorage(taskObj);
			case COMMAND_DISPLAY:
				return displayFromStorage(taskObj);
		}
	}
	
	//junkcode
	
	/*public static boolean displayFromStorage(){
		
		  try {  
			   // converted file to document object  
			   Document document = SAXBuilder.build(file);  
			     
			   // get root node from xml  
			   Element rootNode = document.getRootElement();  
			     
			   // got all xml elements(tasks) into a list  
			      List<Task> taskList = rootNode.getChildren(ELEMENT_TASK);  
			        
			      for(int i=0; i<taskList.size(); i++){  
			       Element element = taskList.get(i);  
			       System.out.println(element);
			      }  
			      return true;
			       
			  } catch (JDOMException e) {  
			   // TODO Auto-generated catch block  
			   e.printStackTrace();  
			  } catch (IOException e) {  
			   // TODO Auto-generated catch block  
			   e.printStackTrace();  
			  } 
	}
	*/
	
	/* Aaron's previous code
	public TaskFormatToStorage(Task task){
		switch(task.get_task()){
		case DEADLINE :
			this.taskName = task.get_task();
			break;
		
		case EVENT :
			this.taskName = task.get_task();
			break;
			
		//floating	
		default:
			this.taskName = task.get_task();
			break;
		}
	}
	*/
}