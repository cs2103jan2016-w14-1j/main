package ui.Controllers;

import java.io.IOException;
import java.time.LocalDate;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.animation.FadeTransition;
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
	
	private final String HELP_PAGE_PATH = "/ui/View/HelpPage.fxml";
	private final String ICON_PATH = "/ui/Application/Stylesheets/Background/time-icon.png";
	private final String CALENDAR_HEADING = "   Schedule";
	private final String CALENDAR_ID = "calendarContainer";

	private MainGUIController main;
	private TutorialRenderer tutorialRenderer;
	private Node calendar;

	@FXML 
	private Text titleMessage;
	@FXML 
	private Text subMessage;
	@FXML 
	private VBox mainPane;

	public void init(MainGUIController mainController) {
		main = mainController;
		tutorialRenderer = new TutorialRenderer(main);
		
		if(main.isToDoListEmpty()) {
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
		ImageView imageView = setImageViewProperties();
		Label label = setLabelProperties(imageView);
		calendarContainer.setId(CALENDAR_ID);
		calendar = new DatePickerSkin(new DatePicker(LocalDate.now())).getPopupContent();
		calendarContainer.getChildren().addAll(label, calendar);
	}

	private Label setLabelProperties(ImageView imageView) {
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

		FadeTransition ft = new FadeTransition(Duration.millis(400), mainPane);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}

	public void showMainPane() {
		mainPane.setManaged(true);
		insertTutorialToggle();

		FadeTransition ft = new FadeTransition(Duration.millis(400), mainPane);
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
