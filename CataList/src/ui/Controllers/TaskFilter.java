package ui.Controllers;

import java.util.ArrayList;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import logic.Task;

public class TaskFilter {
	private static final String DATE_FORMAT = "dd/MM/yy";

	private static final String FLOAT_ID = "taskFloat";
	private static final String OTHERS_ID = "taskOthers";
	private static final String TOMORROW_ID = "taskTomorrow";
	private static final String TODAY_ID = "taskToday";
	private static final String OVERDUE_ID = "taskOverdue";
	private static final String NUM_ID = "numTask";

	private static final int CLASS_SPACE = 20;

	private static final String TODAY_CLASS = "Today";
	private static final String TOMORROW_CLASS = "Tomorrow";
	private static final String FLOAT_CLASS = "Float";
	private static final String OTHERS_CLASS = "Upcoming";
	private static final String OVERDUE_CLASS = "Overdue";
	
	private static final int NULL_INT = 0;

	private ArrayList<HBox> tasksToday;
	private ArrayList<HBox> tasksTomorrow;
	private ArrayList<HBox> tasksOthers;
	private ArrayList<HBox> tasksFloat;
	private ArrayList<HBox> tasksOverdue;

	private HBox taskClassToday;
	private HBox taskClassTomorrow;
	private HBox taskClassOthers;
	private HBox taskClassFloat;
	private HBox taskClassOverdue;

	private int numCompleteToday;
	private int numCompleteTomorrow;
	private int numCompleteOther;
	private int numCompleteFloat;
	private int numCompleteOverdue;
	

	public TaskFilter() {
		tasksToday = new ArrayList<HBox>();
		tasksTomorrow = new ArrayList<HBox>();
		tasksOthers= new ArrayList<HBox>();
		tasksFloat = new ArrayList<HBox>();
		tasksOverdue = new ArrayList<HBox>();

		initNumComplete();
		initClasses();
	}

	private void initNumComplete() {
		numCompleteToday = NULL_INT;
		numCompleteTomorrow = NULL_INT;
		numCompleteOther = NULL_INT;
		numCompleteFloat = NULL_INT;
		numCompleteOverdue = NULL_INT;
	}

	private void initClasses() {
		taskClassToday = new HBox(CLASS_SPACE);
		Label taskToday = new Label(TODAY_CLASS);
		taskToday.setId(TODAY_ID);
		taskClassToday.getChildren().add(taskToday);

		taskClassTomorrow = new HBox(CLASS_SPACE);
		Label taskTomorrow = new Label(TOMORROW_CLASS);
		taskTomorrow.setId(TOMORROW_ID);
		taskClassTomorrow.getChildren().add(taskTomorrow);

		taskClassOthers = new HBox(CLASS_SPACE);
		Label taskOthers = new Label(OTHERS_CLASS);
		taskOthers.setId(OTHERS_ID);
		taskClassOthers.getChildren().add(taskOthers);

		taskClassFloat = new HBox(CLASS_SPACE);
		Label taskFloat = new Label(FLOAT_CLASS);
		taskFloat.setId(FLOAT_ID);
		taskClassFloat.getChildren().add(taskFloat);
		
		taskClassOverdue = new HBox(CLASS_SPACE);
		Label taskOverdue = new Label(OVERDUE_CLASS);
		taskFloat.setId(OVERDUE_ID);
		taskClassOverdue.getChildren().add(taskOverdue);
	}

