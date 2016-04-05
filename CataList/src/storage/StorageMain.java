package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import logic.Task;

public class StorageMain {
	
	private static final String ELEMENT_TASK = "Task";
	private static final String ELEMENT_START_TIME = "StartTime";
	private static final String ELEMENT_END_TIME = "EndTime";
	private static final String ELEMENT_START_DATE = "StartDate";
	private static final String ELEMENT_END_DATE = "EndDate";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ATTRIBUTE_COMPLETE = "Complete";
	private static final String ATTRIBUTE_INCOMPLETE = "Incomplete";
	private static final String ATTRIBUTE_STATE = "State";
	private static final String ATTRIBUTE_NUM = "ID";
	private ArrayList<Task> masterList;
	
	private static String STORAGE_PATH = System.getProperty("user.dir") + "/src/storage/test.xml";
	
	private static String STORAGE_FILE_PATH = System.getProperty("user.dir") + "/src/storage/path";
	
	StoragePathMain storagePathMain;
	//Constructor
	public StorageMain() {
		storagePathMain = new StoragePathMain();
	}
	
	public ArrayList<Task> loadTask() {
		try{
			String path = storagePathMain.filePathReader();
			masterList = StorageReader.readFromStorage(path);
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
				clearFromStorage(tempTask);
			} catch (IOException | JDOMException e1) {
				e1.printStackTrace();
			} 
			try {
				String storagePath = storagePathMain.filePathReader();
				File inputFile = new File(storagePath);
				if(!inputFile.exists()){
					inputFile.createNewFile();
				}
				SAXBuilder saxBuilder = new SAXBuilder();
				Document toDoListDocument;
				toDoListDocument = saxBuilder.build(inputFile);
				
				for(int i=0; i<masterList.size(); i++){
					Task taskObj = masterList.get(i);
					task = new Element(ELEMENT_TASK);
					String completeStateString;
					boolean completeState = taskObj.get_completionState();
					if(completeState){
						completeStateString = ATTRIBUTE_COMPLETE;
					} else {
						completeStateString = ATTRIBUTE_INCOMPLETE;
					}
					
					List<Element> taskList = toDoListDocument.getRootElement().getChildren();
					index = taskList.size() + 1;
					
					task.setAttribute(new Attribute(ATTRIBUTE_NUM, Integer.toString(index)));
					task.setAttribute(new Attribute(ATTRIBUTE_STATE, completeStateString));
					task.addContent(new Element(ELEMENT_EVENT).setText(taskObj.get_task()));
					task.addContent(new Element(ELEMENT_START_TIME).setText(taskObj.get_startTime()));
					task.addContent(new Element(ELEMENT_START_DATE).setText(taskObj.get_startDate()));
					task.addContent(new Element(ELEMENT_END_TIME).setText(taskObj.get_endTime()));
					task.addContent(new Element(ELEMENT_END_DATE).setText(taskObj.get_endDate()));
					
					toDoListDocument.getRootElement().addContent(task);
					
					try{
						StorageWriter.writeToStorage(toDoListDocument, storagePath);
					} catch(IOException e) {
						taskObj.get_messageToUserFail();
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
	
	public void clearFromStorage(Task taskObj) throws IOException, JDOMException {
		
		String path = storagePathMain.filePathReader();
		File inputFile = new File(path);
		if(!inputFile.exists()){
			inputFile.createNewFile();
		}
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
			StorageWriter.writeToStorage(document, path);
		} catch(IOException e) {
			taskObj.get_messageToUserFail();
		}

	}
	
	public String saveFileLocation(String newFileLocation){
		
		storagePathMain.exportFile(newFileLocation);
		storagePathMain.filePathWriter(newFileLocation);
		
		return newFileLocation;
	}

}
