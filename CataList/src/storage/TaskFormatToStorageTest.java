import static org.junit.Assert.*;

import org.junit.Test;

import logic.Task;

public class TaskFormatToStorageTest {
	
	Task testAdd;
	String expectedResults;
	
	@Test
	public void test() {
		testAdd = new Task(true, "find some bitches", "add", "fuck yes", "1300", "3 March");
		expectedResults = "fuck yes";
		assertEquals(TaskFormatToStorage.addToStorage(testAdd), expectedResults);
	}

}
