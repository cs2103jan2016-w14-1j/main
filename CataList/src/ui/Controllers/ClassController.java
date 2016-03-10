package Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import Controllers.MainGUIController;

public class ClassController {
    
	private MainGUIController main;
	
	private final String DEFAULT = "inbox";
	private final boolean OPEN_LIST = true;
	private final boolean CLOSE_LIST = false;

	@FXML 
	public ListView<String> todoClass;
	@FXML
	public VBox classContainer;
   
	public static ObservableList<String> classes =
			FXCollections.observableArrayList();

	public void init(MainGUIController mainController) {
		main = mainController;
		todoClass.getParent().setOpacity(0);
	}
	
	public void loopClassList() {
		if(classes.isEmpty() && !main.todoListController.tasks.isEmpty()) {
			main.classListController.initialiseClassList();
		}
		if(todoClass.getParent().getScaleX() == 0) {
			animateClassList(OPEN_LIST);
		}
	}
	
	private void initialiseClassList() {
		if(classes.isEmpty()) {
			todoClass.getParent().setOpacity(1);
			animateClassList(OPEN_LIST);
			classes.add(DEFAULT);
			todoClass.setItems(classes);
		}
	}
	
	public void closeList() {
		animateClassList(CLOSE_LIST);
	}

	private void animateClassList(boolean isOpen) {
		if(isOpen) {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoClass.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), todoClass.getParent());
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.play();
		}
	}

	/******* temp class parser *********/
	public void initCompletedClassList() {
		if(ListInterfaceController.completed.isEmpty()) {
			classes.add("complete");
		}
	}

	public void clearCompletedClassList() {
		if(ListInterfaceController.completed.isEmpty()) {
			classes.remove("complete");
		}
	}
}