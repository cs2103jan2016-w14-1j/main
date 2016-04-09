//@@author A0112204E
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.LogicMain;
import logic.Task;

public class MainGUIController {

	@FXML private CommandLineController commandLineController;  
	@FXML private ListInterfaceController todoListController;   
	@FXML private TitleInterfaceController titleController;
	@FXML private SupportFeaturesController supportFeaturesController;
	@FXML private AnchorPane backgroundPane;

	private LogicMain logic = new LogicMain();

	public void initialize() throws IOException, JDOMException {
		commandLineController.init(this);
		todoListController.init(this);
		titleController.init(this);
		supportFeaturesController.init(this);
	}
	
	public AnchorPane getBackgroundPane() {
		return backgroundPane;
	}
	
	/*
	 * commandLineController API
	 */
	public Text getMainFeedback() {
		return commandLineController.getMainFeedback();
	}
	
	public Text getHelpFeedback() {
		return commandLineController.getMainFeedback();
	}
	
	public String getTutorialMode() {
		return commandLineController.getTutorialToggle();
	}
	
	public TextField getCommandLine() {
		return commandLineController.getCommandLine();
	}
	
	/*
	 * toDoListController API
	 */
	public void refreshToDoList() throws IOException {
		todoListController.loopTaskList();
	}
	
	public void removeToDoList() {
		todoListController.closeToDoList();
	}
	
	public void openToDoList() {
		todoListController.openToDoList();
	}
	
	public ObservableList<HBox> getTaskList() {
		return todoListController.getTasks();
	}

	public ObservableList<HBox> getCompletedList() {
		return todoListController.getCompleted();
	}
	
	public TabPane getTabPane() {
		return todoListController.getTabPane();
	}
	
	public ListView<HBox> getList() {
		return todoListController.getList();
	}

	/*
	 * supportFeatureController API
	 */
	public VBox getMainPane() {
		return supportFeaturesController.getMainPane();
	}
	
	public void openMainPane() {
		supportFeaturesController.showMainPane();
	}
	
	public void removeMainPane() {
		supportFeaturesController.removeMainPane();
	}
	
	public void startTutorialMode() throws IOException {
		supportFeaturesController.renderTutorial();
	}
	
	public void renderHelpPage() throws IOException {
		supportFeaturesController.loadHelpList();
	}
	
	public void renderCalendar() throws IOException {
		supportFeaturesController.loadCalendar();
	}
	
	/*
	 * Logic API
	 */
	public LogicMain getLogic() {
		return logic;
	}
	
	public String passInputToLogic(String input) {
		return logic.processCommand(input);
	}

	public ArrayList<Task> getIncompleteTasksFromLogic() {
		logic.operatingToIncomplete();
		return logic.getOperatingTasksForUI();
	}
	
	public ArrayList<Task> getCompletedTasksFromLogic() {
		logic.operatingToComplete();
		return logic.getCompleteTasksForUI();
	}
}