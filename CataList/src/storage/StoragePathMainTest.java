//@@author A0123977U
package storage;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.jdom2.JDOMException;

public class StoragePathMainTest {
	
	
	public void testSave() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		
		String newLocation = "C:\\Users\\asus\\Desktop\\tempFolder\\test.xml";
		
		storePathMain.exportFile(newLocation);
	}
	
	
	public void testPathFileRead() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		
		String fileLocation = storePathMain.filePathReader();
		
		System.out.println(fileLocation);
	}
	
	public void testPathFileWrite() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		
		//String fileLocation = storeMain.filePathReader();
		String fileLocation = System.getProperty("user.dir") + "/src/storage/test.xml";
		storePathMain.filePathWriter(fileLocation);
		
	}
	
}
