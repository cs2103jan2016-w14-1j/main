package Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
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
    
    public void initialize() {
       commandLineController.init(this);
       todoListController.init(this);
       classListController.init(this);
       titleController.init(this);
    }
    
    public String loadStringFromCommandLine() {
        return commandLineController.command;
    }
}