//@@author A0112204E
package ui.Controllers;

import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import java.util.Timer;
import java.util.TimerTask;
import ui.Controllers.MainGUIController;
import ui.Controllers.TitleQuotes.QuoteGenerator;

public class TitleInterfaceController extends QuoteGenerator{

	private static final int CHANGE_QUOTE_INTERVAL = 120000;

	@FXML
	private Text quote;

	Timer animate = new Timer(true);

	public void init(MainGUIController mainController) {
		animateQuote();
	}

	private void animateQuote() {
		animate.schedule(new TimerTask() {
			@Override
			public void run() {   	
				quote.setText(QuoteGenerator.generateRandomQuote());

				FadeTransition ft = new FadeTransition(Duration.millis(800), quote);
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.play();
			}
		}, 0, CHANGE_QUOTE_INTERVAL);
	}
}