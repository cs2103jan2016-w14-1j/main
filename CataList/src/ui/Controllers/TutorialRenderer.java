//@@author A0112204E
package ui.Controllers;

import java.io.IOException;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class TutorialRenderer {

	/**
	 * This class renders the tutorial mode via several FXML files
	 * It also detects key event that occurs in tutorial mode
	 * It also set/adjust the properties of the tutorial display
	 * 
	 */

	private static final int LIST_OFFSET_Y = 125;
	private static final int LIST_OFFSET_X = 0;
	private static final int CL_OFFSET_X = 540;

	private static final int TUTORIAL_ANIMATION_DURATION = 300;

	private static final String TUTORIAL_1_PATH = "/ui/View/Tutorial1.fxml";
	private static final String TUTORIAL_2_PATH = "/ui/View/Tutorial2.fxml";
	private static final String TUTORIAL_3_PATH = "/ui/View/Tutorial3.fxml";
	private static final String TUTORIAL_4_PATH = "/ui/View/Tutorial4.fxml";
	private static final String TUTORIAL_5_PATH = "/ui/View/Tutorial5.fxml";

	private static final int INTERFACE_TUTORIAL = 1;
	private static final int COMMAND_TUTORIAL = 2;
	private static final int READ_LIST_TUTORIAL = 3;
	private static final int HELP_TUTORIAL = 4;

	private PopOver commandLineTutorial;
	private PopOver listTutorial;
	private PopOver commandAddTutorial; 
	private PopOver readListTutorial;
	private PopOver helpTutorial;

	private MainGUIController main;

	public boolean tutorialFlag = false;
	private double coordinateX = 0;
	private double coordinateY = 0;
	private int currentTutorial = INTERFACE_TUTORIAL;

	/**
	 * Constructor method
	 * @param mainController The primary controller linking this and the other controllers
	 */
	public TutorialRenderer(MainGUIController mainController) {
		main = mainController;
		initPopOver();
	}

	private void initPopOver() {
		commandLineTutorial = new PopOver();
		listTutorial = new PopOver();
		commandAddTutorial = new PopOver();
		readListTutorial = new PopOver();
		helpTutorial = new PopOver();
	}

	/**
	 * Loads tutorial into view
	 * @throws IOException If there is an I/O error
	 */
	public void loadTutorial() {
		initPopOver();
		setBoundsForCurrentTutorial();
		try {
			checkCurrentTutorialPageAndLoadNextTutorial();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void checkCurrentTutorialPageAndLoadNextTutorial() throws IOException {
		if(currentTutorial == INTERFACE_TUTORIAL) {
			openCommandLineTutorial();
			openListTutorial();
			tutorialKeyHandler(listTutorial);

		} else if(currentTutorial == COMMAND_TUTORIAL) {
			openCommandAddTutorial();
			tutorialKeyHandler(commandAddTutorial);

		} else if(currentTutorial == READ_LIST_TUTORIAL) {
			openReadListTutorial();
			tutorialKeyHandler(readListTutorial);

		} else if(currentTutorial == HELP_TUTORIAL) {
			openHelpTutorial();
			tutorialKeyHandler(helpTutorial);
		}
	}

	private void openHelpTutorial() throws IOException {
		helpTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_5_PATH)));
		getCoordinates(helpTutorial, main.getCommandLine());
		setPopOverProperties(helpTutorial);
		helpTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		helpTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void openReadListTutorial() throws IOException {
		readListTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_4_PATH)));
		getCoordinates(readListTutorial, main.getList());
		setPopOverProperties(readListTutorial);
		readListTutorial.setArrowLocation(ArrowLocation.TOP_CENTER);
		readListTutorial.show(main.getList(), coordinateX+LIST_OFFSET_X, coordinateY+LIST_OFFSET_Y, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void openCommandAddTutorial() throws IOException {
		commandAddTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_3_PATH)));
		getCoordinates(commandAddTutorial, main.getCommandLine());
		setPopOverProperties(commandAddTutorial);
		commandAddTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		commandAddTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void openListTutorial() throws IOException {
		listTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_2_PATH)));
		getCoordinates(listTutorial, main.getList());
		setPopOverProperties(listTutorial);
		listTutorial.setArrowLocation(ArrowLocation.TOP_CENTER);
		listTutorial.show(main.getList(), coordinateX+LIST_OFFSET_X, coordinateY+LIST_OFFSET_Y, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void openCommandLineTutorial() throws IOException {
		commandLineTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_1_PATH)));
		getCoordinates(commandLineTutorial, main.getCommandLine());
		setPopOverProperties(commandLineTutorial);
		commandLineTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		commandLineTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void setBoundsForCurrentTutorial() {
		if(currentTutorial > HELP_TUTORIAL) {
			currentTutorial = HELP_TUTORIAL;
		} else if(currentTutorial < INTERFACE_TUTORIAL) {
			currentTutorial = INTERFACE_TUTORIAL;
		}
	}

	private void hideAll() {
		commandLineTutorial.hide(Duration.ONE);
		listTutorial.hide(Duration.ONE);
		commandAddTutorial.hide(Duration.ONE);
		readListTutorial.hide(Duration.ONE);
		helpTutorial.hide(Duration.ONE);
	}


	private void getCoordinates(PopOver target, Node targetContent) {
		Scene scene = targetContent.getScene();
		Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
		Point2D sceneCoord = new Point2D(scene.getX(), scene.getY());
		Point2D nodeCoord = targetContent.localToScene(0.0, 0.0);
		getCoordinateX(windowCoord, sceneCoord, nodeCoord);
		getCoordinateY(windowCoord, sceneCoord, nodeCoord);
	}

	private void getCoordinateY(Point2D windowCoord, Point2D sceneCoord, Point2D nodeCoord) {
		coordinateY = Math.round(windowCoord.getY() + sceneCoord.getY() + nodeCoord.getY());
	}

	private void getCoordinateX(Point2D windowCoord, Point2D sceneCoord, Point2D nodeCoord) {
		coordinateX = Math.round(windowCoord.getX() + sceneCoord.getX() + nodeCoord.getX());
	}

	private void setPopOverProperties(PopOver popOver) {
		popOver.setAutoFix(false);
		popOver.setAutoHide(false);
	}

	/**
	 * Detects key events that occurs in tutorial mode (e.g. next, previous tutorial)
	 * @param node This node is the tutorial display node
	 */
	private void tutorialKeyHandler(PopOver node) {
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.ENTER) {
					proceedToNextTutorial(event);
				} else if (event.getCode() == KeyCode.LEFT) {
					revertToPreviousTutorial(event);
				} else if (event.getCode() == KeyCode.ESCAPE) {
					tutorialFlag = false;
				} else if (event.getCode() == KeyCode.F12) {
					tutorialFlag = false;
				}
			}
		});
	}

	private void proceedToNextTutorial(KeyEvent event) {
		currentTutorial++;
		if(currentTutorial == INTERFACE_TUTORIAL+1 ||
				currentTutorial == READ_LIST_TUTORIAL+1) {
			event.consume();
		}
		hideAll();
		loadTutorial();

	}

	private void revertToPreviousTutorial(KeyEvent event) {
		currentTutorial--;
		event.consume();
		hideAll();
		loadTutorial();
	}

}
