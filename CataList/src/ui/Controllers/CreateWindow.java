package Controllers;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreateWindow {

	private final String HELP_PAGE_PATH = "/View/HelpPage.fxml";
    private final String HELP_PAGE_NAME = "Help";
    
    public void createWindow() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(HELP_PAGE_PATH));
    	Parent helpPageRoot = (Parent) fxmlLoader.load();
    	Stage helpPageStage = new Stage();
    	helpPageStage.setTitle(HELP_PAGE_NAME);

    	FadeTransition ft = new FadeTransition(Duration.millis(1000), helpPageRoot);
    	ft.setFromValue(0.0);
    	ft.setToValue(1.0);
    	ft.play();

    	Scene helpPageScene = new Scene(helpPageRoot);
    	helpPageStage.setScene(helpPageScene);
    	helpPageStage.show();

    	quitWindow(helpPageScene, helpPageStage);
    }

    private void quitWindow(Scene helpPageScene, Stage helpPageStage) {
    	helpPageScene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> {
    		if (evt.getCode().equals(KeyCode.ESCAPE)) {
    			helpPageStage.close();
    		}
    	});
    };
}