package Controllers;

import javafx.fxml.FXML;
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
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event 
     */
    public void initialize() {
        //System.out.println(this);
        //System.out.println();
       commandLineController.init(this);
       todoListController.init(this);
       classListController.init(this);
       titleController.init(this);
    }
    
    public String loadStringFromCommandLine() {
        return commandLineController.command;
    }
}