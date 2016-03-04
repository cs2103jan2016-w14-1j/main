package Controllers;

import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import java.util.Timer;
import java.util.TimerTask;
import Controllers.MainGUIController;
import Controllers.TitleQuotes.QuoteGenerator;

public class TitleInterfaceController extends QuoteGenerator{
    
    private MainGUIController main;
    
    @FXML private Text quote;
    
    Timer animate = new Timer(true);
    
    public void init(MainGUIController mainController) {
        main = mainController;
        animateQuote();
       
    }

	private void animateQuote() {
		animate.schedule(
        	    new TimerTask() {

        	        @Override
        	        public void run() {   	
        	        	quote.setText(QuoteGenerator.generateRandomQuote());
        	        	
        	        	FadeTransition ft = new FadeTransition(Duration.millis(800), quote);
        	    		ft.setFromValue(0.0);
        	    		ft.setToValue(1.0);
        	    		ft.play();
        	        }
        	    }, 0, 60000);
	}
}