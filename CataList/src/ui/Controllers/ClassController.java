package ui.Controllers;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.Controllers.MainGUIController;

public class ClassController {
    
	private MainGUIController main;
	
	private final String DEFAULT = "inbox";
	private final boolean OPEN_LIST = true;
	private final boolean CLOSE_LIST = false;

	@FXML 
	private ListView<String> todoClass;
	@FXML
	private VBox classContainer;
   
	private static ObservableList<String> classes =
			FXCollections.observableArrayList();

	public void init(MainGUIController mainController) {
		main = mainController;
		todoClass.getParent().setOpacity(0);
	}
	
	public void loopClassList() {
		if(classes.isEmpty() && !main.isToDoListEmpty()) {
			openClassList();
		}
		
		if(todoClass.getParent().getScaleX() == 0) {
			animateClassList(OPEN_LIST);
		}
	}
	
	public void openClassList() {
			todoClass.getParent().setOpacity(1);
			classes.add(DEFAULT);
			todoClass.setItems(classes);
			
			animateClassList(OPEN_LIST);
	}
	
	public void closeClassList() {
		if(todoClass.getParent().getScaleX() == 1) {
			animateClassList(CLOSE_LIST);
		}
	}
	
	public ObservableList<String> getClasses() {
		return classes;
	}

	private void animateClassList(boolean isOpen) {
		if(isOpen) {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), 
					todoClass.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
		} else {
			ScaleTransition st = new ScaleTransition(Duration.millis(500), 
					todoClass.getParent());
			st.setFromX(1);
			st.setToX(0);
			st.setCycleCount(1);
			st.play();
		}
	}

	/******* temp class parser *********/
	public void initCompletedClassList() {
		if(main.isCompletedEmpty()) {
			classes.add("complete");
		}
	}

	public void clearCompletedClassList() {
		if(main.isCompletedEmpty()) {
			classes.remove("complete");
		}
	}
}