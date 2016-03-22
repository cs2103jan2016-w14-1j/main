package ui.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.LogicMain;
import logic.Task;

public class MainGUIController {

	@FXML public CommandLineController commandLineController;  
	@FXML public ListInterfaceController todoListController;   
	@FXML public TitleInterfaceController titleController;
	@FXML public SupportFeatureController supportFeatureController;
	@FXML public AnchorPane mainAnchorPane;

	private LogicMain logic = new LogicMain();

	public void initialize() throws IOException, JDOMException {
		todoListController.init(this);
		commandLineController.init(this);
		titleController.init(this);
		supportFeatureController.init(this);
	}
	/*
    public void refreshClassList() {
    	classListController.loopClassList();
    }

    public void clearCompleted() {
    	classListController.clearCompletedClassList();
    }

    public void loadCompleted() {
    	classListController.initCompletedClassList();
    }
	 */ 
	
	public void refreshToDoList() throws IOException, JDOMException {
		todoListController.loopTaskList();
	}

	public void removeMainPane() {
		supportFeatureController.removeMainPane();
	}

	public void openMainPane() {
		supportFeatureController.showMainPane();
	}
	/*
    public boolean isClassEmpty() {
    	return classListController.getClasses().isEmpty();
    }
	 */
	public boolean isToDoListEmpty() {
		return todoListController.getTasks().isEmpty();
	}

	public boolean isCompletedEmpty() {
		return todoListController.getCompleted().isEmpty();
	}

	public boolean isMainPaneManaged() {
		return supportFeatureController.getMainPane().isManaged();
	}

	public LogicMain getLogic() {
		return logic;
	}

	public String passInputToLogic(String input) {
		return logic.processCommand(input);
	}

	public ArrayList<Task> getFromLogic() {
		return logic.getOperatingTasksForUI();
	}

	public TabPane getTabPane() {
		return todoListController.getTabPane();
	}
	
	public TextField getCommandLine() {
		return commandLineController.getCommandLine();
	}
	
	public Text getFeedback() {
		return commandLineController.getFeedback();
	}
	
	public ListView<HBox> getList() {
		return todoListController.getList();
	}
}