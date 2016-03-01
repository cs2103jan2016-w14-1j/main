package Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.util.Duration;
import Controllers.CommandLineController;
import Controllers.ListInterfaceController;
import Controllers.TitleInterfaceController;


import Controllers.ClassController;

/**
 *
 * @author ericewe
 */
public class MainGUIController {
    @FXML CommandLineController commandLineController;
    @FXML ListInterfaceController todoListController;
    @FXML ClassController classListController;
    @FXML TitleInterfaceController titleController;
    
    @FXML AnchorPane mainAnchorPane;
    @FXML Label titleMessage;
    @FXML Label subMessage;
    @FXML VBox welcomeMessage;
    
    public void initialize() {
       commandLineController.init(this);
       todoListController.init(this);
       classListController.init(this);
       titleController.init(this);
    }
    
    public String loadStringFromCommandLine() {
        return commandLineController.command;
    }
    
    public void removeWelcomeMsg() {
		welcomeMessage.setManaged(false);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), welcomeMessage);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
	}
}