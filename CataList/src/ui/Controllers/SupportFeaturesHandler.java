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
	
	/**
	 * Constructor method
	 * @param mainController The primary controller linking this and the other controllers
	 */
	public SupportFeaturesHandler(MainGUIController mainController) {
		main = mainController;
		feedbackFromLogic = NULL_REGEX;
	}
	
	/**
	 * Matches the feedback from Logic to one of the features
	 * @param feedback This is the main feedback from commandListInterface
	 * @return true if the feedback matches that of the feature, false if nothing is loaded
	 * @throws IOException If there is an I/O Error
	 */
	public boolean isSupportFeaturesLoaded(Text feedback) throws IOException {
		feedbackFromLogic = feedback.getText();
		
		switch(feedbackFromLogic) {
			case MESSAGE_TUTORIAL_DEFAULT:
				loadTutorial();
				return true;
			case MESSAGE_HELP_SUCCESS:
				main.renderHelpPage();
				return true;
			case MESSAGE_CALENDAR_DEFAULT:
				main.renderCalendar();
				return true;
			case MESSAGE_SAVETO_SUCCESS:
				loadSaveWindow(feedback);
				return true;
			default:
				return false;
		}
	}

	private void loadTutorial() throws IOException {
		if(main.getMainPane().isManaged() == false) {
			main.startTutorialMode();
		}
	}

	private void loadSaveWindow(Text feedbackMain) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(SAVE_WINDOW_HEADING);
		File file = fileChooser.showSaveDialog(new Stage());
		setUserInputAsFilePath(file, feedbackMain);
	}

	private void setUserInputAsFilePath(File file, Text feedbackMain) {
		if (file != null) {
			setCommandLineFeedbackAfterSave(passAbsolutePathToLogic(file), feedbackMain);
		}
	}

	private String passAbsolutePathToLogic(File file) {
		return main.passInputToLogic(SAVE_REGEX + file.getAbsolutePath());
	}

	private void setCommandLineFeedbackAfterSave(String saveFeedback, Text feedbackMain) {
		feedbackMain.setText(saveFeedback);
	}
}
