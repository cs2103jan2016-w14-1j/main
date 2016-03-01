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
	
	private static Element task;
	private static Element event;
	private static Element time;
	private static Element date;
	private static int index = 0;
	
	public static String addToStorage(Task taskObj) {
		index++;
		
		task = new Element(ELEMENT_TASK);
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
		
		return taskObj.get_messageToUser();
	}
	
	public static String deleteFromStorage(Task taskObj){
			
		 List<Element> taskChildren = new ArrayList<Element>();
	        for (int i = 0; i < index; i++) {
	            Element taskChild = taskChildren.get(i);
	            
	            taskChild.detach();
	        }
	        return taskObj.get_messageToUser();
	}
	
	public static boolean displayFromStorage(){
		
		  try {  
			   // converted file to document object  
			   Document document = saxBuilder.build(file);  
			     
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