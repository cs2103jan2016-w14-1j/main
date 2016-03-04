package Controllers;

import java.lang.reflect.Field;

import org.w3c.dom.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DocListener extends CalendarGenerator implements ChangeListener<Document>{  
    
	@Override
    public void changed(ObservableValue<? extends Document> observable, Document oldValue, Document newValue) {
        try { 
            Field f = we.getClass().getDeclaredField("page"); 
            f.setAccessible(true); 
            com.sun.webkit.WebPage page = (com.sun.webkit.WebPage) f.get(we);  
            page.setBackgroundColor((new java.awt.Color(0, 0, 0, 0)).getRGB()); 

        } catch (Exception e) {
        }
    }
}  