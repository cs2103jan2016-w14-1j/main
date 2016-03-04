package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainGUI extends Application {
    
    private final String APP_NAME = "CataList";
    private final String GUI_PATH = "/View/MainGUI.fxml";
    private final String STYLESHEET_PATH = "/Application/Stylesheets/MainGUI.css";
    private final String ICON_PATH = "/Application/Stylesheets/Background/catalist_icon.png";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(GUI_PATH));
        
        stage.getIcons().add(new Image(ICON_PATH));
        stage.setTitle(APP_NAME);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(STYLESHEET_PATH).toExternalForm());
        stage.setScene(scene);
        stage.show();
        
        quitProgram(scene, stage);
    }

    
    private void quitProgram(Scene scene, Stage stage) {
    	
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
            if (evt.getCode().equals(KeyCode.ESCAPE)) {
            	stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
