//@@author A0123977U
package storage;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;
import org.junit.Test;

import logic.Task;

public class StorageMainTest {
	
	Task test;
	Task test1;
	String expectedResults;
	ArrayList<Task> masterList;
	ArrayList<String> dateList;
	ArrayList<String> timeList;
	ArrayList<ArrayList<String>> dateTimeArgs;
	
	/**
	 * This function checks whether testWrite writes into the file or not.
	 * It returns a boolean value which we check is true or not.
	 * @throws JDOMException
	 * @throws IOException
	 */
	@Test
	public void testWrite() throws JDOMException, IOException {
		
		dateList = new ArrayList<String>();
		timeList = new ArrayList<String>();
		dateTimeArgs = new ArrayList<ArrayList<String>>();
		
		String startDate = "5 March";
		String endDate = "20 March";
		String startTime = "1000";
		String endTime = "2359";
		
		dateList.add(startDate);
		dateList.add(endDate);
		
		timeList.add(startTime);
		timeList.add(endTime);
		
		dateTimeArgs.add(dateList);
		dateTimeArgs.add(timeList);
		
		test = new Task(true, "hello", "add", "successTest", "failTest", 
											dateTimeArgs);
		test.set_endDate(endDate);
		test.set_endTime(endTime);
		test.set_startDate(startDate);
		test.set_startTime(startTime);
		
		masterList = new ArrayList<Task>();
		masterList.add(test);
		StorageMain storeMain = new StorageMain();
		boolean result = false;
		result = storeMain.storageWrite(masterList);
		assertTrue(result);
	}
	
	/**
	 * This function checks whether testRead reads the file correctly or not.
	 * It reads the first entry in the list and asserts it with
	 * the test entry in the list.
	 * @throws JDOMException
	 * @throws IOException
	 */
	
	public void testRead() throws JDOMException, IOException {
		
		dateList = new ArrayList<String>();
		timeList = new ArrayList<String>();
		dateTimeArgs = new ArrayList<ArrayList<String>>();
		
		String startDate = "5March";
		String endDate = "30March";
		String startTime = "10am";
		String endTime = "23.59";
		
		dateList.add(startDate);
		dateList.add(endDate);
		
		timeList.add(startTime);
		timeList.add(endTime);
		
		dateTimeArgs.add(dateList);
		dateTimeArgs.add(timeList);
		
		test = new Task(true, "hello", "add", "successTest", "failTest", 
											dateTimeArgs);		
		test.set_endDate(endDate);
		test.set_endTime(endTime);
		test.set_startDate(startDate);
		test.set_startTime(startTime);

		masterList = new ArrayList<Task>();
		masterList.add(test);
		
		StorageMain storeMain = new StorageMain();
		storeMain.storageWrite(masterList);
		
		ArrayList<Task> testList = new ArrayList<Task>();
		testList = storeMain.loadTask();
		
		assertEquals(testList.get(0).get_task(), masterList.get(0).get_task());
	}
	
	/**
	 * This method tests whether it successfully saves the file 
	 * to the desired location.
	 * @throws JDOMException
	 * @throws IOException
	 */
	
	public void testFileSaveLocation() throws JDOMException, IOException {
		StorageMain storeMain = new StorageMain();
		
		//String fileLocation = storeMain.filePathReader();
		String fileLocation = "C:\\Users\\asus\\Desktop\\CataList.xml";
		//String fileLocation = "C:\\Users\\asus\\Desktop\\tempFolder\\test.xml";
		storeMain.saveFileLocation(fileLocation);
		
		File file = new File(fileLocation);
		assertTrue(file.exists());
		
	}
}
