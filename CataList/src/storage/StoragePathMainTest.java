//@@author A0123977U
package storage;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.jdom2.JDOMException;

public class StoragePathMainTest {
	
	@Test
	public void testSave() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		
		String newLocation = "C:\\Users\\asus\\Desktop\\CataList.xml";
		
		storePathMain.exportFile(newLocation);
		
		File file = new File(newLocation);
		assertTrue(file.exists());
	}
	
	
	public void testPathFileRead() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		storePathMain.filePathWriter("C:\\Users\\asus\\Desktop\\CataList.xml");
		
		String fileLocation = storePathMain.filePathReader();
		
		assertEquals(fileLocation, "C:\\Users\\asus\\Desktop\\CataList.xml");
	}
	
	public void testPathFileWrite() throws JDOMException, IOException {
		StoragePathMain storePathMain = new StoragePathMain();
		
		String fileLocation = System.getProperty("user.dir") + "/src/storage/test.xml";
		storePathMain.filePathWriter(fileLocation);
		
	}
	
}
