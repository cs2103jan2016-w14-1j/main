import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;
import org.jdom2.Content.CType;


public class Storage {
	private static String fileName;
	private static String LISTNAME = "todoList.xml";
	private static File file;
	
	/*** Constructor ***/
	private Storage(){
		
	}
	
	/*** Methods ***/
	/**
	 * This are the methods that Storage class will call
	 */
	
	public static boolean initFile(){
		if(!file.exists()){
			try{
				file = new File(LISTNAME);
				file.createNewFile();
			}
			catch (IOException ioe){
				ioe.printStackTrace();
                return false;
				}
			}
		else{// file exists
			//read file name
			
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
	
	
	
	public static boolean xmlFileBuilder() throws JDOMException, IOException{
		SAXBuilder jdomBuilder = new SAXBuilder();
		
		Document jdomDocument = jdomBuilder.build(LISTNAME);
		
		System.out.println(jdomDocument.getRootElement().getName());
		
		Element todoList = jdomDocument.getRootElement();
		
		Element firstItem = todoList.getChild("");
		
		IteratorIterable<Content> contents = todoList.getDescendants();
        while (contents.hasNext()) {
            Content todoList_content = contents.next();
            if (!todoList_content.getCType().equals(CType.Text) && !todoList_content.getCType().equals(CType.Comment)) {
                System.out.println(todoList_content.toString());
            }
        }
        return true;
	}
	
	public byte[] getFileInBytes(String fileName) {
        byte[] content = null;
        try {
            Path filePath = Paths.get(fileName);
            content = Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}