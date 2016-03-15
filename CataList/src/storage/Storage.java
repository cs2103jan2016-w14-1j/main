package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import logic.Task;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;
import org.jdom2.Content.CType;

import storage.StorageReader;


public class Storage {
	private static String LISTNAME = "test.xml";
	private static File file;
	private static ArrayList<Task> toBeDoneList;
	private static ArrayList<Task> completedList;
	private static ArrayList<Task> masterList;
	
	/*** Constructor ***/
	public Storage(){
		toBeDoneList = new ArrayList<Task>();
		completedList = new ArrayList<Task>();
		masterList = new ArrayList<Task>();
	}
	
	/*** Methods ***/
	/**
	 * This are the methods that Storage class will call
	 */	
	public static boolean initFile(){
		if(!file.exists()) {
			try{
				file = new File(LISTNAME);
				file.createNewFile();
			} catch (IOException ioe){
				ioe.printStackTrace();
                return false;
			}
		} else{// file exists
			//read file name
			getFileInBytes(LISTNAME);
		}
		return true;
	}
	
	public File getFile(String fileName){
		URL url = getClass().getResource(LISTNAME);
		File file = new File(url.getPath());
		
		return file;
	}
	
	public void changeFilePath(String userFileName){
		try {
			Files.move(Paths.get(LISTNAME), Paths.get(userFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] getFileInBytes(String fileName) {
        byte[] content = null;
        try {
            Path filePath = Paths.get(fileName);
            content = Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
	}
	
	
	public void loadTask() throws IOException, JDOMException {
		toBeDoneList = StorageReader.readFromStorage();
	}
	
	public ArrayList<Task> getToBeDoneList() {
		return toBeDoneList;
	}
	
	public ArrayList<Task> getCompletedList() {
		return completedList;
	}
	
/*	public static boolean XMLFileBuilder() throws JDOMException, IOException{
		Element todoList;
		
		SAXBuilder jdomBuilder = new SAXBuilder();
		
		//building the document(xml file to be used)
		Document toDoListDocument = jdomBuilder.build(LISTNAME);
		
		//this would be the root element of the document, check if element exists or not
		if(toDoListDocument.getRootElement() == null){
			todoList = new Element("masterList");
		} else {
			todoList = toDoListDocument.getRootElement();
		}
		
		Element firstItem = todoList.getChild("");
		
        return true;
	}*/
	
	//Calls to TaskFormatToStorage various methods
	public static String addToStorage(Task task) throws IOException, JDOMException{
		
		TaskFormatToStorage.addToStorage(task);
		
		return task.get_messageToUser();
	}
	
	public static String deleteFromStorage(Task task, int testIndex) throws IOException, JDOMException{
			
			TaskFormatToStorage.deleteFromStorage(task, testIndex);
			
			return task.get_messageToUser();
		}
		
	public static String editFromStorage(Task task, int testIndex) throws IOException, JDOMException{
		
		TaskFormatToStorage.editFromStorage(task, testIndex);
		
		return task.get_messageToUser();
	}
	
	public static String clearFromStorage(Task task) throws IOException, JDOMException{
		
		TaskFormatToStorage.clearFromStorage(task);
		
		return task.get_messageToUser();
	}
	
	public static String displayFromStorage(Task task) throws IOException, JDOMException{
			
			TaskFormatToStorage.displayFromStorage(task);
			
			return task.get_messageToUser();
	}

	
	public ArrayList<Task> undoFromStorage(Task task) throws IOException, JDOMException{
		
		masterList = TaskFormatToStorage.undoFromStorage(task);
		
		return masterList;

	}
	public ArrayList<Task> redoFromStorage(Task task) throws IOException, JDOMException{
			
		masterList = TaskFormatToStorage.redoFromStorage(task);
			
		return masterList;
	
	}
}