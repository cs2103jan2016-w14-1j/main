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
	private static final String ELEMENT_TIME = "Time";
	private static final String ELEMENT_DATE = "Date";
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
				//TaskFormatToStorage.clearFromStorage(tempTask);
				clearFromStorage(tempTask);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JDOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				String storagePath = storagePathMain.filePathReader();
				File inputFile = new File(storagePath);
				//File inputFile = new File(STORAGE_PATH);
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
					task.addContent(new Element(ELEMENT_DATE).setText(taskObj.get_date()));
					task.addContent(new Element(ELEMENT_TIME).setText(taskObj.get_time()));
					
					toDoListDocument.getRootElement().addContent(task);
					
					try{
						StorageWriter.writeToStorage(toDoListDocument, storagePath);
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
	
	public void clearFromStorage(Task taskObj) throws IOException, JDOMException {
		
		String path = storagePathMain.filePathReader();
		//File inputFile = new File(STORAGE_PATH);
		File inputFile = new File(path);
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
			taskObj.setMessageErrorDefault();
		}

	}
	
	public String exportFile(String newFileLocation){
		String result;
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try{
			String storagePath = filePathReader();
			File oldFile = new File(storagePath);
			if(oldFile.exists()){
				oldFile.createNewFile();
			}
    	    //File oldFile = new File(STORAGE_PATH);
    	    File newFile = new File(newFileLocation);
    	    if(!newFile.exists()){
    	    	newFile.createNewFile();
    	    }
    		
    	    inStream = new FileInputStream(oldFile);
    	    outStream = new FileOutputStream(newFile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    String name = oldFile.getName();
    	    System.out.println("exportFile: " + name);
    	    
    	    String oldPath = oldFile.getAbsolutePath();
    	    System.out.println("exportFile: old path: " + oldPath);
    	    
    	    System.out.println("exportFile: saved file location: " + newFileLocation);
    	    
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    result = "File copied successful!";
    	    System.out.println(result);
    	    
    	}catch(IOException e){
    		result = "File failed to copy!";
    		e.printStackTrace();
    		return result;
    	}
		return result;
	}
	
	public String filePathReader(){
		
		List<String> stringList = new ArrayList<String>();
		
		String pointerFilePath, stringPath;
		
		pointerFilePath = STORAGE_FILE_PATH;
		
		Path oldPath = Paths.get(pointerFilePath);
		
		try {
			stringList = Files.readAllLines(oldPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("cannot print");
			e.printStackTrace();
			return "cannot print";
		}
		
		stringPath = stringList.get(0);
		
		
		return stringPath;
	}
	
	public void filePathWriter(String path) {
		
		String newPath = path;
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("path file cannot be created");
				e.printStackTrace();
			}
		}
		
		try {
			Files.write(Paths.get(STORAGE_FILE_PATH), newPath.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("cannot write");
			e.printStackTrace();
			return;
		}
		
		System.out.println("filePathWriter: write file path correctly");
	}
	
	public String saveFileLocation(String newFileLocation){
		
		storagePathMain.exportFile(newFileLocation);
		storagePathMain.filePathWriter(newFileLocation);
		
		return newFileLocation;
	}

}
