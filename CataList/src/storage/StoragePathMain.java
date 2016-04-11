//@@author A0123977U
package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import shared.LogHandler;

/**
 * This class handles all path related issues such as writing the file path
 * of the XML file, reading the file path of the XML file and also
 * exporting the XML file to the specified location of the user.
 * 
 */

public class StoragePathMain {
	
	private static String STORAGE_FILE_DIRECTORY = System.getProperty("user.dir") + "/src/storage";
	private static String STORAGE_FILE_PATH = System.getProperty("user.dir") + "/src/storage/path";
	private static final String ERROR_MESSAGE_PATH = "cannot create new path file";
	private static final String ERROR_MESSAGE_WRITE = "cannot write";
	private static final String ERROR_MESSAGE_READ = "cannot read";
	private static final String ERROR_MESSAGE_COPY = "File failed to copy!";
	private static final String SUCCESS_MESSAGE_WRITE = "write file path succesful";
	private static final String SUCCESS_MESSAGE_COPY = "File copied successful!";
	private final static Logger log = LogHandler.retrieveLog();
	
	private static String STORAGE_PATH = System.getProperty("user.dir") + "/src/storage/Catalist.xml";
	
	/**
	 * This method copies the XML file into a new location. 
	 * @param newFileLocation
	 * @return a string stating success in saving or failure.
	 */
	public String exportFile(String newFileLocation){
		String result;
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try{
			String storagePath = filePathReader();
			File oldFile = new File(storagePath);
			if(!oldFile.exists()){
				oldFile.createNewFile();
			}
    	    File newFile = new File(newFileLocation);
    	    if(!newFile.exists()){
    	    	newFile.createNewFile();
    	    }
    		
    	    inStream = new FileInputStream(oldFile);
    	    outStream = new FileOutputStream(newFile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    result = SUCCESS_MESSAGE_COPY;
    	    //System.out.println(result);
    	    
    	} catch(IOException e1){
    		result = ERROR_MESSAGE_COPY;
    		log.log(Level.FINE, e1.toString(), e1);
    		return result;
    	}
		return result;
	}
	
	/**
	 * This method reads the path to the XML storage path
	 * @return path of the current XML file
	 */
	public String filePathReader(){
		
		List<String> stringList = new ArrayList<String>();
		String pointerFilePath, stringPath;
		pointerFilePath = STORAGE_FILE_PATH;
		Path oldPath = Paths.get(pointerFilePath);
		
		Path directoryPath = Paths.get(STORAGE_FILE_DIRECTORY);
		if(!Files.exists(directoryPath)){
			try{
				Files.createDirectories(directoryPath);
			} catch (IOException e1){
				log.log(Level.FINE, e1.toString(), e1);
			}
		}
		
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
				String defaultPath = STORAGE_PATH;
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e1) {
				log.log(Level.FINE, e1.toString(), e1);
				//System.out.println(ERROR_MESSAGE_PATH);
			}
			
		}
		
		try {
			stringList = Files.readAllLines(oldPath);
		} catch (IOException e1) {
			//System.out.println(ERROR_MESSAGE_READ);
			log.log(Level.FINE, e1.toString(), e1);
			return ERROR_MESSAGE_READ;
		}
		
		stringPath = stringList.get(0);
		
		return stringPath;
	}
	
	/**
	 * This method writes the new path of the xml file to the
	 * path file for the reader to read
	 * @param path
	 */
	public void filePathWriter(String path) {
		
		String newPath = path;
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
				String defaultPath = STORAGE_PATH;
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e1) {
				//System.out.println(ERROR_MESSAGE_PATH);
				log.log(Level.FINE, e1.toString(), e1);
			}
		}
		
		try {
			Files.write(Paths.get(STORAGE_FILE_PATH), newPath.getBytes());
		} catch (IOException e1) {
			//System.out.println(ERROR_MESSAGE_WRITE);
			log.log(Level.FINE, e1.toString(), e1);
			return;
		}
		
		//System.out.println(SUCCESS_MESSAGE_WRITE);
	}
}
