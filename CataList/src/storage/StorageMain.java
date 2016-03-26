package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import logic.Task;

public class StorageMain {
	
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
	private ArrayList<Task> masterList;
	
	private static String STORAGE_PATH = 
			System.getProperty("user.dir") + 
			"/src/storage/test.xml";

	public ArrayList<Task> loadTask() {
		try{
			masterList = StorageReader.readFromStorage();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (JDOMException jdome) {
			jdome.printStackTrace();
		}
		return masterList;
	}
	
	public boolean storageWrite(ArrayList<Task> masterList){
			Element task;
			int index;
			Task tempTask = null;
			try {
				TaskFormatToStorage.clearFromStorage(tempTask);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JDOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				File inputFile = new File(STORAGE_PATH);
				SAXBuilder saxBuilder = new SAXBuilder();
				Document toDoListDocument;
				toDoListDocument = saxBuilder.build(inputFile);
				
				for(int i=0; i<masterList.size(); i++){
					Task taskObj = masterList.get(i);
					task = new Element(ELEMENT_TASK);
					
					List<Element> taskList = toDoListDocument.getRootElement().getChildren();
					index = taskList.size() + 1;
					
					task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
					task.setAttribute(new Attribute(ATTRIBUTE_STATE, ATTRIBUTE_INCOMPLETE));
					task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
					task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_time()));
					task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_date()));
					
					toDoListDocument.getRootElement().addContent(task);
					
					try{
						StorageWriter.writeToStorage(toDoListDocument);
					} catch(IOException e) {
						taskObj.setMessageErrorDefault();
						return false;
					}
				}
			} catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	public String setPath(String newFileLocation){
		String result;
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try{
    		
    	    File oldFile = new File(STORAGE_PATH);
    	    File newFile = new File(newFileLocation);
    		
    	    inStream = new FileInputStream(oldFile);
    	    outStream = new FileOutputStream(newFile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    String name = oldFile.getName();
    	    System.out.println(name);
    	    
    	    String oldPath = oldFile.getAbsolutePath();
    	    System.out.println(oldPath);
    	    
    	    System.out.println("new path location: " + newFileLocation);
    	    
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    result = "File copied successful!";
    	    System.out.println(result);
    	    
    	    STORAGE_PATH = newFileLocation;
    	}catch(IOException e){
    		result = "File failed to copy!";
    		e.printStackTrace();
    		return result;
    	}
		return result;
	}
}
