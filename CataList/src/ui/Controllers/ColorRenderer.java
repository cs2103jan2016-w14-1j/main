package ui.Controllers;

import javafx.scene.Node;

public class ColorRenderer {
	private static final String INVALID_COLOR = "-fx-background: #FFFFFF"; 	
	private static final String ORANGE_COLOR = "-fx-background-color: #FF9900"; 		
	private static final String MAROON_COLOR = "-fx-background: #C13100";
	private static final String BROWN_COLOR = "-fx-background: #CC6600";
	private static final String CORAL_COLOR = "-fx-background: #FF7F50";
	private static final String DARKTURQOISE_COLOR = "-fx-background: #00CED1";
	private static final String DARKCYAN_COLOR = "-fx-background: #008B8B";
	private static final String PALEBLUE_COLOR = "-fx-background: #99CCCC";
	
	private static final int DARKCYAN_FLAG = -3;
	private static final int DARKTURQOISE_FLAG = -2;
	private static final int PALEBLUE_FLAG = -1;
	private static final int ORANGE_FLAG = 0; 
	private static final int CORAL_FLAG = 1;
	private static final int MAROON_FLAG = 2;
	private static final int BROWN_FLAG = 3;
	
	private static final int MAX_FLAG = 3;
	private static final int MIN_FLAG = -3;
	private static final int INIT_FLAG = 0;

	private int colorToggle;

	public ColorRenderer() {
		colorToggle = INIT_FLAG;
	}

	public void toggleColorPlus(Node node) {
		colorToggle++;
		if(colorToggle > MAX_FLAG) {
			colorToggle = MAX_FLAG;
		}
		setBackgroundColor(node);
	}
	
	public void toggleColorMinus(Node node) {
		colorToggle--;
		if(colorToggle < MIN_FLAG) {
			colorToggle = MIN_FLAG;
		}
		setBackgroundColor(node);
	}

	private void setBackgroundColor(Node node) {
		switch(colorToggle) {
		case DARKCYAN_FLAG:
			node.setStyle(DARKCYAN_COLOR);
			break;
		case DARKTURQOISE_FLAG:
			node.setStyle(DARKTURQOISE_COLOR);
			break;
		case PALEBLUE_FLAG:
			node.setStyle(PALEBLUE_COLOR);
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
