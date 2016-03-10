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
    public CommandLineController commandLineController;  
    @FXML 
    public ListInterfaceController todoListController;   
    @FXML 
    public ClassController classListController;
    @FXML 
    public TitleInterfaceController titleController;
    
    @FXML 
    public AnchorPane mainAnchorPane;
    @FXML 
    public Text titleMessage;
    @FXML 
    public Text subMessage;
    @FXML 
    public VBox welcomeMessage;
    
    public void initialize() throws IOException, JDOMException {
       commandLineController.init(this);
       classListController.init(this);
       titleController.init(this);
       todoListController.init(this);
    }
        
    public void removeWelcomeMsg() {
		welcomeMessage.setManaged(false);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), welcomeMessage);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}
    
    public void showMainPane() {
    	welcomeMessage.setManaged(true);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), welcomeMessage);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
    }
}