	public void addSortedClasses(ObservableList<HBox> list) {
		int taskNum = 1;
		while(taskNum <= tasksToday.size() + tasksTomorrow.size() +
				tasksFloat.size() + tasksOthers.size() + tasksOverdue.size()) {
			
			if(taskNum <= tasksOverdue.size()) {
				
				if(taskNum == 1) {
					Label taskOverdueNum = new Label(": " + (tasksOverdue.size()-1));
					taskOverdueNum.setId(NUM_ID);
					if(tasksOverdue.get(taskNum-1).getChildren().size() == 2) {
						tasksOverdue.get(taskNum-1).getChildren().set(1, taskOverdueNum);
					} else {
						tasksOverdue.get(taskNum-1).getChildren().add(taskOverdueNum);
					}
				}
				list.add(tasksOverdue.get(taskNum-1));
				
			} else if(taskNum >= tasksOverdue.size() &&
					(taskNum <= tasksOverdue.size() + tasksToday.size())) {

				if(taskNum == tasksOverdue.size() + 1) {
					Label taskTodayNum = new Label(": " + (tasksToday.size()-1));
					taskTodayNum.setId(NUM_ID);
					if(tasksToday.get(taskNum-tasksOverdue.size()-1).getChildren().size() == 2) {
						tasksToday.get(taskNum-tasksOverdue.size()-1).getChildren().set(1, taskTodayNum);
					} else {
						tasksToday.get(taskNum-tasksOverdue.size()-1).getChildren().add(taskTodayNum);
					}
				}
				list.add(tasksToday.get(taskNum-tasksOverdue.size()-1));

			} else if(taskNum >= tasksOverdue.size() + tasksToday.size() && 
					(taskNum <= tasksTomorrow.size() + tasksOverdue.size() + tasksToday.size())) {

				if(taskNum == tasksOverdue.size() + tasksToday.size() + 1) {
					Label taskTomorrowNum = new Label(": " + (tasksTomorrow.size()-1));
					taskTomorrowNum.setId(NUM_ID);
					if(tasksTomorrow.get(taskNum-tasksOverdue.size()-tasksToday.size()-1).getChildren().size() == 2) {
						tasksTomorrow.get(taskNum-tasksOverdue.size()-tasksToday.size()-1).getChildren().set(1, taskTomorrowNum);
					} else {
						tasksTomorrow.get(taskNum-tasksOverdue.size()-tasksToday.size()-1).getChildren().add(taskTomorrowNum);
					}
				}
				list.add(tasksTomorrow.get(taskNum-tasksToday.size()-1));

			} else if(taskNum >= tasksOverdue.size() + tasksToday.size() + tasksTomorrow.size() && 
					(taskNum <= tasksOthers.size() + tasksTomorrow.size() + tasksOverdue.size() + tasksToday.size())) {

				if(taskNum == tasksOverdue.size() + tasksToday.size() + tasksTomorrow.size() + 1) {
					Label taskOthersNum = new Label(": " + (tasksOthers.size()-1));
					taskOthersNum.setId(NUM_ID);
					if(tasksOthers.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-1).getChildren().size() == 2) {
						tasksOthers.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-1).getChildren().set(1, taskOthersNum);
					} else {
						tasksOthers.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-1).getChildren().add(taskOthersNum);
					}
				}
				list.add(tasksOthers.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-1));

			} else if(taskNum >= tasksOverdue.size() + tasksToday.size() + tasksTomorrow.size() + tasksOthers.size()) {

				if(taskNum == tasksOverdue.size() + tasksToday.size() + tasksTomorrow.size() + tasksOthers.size() + 1) {
					Label taskFloatNum = new Label(": " + (tasksFloat.size()-1));
					taskFloatNum.setId(NUM_ID);
					if(tasksFloat.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-tasksOthers.size()-1).getChildren().size() == 2) {
						tasksFloat.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-tasksOthers.size()-1).getChildren().set(1, taskFloatNum);
					} else {
						tasksFloat.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-tasksOthers.size()-1).getChildren().add(taskFloatNum);
					}
				}
				list.add(tasksFloat.get(taskNum-tasksOverdue.size()-tasksToday.size()-tasksTomorrow.size()-tasksOthers.size()-1));

			} 
			taskNum++;
		}
		clearAll();
	}

	public void sortTasksByClasses(Task taskObj, HBox taskRow) {
		LocalDateTime localDate = new LocalDateTime();
		String dateToday = localDate.toString(DATE_FORMAT);
		String dateTomorrow = localDate.plusDays(1).toString(DATE_FORMAT);
		
		LocalDateTime taskDate = new LocalDateTime();
		if(taskObj.get_date() != "") {
			taskDate = LocalDateTime.parse(taskObj.get_date(), DateTimeFormat.forPattern(DATE_FORMAT));
		}
		
		if(taskDate.plusDays(1).isBefore(localDate)) {
			if(!tasksOverdue.contains(taskClassOverdue)) {
				tasksOverdue.add(taskClassOverdue);
			}
			tasksOverdue.add(taskRow);
		}
		else if(taskObj.get_date().equals(dateToday) 
				|| (!taskObj.get_time().isEmpty() && taskObj.get_date().isEmpty())) {
			if(!tasksToday.contains(taskClassToday)) {
				tasksToday.add(taskClassToday);	
			}
			tasksToday.add(taskRow);
		} else if(taskObj.get_date().equals(dateTomorrow)) {
			if(!tasksTomorrow.contains(taskClassTomorrow)) {
				tasksTomorrow.add(taskClassTomorrow);
			}
			tasksTomorrow.add(taskRow);
		} else if(taskObj.get_time().isEmpty() && taskObj.get_date().isEmpty()) {
			if(!tasksFloat.contains(taskClassFloat)) {
				tasksFloat.add(taskClassFloat);
			}
			tasksFloat.add(taskRow);
		} else {
			if(!tasksOthers.contains(taskClassOthers)) {
				tasksOthers.add(taskClassOthers);
			}
			tasksOthers.add(taskRow);
		}
	}

	private void clearAll() {
		tasksToday.clear();
		tasksTomorrow.clear();
		tasksOthers.clear();
		tasksFloat.clear();
		tasksOverdue.clear();
	}
}
