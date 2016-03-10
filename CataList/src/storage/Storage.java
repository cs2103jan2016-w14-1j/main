package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	private static String fileName;
	private static String LISTNAME = "todoList.xml";
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
		File file = new File(fileName);
		if(!file.exists()){
			return null;
		}
		return file;
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
	
	public static boolean XMlFileBuilder() throws JDOMException, IOException{
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
	}
	
	//method takes in a task and then based on command does what is needed.
	public static String formatToStorage(Task task) throws IOException, JDOMException{
		
		StorageParser.StorageParse(task);
		
		return task.get_messageToUser();
	}
	
	
	//junk code i will return to
	/*
	IteratorIterable<Content> contents = todoList.getDescendants();
    while (contents.hasNext()) {
        Content todoList_content = contents.next();
        if (!todoList_content.getCType().equals(CType.Text) && !todoList_content.getCType().equals(CType.Comment)) {
            System.out.println(todoList_content.toString());
        }
    }*/
}