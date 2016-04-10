//@@author A0112204E
package ui.Controllers;

import java.io.IOException;
import java.util.ArrayList;

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
	
	/**
	 * Constructor for MainGUIController
	 * @throws IOException If an I/O Error occurs
	 */
	public void initialize() throws IOException {
		commandLineController.init(this);
		todoListController.init(this);
		titleController.init(this);
		supportFeaturesController.init(this);
	}
	
	/**
	 * Gets the background for the application
	 * @return AnchorPane background for application
	 */
	public AnchorPane getBackgroundPane() {
		return backgroundPane;
	}
	
	/**
	 * Gets main feedback from commandLineController
	 * @return Text main feedback
	 */
	public Text getMainFeedback() {
		return commandLineController.getMainFeedback();
	}
	
	/**
	 * Gets secondary feedback from commandLineController
	 * @return Text secondary feedback
	 */
	public Text getHelpFeedback() {
		return commandLineController.getHelpFeedback();
	}
	
	/**
	 * Gets tutorial flag in string from commandLineController
	 * @return String Flag for indication of whether tutorial is ON or OFF
	 */
	public String getTutorialMode() {
		return commandLineController.getTutorialToggle();
	}
	
	/**
	 * Gets user input field from commandLineController
	 * @return TextField This is the user input field
	 */
	public TextField getCommandLine() {
		return commandLineController.getCommandLine();
	}
	
	/**
	 * Refreshes the to-do list
	 * @throws IOException
	 */
	public void refreshToDoList() throws IOException {
		todoListController.loopTaskList();
	}
	
	/**
	 * Remove to-do list from view
	 */
	public void removeToDoList() {
		todoListController.closeToDoList();
	}
	
	/**
	 * Loads to-do list into view
	 */
	public void openToDoList() {
		todoListController.openToDoList();
	}
	
	/**
	 * Gets the list of incomplete tasks from todoListController
	 * @return ObservableList<HBox> List of incomplete task in HBox
	 */
	public ObservableList<HBox> getTaskList() {
		return todoListController.getTasks();
	}
	
	/**
	 * Gets the list of completed tasks from todoListController
	 * @return ObservableList<HBox> List of completed Tasks in HBox
	 */
	public ObservableList<HBox> getCompletedList() {
		return todoListController.getCompleted();
	}
	
	/**
	 * Gets tab pane from todoListController
	 * @return TabPane The tab pane containing filter tabs
	 */
	public TabPane getTabPane() {
		return todoListController.getTabPane();
	}
	
	/**
	 * Gets the entire list pane from todoListController
	 * @return ListView<HBox> The list view pane
	 */
	public ListView<HBox> getList() {
		return todoListController.getList();
	}

	/**
	 * Gets the center pane that holds supporting features
	 * from supportFeaturesController
	 * @return VBox This is the center pane for several features
	 */
	public VBox getMainPane() {
		return supportFeaturesController.getMainPane();
	}
	
	/**
	 * Loads the center pane into view
	 */
	public void openMainPane() {
		supportFeaturesController.showMainPane();
	}
	
	/**
	 * Removes the center main from view
	 */
	public void removeMainPane() {
		supportFeaturesController.removeMainPane();
	}
	
	/**
	 * Starts tutorial
	 * @throws IOException If an I/O Error occurs
	 */
	public void startTutorialMode() throws IOException {
		supportFeaturesController.renderTutorial();
	}
	
	/**
	 * Displays help page
	 * @throws IOException If an I/O Error occurs
	 */
	public void renderHelpPage() throws IOException {
		supportFeaturesController.loadHelpList();
	}
	
	/**
	 * Displays calendar
	 * @throws IOException If an I/O Error occurs
	 */
	public void renderCalendar() throws IOException {
		supportFeaturesController.loadCalendar();
	}
	
	/**
	 * Gets an instance of Logic
	 * @return LogicMain This is an instance of LogicMain object
	 */
	public LogicMain getLogic() {
		return logic;
	}
	
	/**
	 * Passes the user input to Logic
	 * @param input This is the user input
	 * @return String This is the feedback from Logic
	 */
	public String passInputToLogic(String input) {
		assert (this != null);
		return logic.processCommand(input);
	}
	
	/**
	 * Gets list of incomplete tasks from Logic
	 * @return ArrayList<Task> List of incomplete TaskObjects
	 */
	public ArrayList<Task> getIncompleteTasksFromLogic() {
		return logic.getOperatingTasksForUI();
	}
	
	/**
	 * Gets list of completed tasks from Logic
	 * @return ArrayList<Task> List of completed TaskObjects
	 */
	public ArrayList<Task> getCompletedTasksFromLogic() {
		return logic.getCompleteTasksForUI();
	}
	
	/**
	 * Sets operating list to display complete tasks
	 */
	public void setOperatingListAsComplete() {
		logic.operatingToComplete();
	}
	
	/**
	 * Sets operating list to display incomplete tasks
	 */
	public void setOperatingListAsIncomplete() {
		logic.operatingToIncomplete();
	}
}