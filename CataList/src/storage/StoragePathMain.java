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

public class StoragePathMain {
	
	private static String STORAGE_FILE_DIRECTORY = System.getProperty("user.dir") + "/src/storage";
	private static String STORAGE_FILE_PATH = System.getProperty("user.dir") + "/src/storage/path";
	private static final String ERROR_MESSAGE_PATH = "cannot create new path file";
	private static final String ERROR_MESSAGE_WRITE = "cannot write";
	private static final String ERROR_MESSAGE_READ = "cannot read";
	private static final String ERROR_MESSAGE_COPY = "File failed to copy!";
	private static final String SUCCESS_MESSAGE_WRITE = "write file path succesful";
	private static final String SUCCESS_MESSAGE_COPY = "File copied successful!";
	
	private static String STORAGE_PATH = System.getProperty("user.dir") + "/src/storage/test.xml";
	
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
    		
    	    String name = oldFile.getName();
    	    System.out.println("exportFile: " + name);
    	    
    	    String oldPath = oldFile.getAbsolutePath();
    	    System.out.println("exportFile: old path: " + oldPath);
    	    
    	    System.out.println("exportFile: saved file location: " + newFileLocation);
    	    
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    result = SUCCESS_MESSAGE_COPY;
    	    System.out.println(result);
    	    
    	}catch(IOException e){
    		result = ERROR_MESSAGE_COPY;
    		e.printStackTrace();
    		return result;
    	}
		return result;
	}
	
	public String filePathReader(){
		
		List<String> stringList = new ArrayList<String>();
		
		String pointerFilePath, stringPath;
		
		pointerFilePath = STORAGE_FILE_PATH;
		
		Path oldPath = Paths.get(pointerFilePath);
		
		Path directoryPath = Paths.get(STORAGE_FILE_DIRECTORY);
		if(!Files.exists(directoryPath)){
			try{
				Files.createDirectories(directoryPath);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
				String defaultPath = STORAGE_PATH;
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(ERROR_MESSAGE_PATH);
			}
			
		}
		
		try {
			stringList = Files.readAllLines(oldPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(ERROR_MESSAGE_READ);
			e.printStackTrace();
			return ERROR_MESSAGE_READ;
		}
		
		stringPath = stringList.get(0);
		
		return stringPath;
	}
	
	public void filePathWriter(String path) {
		
		String newPath = path;
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
				String defaultPath = STORAGE_PATH;
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(ERROR_MESSAGE_PATH);
				e.printStackTrace();
			}
		}
		
		try {
			Files.write(Paths.get(STORAGE_FILE_PATH), newPath.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(ERROR_MESSAGE_WRITE);
			e.printStackTrace();
			return;
		}
		
		System.out.println(SUCCESS_MESSAGE_WRITE);
	}
}
