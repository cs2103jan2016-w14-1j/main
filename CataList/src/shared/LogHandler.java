//@@author A0112204E
package shared;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.IOException;

public class LogHandler {
	
	private static final String LOG_FILE = "CataList.log";
	private static final String LOG_NAME = "CataListLog";
	private static FileHandler fileHandler;
	private static Logger logger;

	/**
	 * Get instance of Logger
	 * @return Logger log file
	 */
	public static Logger retrieveLog() {
		if(logger == null) {
			try {
				logger = Logger.getLogger(LOG_NAME);
				logger.setLevel(Level.INFO);
				logger.addHandler(getFileHandler());
			} catch (SecurityException | IOException e) {
			}
		}
		return logger;
	}
	
	private static FileHandler getFileHandler() throws SecurityException, IOException {
		if(fileHandler == null) {
			fileHandler = new FileHandler(LOG_FILE);
		}
		return fileHandler;
	}
}
