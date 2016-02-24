package Controllers;

import javafx.scene.text.Text;
import javafx.fxml.FXML;
import Controllers.MainGUIController;

/**
 *
 * @author ericewe
 */
public class TitleInterfaceController extends quoteGenerator {
    
    private MainGUIController main;
    
    @FXML private Text quote;
 
    /**
     * Method to handle command line input
     * @command today, complete
     * @param event 
     */
    public void init(MainGUIController mainController) {
        main = mainController;
        quote.setText(quoteGenerator.generateRandomQuote());
    }
}