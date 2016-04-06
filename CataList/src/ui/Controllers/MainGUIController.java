//@@author A01122204E
package ui.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import logic.LogicMain;
import logic.Task;

public class MainGUIController {

	@FXML public CommandLineController commandLineController;  
	@FXML public ListInterfaceController todoListController;   
	@FXML public TitleInterfaceController titleController;
	@FXML public SupportFeatureController supportFeatureController;
	@FXML public AnchorPane backgroundPane;

	private LogicMain logic = new LogicMain();

	public void initialize() throws IOException, JDOMException {
		commandLineController.init(this);
		todoListController.init(this);
		titleController.init(this);
		supportFeatureController.init(this);
	}
	
	public AnchorPane getBackgroundPane() {
		return backgroundPane;
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
	
	public boolean isToDoListEmpty() {
		return todoListController.getTasks().isEmpty();
	}
	
	public ObservableList<HBox> getTaskList() {
		return todoListController.getTasks();
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

	public ArrayList<Task> getOperatingTasksFromLogic() {
		return logic.getOperatingTasksForUI();
	}
	
	public ArrayList<Task> getCompletedTasksFromLogic() {
		return logic.getCompleteTasksForUI();
	}
	
	public ArrayList<Task> getPendingTasksFromLogic() {
		return logic.getIncompleteTasksForUI();
	}

	public TabPane getTabPane() {
		return todoListController.getTabPane();
	}
	
	public TextField getCommandLine() {
		return commandLineController.getCommandLine();
	}
	
	public ListView<HBox> getList() {
		return todoListController.getList();
	}
	
	public String getTutorialMode() {
		return commandLineController.getTutorialToggle();
	}

}