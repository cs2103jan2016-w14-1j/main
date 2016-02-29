package Controllers.HelpCommands;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class HelpCommandStorageParser {
    
    private static final String ID = "id";
    private static final String COMMAND = "command";
    private static final String HELP_FILE_PATH = 
            System.getProperty("user.dir") + 
            "/Controllers/HelpCommands/HelpCommandStorage.xml";
    
    private static ArrayList<ArrayList<String>> result = new ArrayList<>();
    
    public static ArrayList<ArrayList<String>> parseHelpList() {
        
      try {
         File inputFile = new File(HELP_FILE_PATH);
         
         SAXBuilder saxBuilder = new SAXBuilder();
         Document document = saxBuilder.build(inputFile);
         Element helpElement = document.getRootElement();
         List<Element> helpList = helpElement.getChildren();
         
         accessStorage(helpList);
      }catch(JDOMException | IOException e){
      }
      
      return result;
   }

    private static void accessStorage(List<Element> helpList) {
        for (int i = 0; i < helpList.size(); i++) {
            result.add(new ArrayList<>());
           
            Element helpCmd = helpList.get(i);
            
            result.get(i).add(helpCmd.getChild(ID).getText());
            result.get(i).add(helpCmd.getChild(COMMAND).getText());
        }
    }
}
