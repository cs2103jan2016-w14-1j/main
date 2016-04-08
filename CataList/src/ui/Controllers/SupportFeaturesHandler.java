package ui.Controllers;

import java.io.File;
import java.io.IOException;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SupportFeaturesHandler {
	private static final String SAVE_WINDOW_HEADING = "Save Task List";
	
	private static final String MESSAGE_TUTORIAL_DEFAULT = "Displaying Tutorial";
	private static final String MESSAGE_HELP_SUCCESS = "Displaying Help";
	private static final String MESSAGE_CALENDAR_DEFAULT = "Displaying Calendar";
	private static final String MESSAGE_SAVETO_SUCCESS = "Displaying file explorer";
	
	private static final String NULL_REGEX = "";
	private static final String SAVE_REGEX = "save ";
	
	private String feedbackFromLogic;
	private MainGUIController main;
	
	public SupportFeaturesHandler(MainGUIController mainController) {
		main = mainController;
		feedbackFromLogic = NULL_REGEX;
	}
	
	public boolean isSupportFeaturesLoaded(Text feedbackMain) throws IOException {
		feedbackFromLogic = feedbackMain.getText();
		
		switch(feedbackFromLogic) {
			case MESSAGE_TUTORIAL_DEFAULT:
				loadTutorial();
				return true;
			case MESSAGE_HELP_SUCCESS:
				main.supportFeaturesController.loadHelpList();
				return true;
			case MESSAGE_CALENDAR_DEFAULT:
				main.supportFeaturesController.loadCalendar();
				return true;
			case MESSAGE_SAVETO_SUCCESS:
				loadSaveWindow();
				return true;
			default:
				return false;
		}
	}

	private void loadTutorial() throws IOException {
		if(main.supportFeaturesController.getMainPane().isManaged() == false) {
			main.supportFeaturesController.renderTutorial();
		}
	}

	private void loadSaveWindow() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(SAVE_WINDOW_HEADING);
		File file = fileChooser.showSaveDialog(new Stage());
		setUserInputAsFilePath(file);
	}

	private void setUserInputAsFilePath(File file) {
		if (file != null) {
			main.commandLineController.getMainFeedback().setText(main.commandLineController.uiToLogic(SAVE_REGEX + file.getAbsolutePath()));
		}
	}
}
