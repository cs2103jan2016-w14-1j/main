package storage;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;
import org.junit.Test;

import logic.Task;

public class StorageTest {
	
	Task test;
	String expectedResults;
	ArrayList<Task> masterList;
	
	private static final String MESSAGE_ADD_SUCCESS = "The event has been added.";
	
	@Test
	public void testAdd1() throws JDOMException, IOException {
		test = new Task(true, "find some bitches", "add", "fuck yes", "1300", "5 March");
		expectedResults = "fuck yes";
		TaskFormatToStorage.addToStorage(test);
	}
	
	@Test
	public void testAdd2() throws JDOMException, IOException {
		test = new Task(true, "go home and sleep", "add", "fuck yes", "0100", "32 March");
		expectedResults = "fuck yes";
		TaskFormatToStorage.addToStorage(test);
	}
	
	@Test
	public void testAdd3() throws JDOMException, IOException {
		test = new Task(true, "do some coding", "add", "fuck yes", "3000", "59 March");
		expectedResults = "fuck yes";
		TaskFormatToStorage.addToStorage(test);
	}
	
	public void testClear() throws JDOMException, IOException {
		test = new Task(true, "go home and sleep", "clear", "fuck yes", "0100", "32 March");
		Storage.clearFromStorage(test);
	}
	
	public void testDisplay() throws JDOMException, IOException {
		test = new Task(true, "go home and sleep", "add", "fuck yes", "0100", "32 March");
		TaskFormatToStorage.displayFromStorage(test);
	}
	
	public void testDelete() throws JDOMException, IOException {
		test = new Task(true, "find some bitches", "delete", "fuck yes", "0100", "32 March");
		test.set_index(2);
		Storage.deleteFromStorage(test);
	}
	
	public void testEdit1() throws JDOMException, IOException {
		test = new Task(true, "find some putang ina", "edit", MESSAGE_ADD_SUCCESS, "1234", "03 March");
		test.set_index(1);
		Storage.editFromStorage(test);
	}
	
	public void testEdit2() throws JDOMException, IOException {
		test = new Task(true, "tang ina BOBO", "edit", MESSAGE_ADD_SUCCESS, "5555", "20 March");
		test.set_index(3);
		Storage.editFromStorage(test);
	}
	
	public void testAddStorage() throws JDOMException, IOException {
		test = new Task(true, "go home and sleep", "add", MESSAGE_ADD_SUCCESS, "0100", "32 March");
		assertEquals(MESSAGE_ADD_SUCCESS, Storage.addToStorage(test));
	}
	
	public void testEditStorage() throws JDOMException, IOException {
		test = new Task(true, "find some putang ina", "edit", MESSAGE_ADD_SUCCESS, "1234", "03 March");
		test.set_index(1);
		assertEquals(MESSAGE_ADD_SUCCESS, Storage.editFromStorage(test));
	}
}
