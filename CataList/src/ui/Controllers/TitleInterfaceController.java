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
    
    public void init(MainGUIController mainController) {
        main = mainController;
        quote.setText(quoteGenerator.generateRandomQuote());
    }
}