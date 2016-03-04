package Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainGUIController {
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
    
    public void initialize() {
       commandLineController.init(this);
       todoListController.init(this);
       classListController.init(this);
       titleController.init(this);
    }
        
    public void removeWelcomeMsg() {
		welcomeMessage.setManaged(false);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), welcomeMessage);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
}