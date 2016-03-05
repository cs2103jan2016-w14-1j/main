package storage;

import logic.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.util.IteratorIterable;
import org.jdom2.input.SAXBuilder;

public class TaskFormatToStorage {
	
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
	
	private static ArrayList<Task> toBeDoneList;
	private static ArrayList<Task> completedList;
	private static ArrayList<Task> masterList;
	
	private static Task taskObj;
	
	public static String addToStorage(Task taskObj) {
		
		Task task = new Element(ELEMENT_TASK);
		
		Document toDoListDocument = new Document(task);
		
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
		
		index++;
		toBeDoneList.add(task);
		masterList.add(task);
		
		StorageWriter writer = new StorageWriter(toDoListDocument);
		
		return taskObj.get_messageToUser();
	}
	
	public static String deleteFromStorage(Task taskObj){
			
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
	
	public static boolean displayFromStorage(){
		
		//stub files in storage reader. file only reads "todoList.xml" for now.
		StorageReader StorageReader = new StorageReader();
		
		return true;
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
	}*/
	
	
}