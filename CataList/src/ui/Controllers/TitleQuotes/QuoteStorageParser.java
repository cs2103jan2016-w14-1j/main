package ui.Controllers.TitleQuotes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class QuoteStorageParser {
    
    private static final String INITIALIZE = "Good Day!";
    private static final String PHRASE = "phrase";
    private static final String PERSON = "person";
    private static final String QUOTE_CONJUNCTION = " -- ";
    private static final String QUOTE_FILE_PATH = 
           System.getProperty("user.dir") + 
           "/src/ui/Controllers/TitleQuotes/QuoteStorage.xml";
    
    public static String parseQuoteList(int i) {
        
      String result = INITIALIZE;
      
      try {
         File inputFile = new File(QUOTE_FILE_PATH);
         if (!inputFile.exists()) {
             return result;
         }
         
         SAXBuilder saxBuilder = new SAXBuilder();
         Document document = saxBuilder.build(inputFile);
         Element quoteElement = document.getRootElement();
         List<Element> quoteList = quoteElement.getChildren();
         
         result = accessStorage(quoteList, i, result);
      }catch(JDOMException | IOException e){
      }
      
      
      return result;
   }

    private static String accessStorage(List<Element> quoteList, int i, String result) {
        for (int j = 0; j < quoteList.size(); j++) {
            Element quote = quoteList.get(j);
            result = getStorageItem(j, i, result, quote);
        }
        return result;
    }

    private static String getStorageItem(int temp, int i, String result, Element quote) {
        if(temp ==  i) {
            result = quote.getChild(PHRASE).getText() + QUOTE_CONJUNCTION
                    + quote.getChild(PERSON).getText();
        }
        return result;
    }
}
