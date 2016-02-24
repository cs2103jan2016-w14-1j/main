import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


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

	
	public static void XML(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setValidating(true);
	    factory.setIgnoringElementContentWhitespace(true);
	    try {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(file);
	        // Do something with the document here.
	    } catch (ParserConfigurationException e) {
	    } catch (SAXException e) {
	    } catch (IOException e) { 
	    }
		}
	}
	
