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

	@FXML 
	ListView<String> todoClass;
	@FXML
	VBox classContainer;
   
	public static ObservableList<String> classes =
			FXCollections.observableArrayList();

	public void initEmptyClassList() {
		if(classes.isEmpty()) {
			todoClass.getParent().setOpacity(1);
			ScaleTransition st = new ScaleTransition(Duration.millis(700), todoClass.getParent());
			st.setFromX(0);
			st.setToX(1);
			st.setCycleCount(1);
			st.play();
			classes.add(DEFAULT);
			todoClass.setItems(classes);
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

	public void init(MainGUIController mainController) {
		main = mainController;
		todoClass.getParent().setOpacity(0);
	}
}