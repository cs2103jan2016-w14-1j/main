//@@author A0112204E
package ui.Controllers;

import javafx.geometry.Pos;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

public class NotificationRenderer {
	
	/**
	 * This class set the properties of the notification and display it to users
	 * It retrieves the number of incoming task and displays a reminder note
	 * The notification is rendered for tasks with deadlines 15 minutes prior or daily tasks when
	 * the application is loaded
	 * 
	 */

	private static final String REMINDER_HEADING = "Reminder!\n";
	private static final String REMINDER_NOTIFICATION_TIME_PLURAL = "\nYou have %1s pending tasks in 15 minutes!";
	private static final String REMINDER_NOTIFICATION_TIME_SINGULAR = "\nYou have %1s pending task in 15 minutes!";
	private static final String REMINDER_NOTIFICATION_DAY_PLURAL = "\nYou have %1s pending tasks in 15 minutes!";
	private static final String REMINDER_NOTIFICATION_DAY_SINGULAR = "\nYou have %1s pending task in 15 minutes!";
	
	private static final int TIME_FLAG = 1;
	private static final int DAY_FLAG = 0;
	private static final int NOTIFICATION_DURATION = 3;
	
	private boolean dayNotification;
	private boolean timeNotification;
	
	/**
	 * Constructor method
	 */
	public NotificationRenderer() {
		dayNotification = false;
		timeNotification = false;
	}

	/**
	 * Loads notification into view
	 * @param todo This is the number of upcoming to-dos
	 * @param flag This is the indication of whether it is a daily or time reminder
	 */
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
		.title(REMINDER_HEADING)
		.text(String.format(REMINDER_NOTIFICATION_TIME_PLURAL, todo))
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationTimeSingular(int todo) {
		Notifications.create()
		.title(REMINDER_HEADING)
		.text(String.format(REMINDER_NOTIFICATION_TIME_SINGULAR, todo))
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationDayPlural(int todo) {
		Notifications.create()
		.title(REMINDER_HEADING)
		.text(String.format(REMINDER_NOTIFICATION_DAY_PLURAL, todo))
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}

	private void showNotificationDaySingular(int todo) {
		Notifications.create()
		.title(REMINDER_HEADING)
		.text(String.format(REMINDER_NOTIFICATION_DAY_SINGULAR, todo))
		.position(Pos.TOP_RIGHT)
		.hideAfter(Duration.seconds(NOTIFICATION_DURATION))
		.showWarning();
	}
}
