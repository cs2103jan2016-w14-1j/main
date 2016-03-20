package ui.Controllers;

import java.io.IOException;
import java.time.LocalDate;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private Node calendar;

	@FXML 
	private Text titleMessage;
	@FXML 
	private Text subMessage;
	@FXML 
	private VBox mainPane;

	public void init(MainGUIController mainController) {
		main = mainController;
		if(main.isToDoListEmpty()) {
			showMainPane();
		}
	}

	public VBox getMainPane() {
		return mainPane;
	}

	public void loadTutorial() {
		PopOver commandLineTutorial = new PopOver();
		PopOver listTutorial = new PopOver();
		PopOver feedbackTutorial = new PopOver();
		tutorialKeyHandler(commandLineTutorial, listTutorial, commandLineTutorial, main.getList(), main.getCommandLine());
		tutorialKeyHandler(listTutorial, feedbackTutorial, commandLineTutorial, main.getFeedback(), main.getCommandLine());
		tutorialKeyHandler(feedbackTutorial, feedbackTutorial, listTutorial, main.getFeedback(), main.getList());
		
		commandLineTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		listTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		feedbackTutorial.setArrowLocation(ArrowLocation.RIGHT_BOTTOM);
		
		initTutorial(commandLineTutorial, main.getCommandLine());
	}

	private void initTutorial(PopOver target, Node targetContent) {
		final Scene scene = targetContent.getScene();
		final Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
		final Point2D sceneCoord = new Point2D(scene.getX(), scene.getY());
		final Point2D nodeCoord = targetContent.localToScene(0.0, 0.0);
		final double clickX = Math.round(windowCoord.getX() + sceneCoord.getX() + nodeCoord.getX());
		final double clickY = Math.round(windowCoord.getY() + sceneCoord.getY() + nodeCoord.getY());
		target.show(targetContent, clickX+500, clickY);
	}

	private void tutorialKeyHandler(PopOver start, PopOver next, PopOver previous, Node nextNode, Node prevNode) {
		start.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
		() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.RIGHT) {
					if(start != next) {
						start.hide();
					}
					initTutorial(next, nextNode);
				} else if (event.getCode() == KeyCode.LEFT) {
					if(start != previous) {
						start.hide();
					}
					initTutorial(previous, prevNode);
				}
			}
		});
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

		FadeTransition ft = new FadeTransition(Duration.millis(400), mainPane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
}
