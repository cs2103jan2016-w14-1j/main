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
	
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
            "/src/storage/test.xml";

	public static void writeToStorage(Document todoListDocument, String path) throws IOException{
		
		
		XMLOutputter serializer = new XMLOutputter();
	    
	    serializer.setFormat(Format.getPrettyFormat());
	    //serializer.output(todoListDocument, new FileWriter(STORAGE_PATH));
	    serializer.output(todoListDocument, new FileWriter(path));
	}
}
