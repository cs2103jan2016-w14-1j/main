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
	private static final int TUTORIAL_ANIMATION_DURATION = 300;
	private static final int LIST_OFFSET_Y = 125;
	private static final int LIST_OFFSET_X = 0;

	private static final int CL_OFFSET_X = 540;

	private static final String TUTORIAL_1_PATH = "/ui/View/Tutorial1.fxml";
	private static final String TUTORIAL_2_PATH = "/ui/View/Tutorial2.fxml";
	private static final String TUTORIAL_3_PATH = "/ui/View/Tutorial3.fxml";
	private static final String TUTORIAL_4_PATH = "/ui/View/Tutorial4.fxml";
	private static final String TUTORIAL_5_PATH = "/ui/View/Tutorial5.fxml";

	private static final int INTERFACE_TUTORIAL = 1;
	private static final int COMMAND_ADD_TUTORIAL = 2;
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

	public void loadTutorial() throws IOException {
		initPopOver();
		setBoundsForCurrentTutorial();

		if(currentTutorial == INTERFACE_TUTORIAL) {
			setPropertiesForListTutorial();
			setPropertiesForCommandLineTutorial();
			tutorialKeyHandler(listTutorial);
			
		} else if(currentTutorial == COMMAND_ADD_TUTORIAL) {
			setPropertiesForCommandAddTutorial();
			tutorialKeyHandler(commandAddTutorial);
			
		} else if(currentTutorial == READ_LIST_TUTORIAL) {
			setPropertiesForReadListTutorial();
			tutorialKeyHandler(readListTutorial);

		} else if(currentTutorial == HELP_TUTORIAL) {
			setPropertiesForHelpTutorial();
			tutorialKeyHandler(helpTutorial);

		}
	}

	private void setBoundsForCurrentTutorial() {
		if(currentTutorial > HELP_TUTORIAL) {
			currentTutorial = HELP_TUTORIAL;
		} else if(currentTutorial < INTERFACE_TUTORIAL) {
			currentTutorial = INTERFACE_TUTORIAL;
		}
	}

	private void setPropertiesForHelpTutorial() throws IOException {
		helpTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_5_PATH)));
		getCoordinates(helpTutorial, main.getCommandLine());
		setPopOverProperties(helpTutorial);
		helpTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		helpTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void setPropertiesForReadListTutorial() throws IOException {
		readListTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_4_PATH)));
		getCoordinates(readListTutorial, main.getList());
		setPopOverProperties(readListTutorial);
		readListTutorial.setArrowLocation(ArrowLocation.TOP_CENTER);
		readListTutorial.show(main.getList(), coordinateX+LIST_OFFSET_X, coordinateY+LIST_OFFSET_Y, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void setPropertiesForCommandAddTutorial() throws IOException {
		commandAddTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_3_PATH)));
		getCoordinates(commandAddTutorial, main.getCommandLine());
		setPopOverProperties(commandAddTutorial);
		commandAddTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		commandAddTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void setPropertiesForCommandLineTutorial() throws IOException {
		listTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_2_PATH)));
		getCoordinates(listTutorial, main.getList());
		setPopOverProperties(listTutorial);
		listTutorial.setArrowLocation(ArrowLocation.TOP_CENTER);
		listTutorial.show(main.getList(), coordinateX+LIST_OFFSET_X, coordinateY+LIST_OFFSET_Y, Duration.millis(TUTORIAL_ANIMATION_DURATION));
	}

	private void setPropertiesForListTutorial() throws IOException {
		commandLineTutorial.setContentNode(FXMLLoader.load(getClass().getResource(TUTORIAL_1_PATH)));
		getCoordinates(commandLineTutorial, main.getCommandLine());
		setPopOverProperties(commandLineTutorial);
		commandLineTutorial.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		commandLineTutorial.show(main.getCommandLine(), coordinateX+CL_OFFSET_X, coordinateY, Duration.millis(TUTORIAL_ANIMATION_DURATION));
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
		Point2D nodeCoord = targetContent.localToScene(0, 0);
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

	private void tutorialKeyHandler(PopOver node) {
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.ENTER) {
					proceedToNextTutorial(event);
				} else if (event.getCode() == KeyCode.LEFT) {
					proceedToPreviousTutorial(event);
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
		try {
			if(currentTutorial == INTERFACE_TUTORIAL+1 ||
					currentTutorial == READ_LIST_TUTORIAL+1) {
				event.consume();
			}
			hideAll();
			loadTutorial();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void proceedToPreviousTutorial(KeyEvent event) {
		currentTutorial--;
		try {
			event.consume();
			hideAll();
			loadTutorial();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
