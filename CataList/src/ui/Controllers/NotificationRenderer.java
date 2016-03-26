package ui.Controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

public class NotificationRenderer {

	private static final int TIME_FLAG = 1;
	private static final int DAY_FLAG = 0;

	public void loadNotification(int todo, int flag) {
		Stage notificationOwner = new Stage(StageStyle.TRANSPARENT);
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: TRANSPARENT");
		Scene scene = new Scene(root, 1, 1);
		scene.setFill(Color.TRANSPARENT);
		notificationOwner.setScene(scene);
		notificationOwner.setWidth(1);
		notificationOwner.setHeight(1);
		notificationOwner.setAlwaysOnTop(true);
		notificationOwner.show();
		quitNotification(notificationOwner);

		if(flag == TIME_FLAG) {
			if(todo > 1) {
				showNotificationTimePlural(todo);
			} else if (todo == 1) {
				showNotificationTimeSingular(todo);
			}
		} else if(flag == DAY_FLAG) {
			if(todo > 1) {
				showNotificationDayPlural(todo);
			} else if (todo == 1){
				showNotificationDaySingular(todo);
			}
		}
	}

	private void quitNotification(Stage stage) {
		stage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
			if (evt.getCode().equals(KeyCode.ESCAPE)) {
				stage.close();
			}
		});
	}

	private void showNotificationTimePlural(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("You have " + todo + " pending tasks in 15 minutes!"
				+ "\nPress ESC twice to return to main window." )
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(5))
		.showWarning();
	}

	private void showNotificationTimeSingular(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("You have " + todo + " pending task in 15 minutes!"
				+ "\nPress ESC twice to return to main window." )
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(5))
		.showWarning();
	}

	private void showNotificationDayPlural(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("You have " + todo + " tasks today!"
				+ "\nPress ESC twice to return to main window." )
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(5))
		.showWarning();
	}

	private void showNotificationDaySingular(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("You have " + todo + " task today!"
				+ "\nPress ESC twice to return to main window." )
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(5))
		.showWarning();
	}
}
