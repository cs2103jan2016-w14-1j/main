//@@author A0112204E
package ui.Controllers;

import javafx.scene.Node;

public class ColorRenderer {
	
	/**
	 * This class functions to change the background color of the application  
	 * It also maintains a color toggle and parses the toggle
	 * The color change depends on the value of the toggle
	 * 
	 */
	
	private static final String INVALID_COLOR = "-fx-background: #FFFFFF"; 	
	private static final String ORANGE_COLOR = "-fx-background-color: linear-gradient(orangered, orange)"; 		
	private static final String MAROON_COLOR = "-fx-background-color: linear-gradient(maroon, goldenrod)";
	private static final String BROWN_COLOR = "-fx-background-color: linear-gradient(sienna, burlywood)";
	private static final String CORAL_COLOR = "-fx-background-color: linear-gradient(coral, plum)";
	private static final String TURQOISE_COLOR = "-fx-background-color: linear-gradient(teal, turquoise)";
	private static final String OLIVE_COLOR = "-fx-background-color: linear-gradient(darkolivegreen, mediumseagreen)";
	private static final String ORCHID_COLOR = "-fx-background-color: linear-gradient(blueviolet, firebrick)";
	
	private static final int OLIVE_FLAG = -3;
	private static final int TURQOISE_FLAG = -2;
	private static final int ORCHID_FLAG = -1;
	private static final int ORANGE_FLAG = 0; 
	private static final int CORAL_FLAG = 1;
	private static final int MAROON_FLAG = 2;
	private static final int BROWN_FLAG = 3;
	
	private static final int MAX_FLAG = 3;
	private static final int MIN_FLAG = -3;
	private static final int INIT_FLAG = 0;

	private int colorToggle;

	/**
	 * Constructor method
	 */
	public ColorRenderer() {
		colorToggle = INIT_FLAG;
	}
	
	/**
	 * Increases the color toggle flag
	 * @param node This is the background node for the application
	 */
	public void toggleColorPlus(Node node) {
		colorToggle++;
		if(colorToggle > MAX_FLAG) {
			colorToggle = MAX_FLAG;
		}
		setBackgroundColor(node);
	}
	
	/**
	 * Decreases the color toggle flag
	 * @param node This is the background node for the application
	 */
	public void toggleColorMinus(Node node) {
		colorToggle--;
		if(colorToggle < MIN_FLAG) {
			colorToggle = MIN_FLAG;
		}
		setBackgroundColor(node);
	}

	private void setBackgroundColor(Node node) {
		switch(colorToggle) {
		case OLIVE_FLAG:
			node.setStyle(OLIVE_COLOR);
			break;
		case TURQOISE_FLAG:
			node.setStyle(TURQOISE_COLOR);
			break;
		case ORCHID_FLAG:
			node.setStyle(ORCHID_COLOR);
			break;
		case ORANGE_FLAG:
			node.setStyle(ORANGE_COLOR);
			break;
		case CORAL_FLAG:	
			node.setStyle(CORAL_COLOR);
			break;
		case MAROON_FLAG:
			node.setStyle(MAROON_COLOR);
			break;
		case BROWN_FLAG:
			node.setStyle(BROWN_COLOR);
			break;
		default:
			node.setStyle(INVALID_COLOR);
			break;
		}
	}

}
