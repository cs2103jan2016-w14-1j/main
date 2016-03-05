
import logic.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class TaskFormatToStorage {
	
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
	
	private static Element task;
	private static Element event;
	private static Element time;
	private static Element date;
	private static int index = 0;
	
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
            "/CataList/src/storage/test.xml";
	
	public static void addToStorage(Task taskObj) throws JDOMException, IOException {
		/*
		changes: 
		changed type from String to void for testing, can change back later
		commented out return type
		commented out other functions
		added xmloutputter
		*/
		
		File inputFile = new File(STORAGE_PATH);
		Document document = null;
        if(inputFile.exists()) {
        	SAXBuilder saxBuilder = new SAXBuilder();
            document = saxBuilder.build(inputFile);
        } else {
        	Element taskList = new Element(ELEMENT_TASKLIST);
        	document = new Document(taskList);
        }
		
        index++;
		
		task = new Element(ELEMENT_TASK);
		task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
		task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
		task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
		task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
		
		document.getRootElement().addContent(task);
		
		FileWriter writer = new FileWriter(STORAGE_PATH);  // to write to xml
		XMLOutputter xmlOutput = new XMLOutputter();
		
        xmlOutput.setFormat(Format.getPrettyFormat()); // make display nice
        xmlOutput.output(document, writer);  // writes to xml
        xmlOutput.output(document, System.out);  // print what is written on xml
        writer.close(); // close writer
		
		//return taskObj.get_messageToUser();
	}
	
	public static void clearFromStorage() throws IOException, JDOMException {
	
		File inputFile = new File(STORAGE_PATH);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element rootElement = document.getRootElement();
		
		List<Element> taskList = rootElement.getChildren();
		Iterator itr = taskList.iterator();
		List<Element> elements = new ArrayList<Element>();

		while(itr.hasNext()) {
			Element subchild = (Element)itr.next();
			elements.add(subchild);
		}
		
		for(Element e: elements) {
			e.getParent().removeContent(e);
		}
        
        FileWriter writer = new FileWriter(STORAGE_PATH);  // to write to xml
		XMLOutputter xmlOutput = new XMLOutputter();
		
        xmlOutput.setFormat(Format.getPrettyFormat()); // make display nice
        xmlOutput.output(document, writer);  // writes to xml
        xmlOutput.output(document, System.out);  // print what is written on xml
        writer.close();
	}
	
	public static String deleteFromStorage(Task taskObj){
			
		 List<Element> taskChildren = new ArrayList<Element>();
	        for (int i = 0; i < index; i++) {
	            Element taskChild = taskChildren.get(i);
	            
	            taskChild.detach();
	        }
	        return taskObj.get_messageToUser();
	}
	
	/*
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