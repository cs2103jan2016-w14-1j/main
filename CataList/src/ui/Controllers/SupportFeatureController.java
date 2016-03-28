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
public class SupportFeatureController {
	
	private static final String HELP_PAGE_PATH = "/ui/View/HelpPage.fxml";
	private static final String ICON_PATH = "/ui/Application/Stylesheets/Background/time-icon.png";
	private static final String CALENDAR_HEADING = "   SCHEDULE";
	private static final String CALENDAR_ID = "calendarContainer";
	private static final String TIME_ID = "calendarTime";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final int TIME_LABEL_INDEX = 1;

	private MainGUIController main;
	private TutorialRenderer tutorialRenderer;
	private Node calendar;
	private Label heading;
	private Label time;

	@FXML 
	private Text titleMessage;
	@FXML 
	private Text subMessage;
	@FXML 
	private VBox mainPane;

	public void init(MainGUIController mainController) {
		main = mainController;
		tutorialRenderer = new TutorialRenderer(main);
		
		if(main.getTaskList().size() <= 1) {
			showMainPane();
		}
	}

	public VBox getMainPane() {
		return mainPane;
	}
	
	public void renderTutorial() throws IOException {
		tutorialRenderer.loadTutorial();
	}
	
	public void loadHelpList() throws IOException {
		main.todoListController.closeToDoList();
		showMainPane();
		mainPane.getChildren().clear();
		try {
			mainPane.getChildren().add(FXMLLoader.load(getClass().getResource(HELP_PAGE_PATH)));
		} catch (IOException ioe)  {

		}
	}

	public void loadCalendar() {
		VBox calendarContainer = new VBox(10);

		main.todoListController.closeToDoList();
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

	private void loopCheckTime(VBox calendarContainer) {
		Timer checkTasks = new Timer(true);
		checkTasks.schedule(new TimerTask() {
			@Override
			public void run() {  
				Platform.runLater(new Runnable() {
					public void run() {
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
				});

			}
		}, 0, 1000);
	}

	private String setTimeProperties() {
		return new LocalTime().toString(TIME_FORMAT);
	}

	private Label setHeadingProperties(ImageView imageView) {
		Label label = new Label(CALENDAR_HEADING);
		label.setTextFill(Color.BLACK);
		label.setGraphic(imageView);
		label.setFont(Font.font(20));
		return label;
	}

	private ImageView setImageViewProperties() {
		Image image = new Image(getClass().getResourceAsStream(ICON_PATH));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(40);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		return imageView;
	}

	public void removeMainPane() {
		mainPane.setManaged(false);

		FadeTransition ft = new FadeTransition(Duration.millis(200), mainPane);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}

	public void showMainPane() {
		mainPane.setManaged(true);
		insertTutorialToggle();

		FadeTransition ft = new FadeTransition(Duration.millis(200), mainPane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	private void insertTutorialToggle() {
		Text tutorialLabel = new Text("Tutorial Mode: " + main.getTutorialMode() + ", SHIFT+RIGHT to toggle");
		if(mainPane.getChildren().size() > 3) {
			mainPane.getChildren().set(mainPane.getChildren().size()-1, tutorialLabel);
		} else {
			mainPane.getChildren().add(tutorialLabel);
		}
	}
}
