package Controllers;

import java.io.IOException;

import org.jdom2.JDOMException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.LogicHandler;

public class MainGUIController {
	
	private final String INIT_LIST = "display";
	
    @FXML 
    CommandLineController commandLineController;  
    @FXML 
    ListInterfaceController todoListController;   
    @FXML 
    ClassController classListController;
    @FXML 
    TitleInterfaceController titleController;
    
    @FXML 
    AnchorPane mainAnchorPane;
    @FXML 
    Text titleMessage;
    @FXML 
    Text subMessage;
    @FXML 
    VBox welcomeMessage;
    
    public void initialize() throws IOException, JDOMException {
       commandLineController.init(this);
       classListController.init(this);
       titleController.init(this);
       todoListController.init(this);
    }
        
    public void removeWelcomeMsg() {
		welcomeMessage.setManaged(false);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), welcomeMessage);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
}