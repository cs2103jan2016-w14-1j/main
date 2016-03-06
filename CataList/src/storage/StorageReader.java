

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class StorageReader {
	
	private static final String ELEMENT_TASK = "Task";
	private static final String STORAGE_PATH = 
			System.getProperty("user.dir") + 
            "/CataList/src/storage/test.xml";
	
	private static final String ELEMENT_TIME = "Time";
	private static final String ELEMENT_DATE = "Date";
	private static final String ELEMENT_EVENT = "Event";
	private static final String ATTRIBUTE_NUM = "ID";
	
	public static void readStorage() throws IOException, JDOMException{
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(STORAGE_PATH);
		
		Document todoListDocument = (Document) builder.build(xmlFile);
		Element rootNode = todoListDocument.getRootElement(); //rootnode is a tasklist
		
		List<Element> list = rootNode.getChildren(); // every single children is a task
		
		for(int i=0; i<list.size(); i++) {
			
			Element node = (Element) list.get(i);
			
			System.out.println(node.getAttributeValue(ATTRIBUTE_NUM));
			System.out.println(node.getChildText(ELEMENT_EVENT));
			System.out.println(node.getChildText(ELEMENT_DATE));
			System.out.println(node.getChildText(ELEMENT_TIME));
		
		}
		
	}
}
