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
    public SupportFeatureController supportFeatureController;
    
    @FXML 
    public AnchorPane mainAnchorPane;
    
    
    
    //TODO: refactor MainGUI - need more OO concepts
    //TODO: complete the extraction of support feature
    public void initialize() throws IOException, JDOMException {
       commandLineController.init(this);
       classListController.init(this);
       titleController.init(this);
       todoListController.init(this);
       supportFeatureController.init(this);
    }
    
    public void refreshClassList() {
    	classListController.loopClassList();
    }
    
    public void clearCompleted() {
    	classListController.clearCompletedClassList();
    }
    
    public void loadCompleted() {
    	classListController.initCompletedClassList();
    }
    
    public void refreshToDoList() throws IOException, JDOMException {
    	todoListController.loopTaskList();
    }
    
    public void removeMainPane() {
    	supportFeatureController.removeMainPane();
    }
    
    public void openMainPane() {
    	supportFeatureController.showMainPane();
    }
    
    public boolean isClassEmpty() {
    	return classListController.classes.isEmpty();
    }
    
    public boolean isToDoListEmpty() {
    	return todoListController.tasks.isEmpty();
    }
    
    public boolean isCompletedEmpty() {
    	return todoListController.completed.isEmpty();
    }
    
    public boolean isMainPaneManaged() {
    	return supportFeatureController.welcomeMessage.isManaged();
    }
    
    
}