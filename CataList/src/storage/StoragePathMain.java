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
	
	private static String STORAGE_FILE_PATH = System.getProperty("user.dir") + "/src/storage/path";
	
	public String exportFile(String newFileLocation){
		String result;
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try{
			String storagePath = filePathReader();
			File oldFile = new File(storagePath);
			if(oldFile.exists()){
				oldFile.createNewFile();
			}
    	    //File oldFile = new File(STORAGE_PATH);
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
    	    result = "File copied successful!";
    	    System.out.println(result);
    	    
    	}catch(IOException e){
    		result = "File failed to copy!";
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
		
		File pathFile = new File(STORAGE_FILE_PATH);
		if(!pathFile.exists()){
			try {
				pathFile.createNewFile();
				String defaultPath = System.getProperty("user.dir")+ "/src/storage/test.xml";
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("cannot create new path file");
			}
			
		}
		
		try {
			stringList = Files.readAllLines(oldPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("cannot print");
			e.printStackTrace();
			return "cannot print";
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
				String defaultPath = System.getProperty("user.dir")+ "/src/storage/test.xml";
				Files.write(Paths.get(STORAGE_FILE_PATH), defaultPath.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("path file cannot be created");
				e.printStackTrace();
			}
		}
		
		try {
			Files.write(Paths.get(STORAGE_FILE_PATH), newPath.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("cannot write");
			e.printStackTrace();
			return;
		}
		
		System.out.println("filePathWriter: write file path correctly");
	}
}
