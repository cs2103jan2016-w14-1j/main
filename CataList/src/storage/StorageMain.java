package storage;

import java.io.File;
import java.io.IOException;
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

	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
			"/src/storage/test.xml";
	
	public boolean storageWrite(ArrayList<Task> masterList){
			Element task;
			int index;
			Task tempTask;
			TaskFormatToStorage.clearFromStorage(tempTask);
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
}
