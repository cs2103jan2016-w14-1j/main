//@@author A01122204E
package ui.Controllers;

import javafx.geometry.Pos;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

public class NotificationRenderer {

	private static final int TIME_FLAG = 1;
	private static final int DAY_FLAG = 0;
	private static final int NOTIFICATION_DURATION = 3;
	
	private boolean dayNotification;
	private boolean timeNotification;
	
	public NotificationRenderer() {
		dayNotification = false;
		timeNotification = false;
	}

	public void loadNotification(int todo, int flag) {
		if(flag == TIME_FLAG && timeNotification == false) {
			if(todo > 1) {
				showNotificationTimePlural(todo);
			} else if (todo == 1) {
				showNotificationTimeSingular(todo);
			}
		} else if(flag == DAY_FLAG && dayNotification == false) {
			if(todo > 1) {
				showNotificationDayPlural(todo);
			} else if (todo == 1){
				showNotificationDaySingular(todo);
			}
			dayNotification = true;
		}
	}

	private void showNotificationTimePlural(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("\nYou have " + todo + " pending tasks in 15 minutes!")
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationTimeSingular(int todo) {
		Notifications.create()
		.title("Reminder!")
		.text("\nYou have " + todo + " pending task in 15 minutes!")
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationDayPlural(int todo) {
		Notifications.create()
		.title("Reminder!\n")
		.text("\nYou have " + todo + " tasks today!")
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationDaySingular(int todo) {
		Notifications.create()
		.title("Reminder!\n")
		.text("\nYou have " + todo + " task today!")
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}
}
