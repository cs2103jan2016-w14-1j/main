package storage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class StorageReader {
	
	public static String StorageReader() throws IOException, JDOMException{
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("todoList.xml");
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement(); //rootnode is a tasklist
		
		List list = rootNode.getChildren("tasks"); // every single children is a task
		
		for(int i=0; i<list.size(); i++){
			
			Element node = (Element) list.get(i);
			
			System.out.println(node.getChildText("task"));
			System.out.println(node.getChildText("attribute"));
			System.out.println(node.getChildText("event"));
			System.out.println(node.getChildText("date"));
			System.out.println(node.getChildText("time"));
		
		}
		
	}
}
