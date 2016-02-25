package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import Controllers.MainGUIController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author ericewe
 */
public class CommandLineController {
    
    private MainGUIController main;
    
    private static final String INITIALIZE = "";
    private static final String INIT_FEEDBACK = "Do you have anything on your mind?";
    
    
    @FXML 
    private Text feedback;
    @FXML 
    public TextField userInput;
    
    String command = INITIALIZE;
    
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event
     */
    @FXML 
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        feedback.setText("Input entered");
        command = userInput.getText();
        userInput.clear();
        
        if(command.toLowerCase().equals("today") && !main.todoListController.tasks.isEmpty()) {
            main.todoListController.displayPending();
        } else if(command.toLowerCase().equals("complete") && !main.todoListController.completed.isEmpty()) {
            main.todoListController.displayCompleted();
        } else if(command.toLowerCase().equals("help")) {
            createHelpWindow();
        } else {
            main.todoListController.addTaskToList();
            
            if(main.classListController.classes.isEmpty()) {
                main.classListController.initEmptyClassList();
            }
        }
    }

    private void createHelpWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpPage.fxml"));
        Parent helpPageRoot = (Parent) fxmlLoader.load();
        Stage helpPageStage = new Stage();
        helpPageStage.setTitle("Help");
        Scene helpPageScene = new Scene(helpPageRoot);
        helpPageStage.setScene(helpPageScene);
        helpPageStage.show();
        
        helpPageScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
                if (evt.getCode().equals(KeyCode.ESCAPE)) {
                    helpPageStage.close();
                }
            }
        });
    }
        
    public void init(MainGUIController mainController) {
        main = mainController;
        feedback.setText(INIT_FEEDBACK);
    }
}