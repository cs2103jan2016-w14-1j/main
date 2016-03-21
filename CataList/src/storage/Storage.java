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
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.jdom2.Content.CType;

import storage.StorageReader;


public class Storage {
	private static String LISTNAME = "test.xml";
	private static File file;
	private static ArrayList<Task> toBeDoneList;
	private static ArrayList<Task> completedList;
	private static ArrayList<Task> masterList;
	
	private static final String MESSAGE_DEFAULT_ERROR = "Error";
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
	/**
	 * new methods loadincompleteTask and loadcompletetask
	 */
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
	
	public ArrayList<Task> loadIncompleteTask() {
		try{
			masterList = StorageReader.readUncompletedTasks();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (JDOMException jdome) {
			jdome.printStackTrace();
		}
		return masterList;
	}
	
	public ArrayList<Task> loadCompleteTask() {
		try{
			masterList = StorageReader.readCompletedTasks();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (JDOMException jdome) {
			jdome.printStackTrace();
		}
		return masterList;
	}
	
	public ArrayList<Task> getToBeDoneList() {
		return toBeDoneList;
	}
	
	public ArrayList<Task> getCompletedList() {
		return completedList;
	}
	
	public ArrayList<Task> getMasterList() {
		return masterList;
	}
	
	//Calls to TaskFormatToStorage various methods
	public static String addToStorage(Task task) {
		try{
			TaskFormatToStorage.addToStorage(task);
			masterList.add(task);
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
		return task.get_messageToUser();
	}
	
	public static String deleteFromStorage(Task task){
		int taskIndex;
		ArrayList<Task> tempList = new ArrayList<Task>();
		try{
			TaskFormatToStorage.deleteFromStorage(task);
			taskIndex = task.get_index();
			
			masterList.remove(taskIndex-1);
			for(int i=0; i<masterList.size(); i++){
				Task tempTask = masterList.get(i);
				tempTask.set_index(i+1);
				tempList.add(tempTask);
			}
			masterList = (ArrayList<Task>)tempList.clone();
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
			return task.get_messageToUser();
		}
		
	public static String editFromStorage(Task task){
		try{
			TaskFormatToStorage.editFromStorage(task);
			masterList.remove(task.get_index() -1);
			masterList.add(task.get_index() - 1, task);
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
		return task.get_messageToUser();
	}
	
	public static String clearFromStorage(Task task){
		try{
			TaskFormatToStorage.clearFromStorage(task);
			masterList.clear();
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
		return task.get_messageToUser();
	}
	
	public static String displayFromStorage(Task task){
			try{
				TaskFormatToStorage.displayFromStorage(task);
			} catch (IOException ioe){
				task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
				return task.get_messageToUser();
			} catch (JDOMException jdome) {
				task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
				return task.get_messageToUser();
			}
			return task.get_messageToUser();
	}
	
	public String undoFromStorage(Task task){
		try{
			masterList = TaskFormatToStorage.undoFromStorage(task);
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
			return task.get_messageToUser();
	}
	
	public String redoFromStorage(Task task){
		try{	
			masterList = TaskFormatToStorage.redoFromStorage(task);
		} catch (IOException ioe){
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		} catch (JDOMException jdome) {
			task.setMessageErrorDefault(MESSAGE_DEFAULT_ERROR);
			return task.get_messageToUser();
		}
		return task.get_messageToUser();
	}
}