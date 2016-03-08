import static org.junit.Assert.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Test;

import logic.Task;

public class TaskFormatToStorageTest {
	
	Task testAdd;
	String expectedResults;
	
	
	public void testAdd1() throws JDOMException, IOException {
		testAdd = new Task(true, "find some bitches", "add", "fuck yes", "1300", "5 March");
		expectedResults = "fuck yes";
		TaskFormatToStorage.addToStorage(testAdd);
	}
	
	
	public void testAdd2() throws JDOMException, IOException {
		testAdd = new Task(true, "go home and sleep", "add", "fuck yes", "0100", "32 March");
		expectedResults = "fuck yes";
		TaskFormatToStorage.addToStorage(testAdd);
	}
	
	@Test
	public void testClear() throws JDOMException, IOException {
		TaskFormatToStorage.clearFromStorage();
	}
	
	
	public void testDisplay() throws JDOMException, IOException {
		TaskFormatToStorage.displayFromStorage();
	}

}
