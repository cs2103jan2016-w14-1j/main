//@@author A0123977U
package storage;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class StorageWriter {
	
	public void writeToStorage(Document todoListDocument, String path) throws IOException{
		
		XMLOutputter serializer = new XMLOutputter();
	    
	    serializer.setFormat(Format.getPrettyFormat());
	    //serializer.output(todoListDocument, new FileWriter(STORAGE_PATH));
	    serializer.output(todoListDocument, new FileWriter(path));
	}
}
