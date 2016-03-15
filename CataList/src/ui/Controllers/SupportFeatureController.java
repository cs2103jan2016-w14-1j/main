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

public class SupportFeatureController {
	
	private final String HELP_PAGE_PATH = "/View/HelpPage.fxml";
	private final String ICON_PATH = "/Application/Stylesheets/Background/time-icon.png";
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
        showMainPane();
    }
	
	public VBox getMainPane() {
		return mainPane;
	}
	
	public void loadHelpList() throws IOException {
		main.todoListController.closeToDoList();
		main.classListController.closeClassList();
		showMainPane();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(FXMLLoader.load(getClass().getResource(HELP_PAGE_PATH)));
	}
	
	public void loadCalendar() {
    	main.todoListController.closeToDoList();
		main.classListController.closeClassList();
		VBox calendarContainer = new VBox(10);
		Label label = new Label(CALENDAR_HEADING);
		label.setTextFill(Color.BLACK);
		Image image = new Image(getClass().getResourceAsStream(ICON_PATH));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(40);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		label.setGraphic(imageView);
		label.setFont(Font.font(20));
		calendarContainer.setId(CALENDAR_ID);
		calendar = new DatePickerSkin(new DatePicker(LocalDate.now())).getPopupContent();
		calendarContainer.getChildren().addAll(label, calendar);
		showMainPane();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(calendarContainer);
    }
	
	public void removeMainPane() {
		mainPane.setManaged(false);
		
		FadeTransition ft = new FadeTransition(Duration.millis(400), mainPane);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
	}
    
    public void showMainPane() {
    	if(main.isToDoListEmpty()) {
    		mainPane.setManaged(true);

    		FadeTransition ft = new FadeTransition(Duration.millis(400), mainPane);
    		ft.setFromValue(0);
    		ft.setToValue(1);
    		ft.play();
    	}
    }
}
