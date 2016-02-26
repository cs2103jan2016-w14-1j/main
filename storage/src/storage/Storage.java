package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Storage {
	private static String fileName;
	static File file;
	
	/*** Constructor ***/
	private Storage(){
		
	}
	
	/*** Methods ***/
	/**
	 * This are the methods that Storage class will call
	 */
	
	public static boolean initFile(){
		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch (IOException ioe){
				ioe.printStackTrace();
                return false;
				}
			}
		else{// file exists
			//read file name
			
			}
		return true;
		}

}
	