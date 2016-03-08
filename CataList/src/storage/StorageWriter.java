package storage;

import logic.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class StorageWriter {
	
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

	public static void writeToStorage(Document todoListDocument) throws IOException{
		
		/*
		 * SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("todoList.xml");
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		*/
		
		XMLOutputter serializer = new XMLOutputter();
	    //serializer.output(todoListDocument, System.out);
	    
	    serializer.setFormat(Format.getPrettyFormat());
	    serializer.output(todoListDocument, new FileWriter(STORAGE_PATH));
	}
}
