//@@author A0112204E
package ui.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.LocalTime;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class SupportFeaturesController {
	
	/**
	 * The SupportFeaturesController controls a hidden center pane that will display various supporting
	 * features for the application
	 * Features included are calendar, help page, tutorial and the main title
	 * It sets the properties for all the features and renders them
	 * 
	 */
	
	private static final int TIME_CHECK_INTERVAL = 1000;
	private static final int CALENDAR_SPACING = 10;
	private static final int HEADING_SIZE = 20;
	private static final int IMAGE_SIZE = 40;
	private static final int MAIN_PANE_ANIMATION_DURATION = 200;
	
	private static final String HELP_PAGE_PATH = "/ui/View/HelpPage.fxml";
	private static final String ICON_PATH = "/ui/Application/Stylesheets/Background/time-icon.png";
	private static final String CALENDAR_HEADING = "   SCHEDULE";
	private static final String CALENDAR_ID = "calendarContainer";
	private static final String TIME_ID = "calendarTime";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final int TIME_LABEL_INDEX = 1;
	
	private static final String TUTORIAL_NOTIFICATION = "Tutorial Mode: %1s, SHIFT+RIGHT to toggle";
	
	private MainGUIController main;
	private TutorialRenderer tutorialRenderer;
	private Node calendar;
	private Label heading;
	private Label time;

	@FXML private Text titleMessage;
	@FXML private Text subMessage;
	@FXML private VBox mainPane;

	/**
	 * Constructor method
	 * @param mainController The primary controller linking this and the other controllers
	 */
	public void init(MainGUIController mainController) {
		main = mainController;
		tutorialRenderer = new TutorialRenderer(main);
		
		if(main.getTaskList().size() <= 1) {
			showMainPane();
		}
	}

	/**
	 * Gets the center pane that holds supporting features
	 * from supportFeaturesController
	 * @return VBox This is the center pane for several features
	 */
	public VBox getMainPane() {
		return mainPane;
	}
	
	/**
	 * Starts tutorial and display it
	 * @throws IOException If an I/O Error occurs
	 */
	public void renderTutorial() {
		tutorialRenderer.loadTutorial();
	}
	
	/**
	 * Displays help page
	 * @throws IOException If an I/O Error occurs
	 */
	public void loadHelpList() {
		main.removeToDoList();
		showMainPane();
		mainPane.getChildren().clear();
		try {
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource(HELP_PAGE_PATH)));
		} catch (IOException e)  {
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays calendar
	 * @throws IOException If an I/O Error occurs
	 */
	public void loadCalendar() {
		VBox calendarContainer = new VBox(CALENDAR_SPACING);

		main.removeToDoList();
		setCalendarProperties(calendarContainer);
		showMainPane();
		insertCalendarIntoContainer(calendarContainer);
	}

	private void insertCalendarIntoContainer(VBox calendarContainer) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(calendarContainer);
	}

	private void setCalendarProperties(VBox calendarContainer) {
		calendar = new DatePickerSkin(new DatePicker(LocalDate.now())).getPopupContent();
		loopCheckTime(calendarContainer);
	}
	
	/**
	 * Checks and changes the time every second to create a functioning clock
	 * @param calendarContainer This is the pane containing the entire calendar
	 */
	private void loopCheckTime(VBox calendarContainer) {
		Timer checkTasks = new Timer(true);
		checkTasks.schedule(new TimerTask() {
			@Override
			public void run() {  
				Platform.runLater(new Runnable() {
					public void run() {
						setCalendarIntoPlace(calendarContainer);
					}
				});

			}
		}, 0, TIME_CHECK_INTERVAL);
	}

	private String setTimeProperties() {
		return new LocalTime().toString(TIME_FORMAT);
	}

	private Label setHeadingProperties(ImageView imageView) {
		Label label = new Label(CALENDAR_HEADING);
		label.setTextFill(Color.BLACK);
		label.setGraphic(imageView);
		label.setFont(Font.font(HEADING_SIZE));
		return label;
	}

	private ImageView setImageViewProperties() {
		Image image = new Image(getClass().getResourceAsStream(ICON_PATH));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(IMAGE_SIZE);
		imageView.setFitWidth(IMAGE_SIZE);
		imageView.setPreserveRatio(true);
		return imageView;
	}

	/**
	 * Removes the center main from view
	 */
	public void removeMainPane() {
		mainPane.setManaged(false);

		FadeTransition ft = new FadeTransition(Duration.millis(MAIN_PANE_ANIMATION_DURATION), mainPane);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}

	/**
	 * Loads the center pane into view
	 */
	public void showMainPane() {
		mainPane.setManaged(true);
		insertTutorialToggle();

		FadeTransition ft = new FadeTransition(Duration.millis(MAIN_PANE_ANIMATION_DURATION), mainPane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	private void insertTutorialToggle() {
		Text tutorialLabel = new Text(String.format(TUTORIAL_NOTIFICATION, main.getTutorialMode()));
		if(mainPane.getChildren().size() > 3) {
			mainPane.getChildren().set(mainPane.getChildren().size()-1, tutorialLabel);
		} else {
			mainPane.getChildren().add(tutorialLabel);
		}
	}

	private void setCalendarIntoPlace(VBox calendarContainer) {
		ImageView imageView = setImageViewProperties();
		heading = setHeadingProperties(imageView);
		time = new Label(setTimeProperties());
		time.setId(TIME_ID);
		calendarContainer.setId(CALENDAR_ID);
		if(calendarContainer.getChildren().isEmpty()) {
			calendarContainer.getChildren().addAll(heading, time, calendar);
		} else {
			calendarContainer.getChildren().set(TIME_LABEL_INDEX, time);
		}
	}

}
