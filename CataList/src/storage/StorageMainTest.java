package storage;

import static org.junit.Assert.*;

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
	
	/**
	 * This function checks whether testWrite writes into the file or not.
	 * It returns a boolean value which we check is true or not.
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void testWrite() throws JDOMException, IOException {
		test = new Task(true, "hello", "add", "", "1300", "5 March");
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
		test = new Task(true, "hello", "add", "", "1300", "5 March");
		masterList = new ArrayList<Task>();
		masterList.add(test);
		
		StorageMain storeMain = new StorageMain();
		
		ArrayList<Task> testList = new ArrayList<Task>();
		testList = storeMain.loadTask();
		
		assertEquals(testList.get(0).get_task(), masterList.get(0).get_task());
	}
}
