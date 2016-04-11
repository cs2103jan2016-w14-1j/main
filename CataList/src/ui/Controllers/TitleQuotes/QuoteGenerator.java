//@@author A0112204E
package ui.Controllers.TitleQuotes;

import ui.Controllers.TitleQuotes.QuoteStorage;
import java.util.Date;

public class QuoteGenerator {
	
	/**
	 * This class retrieves quotes from the storage maintain by the UI
	 * It also generates a random number to which it will be send to the storage to generate
	 * the quote with that index
	 * 
	 */
    
    private static final int NUM_QUOTES = 21;
    
    /**
     * Gets a quote from storage
     * @return String This is the quote from QuoteStorage
     */
    public static String generateRandomQuote() {
        return QuoteStorage.parseQuoteList(randomGeneratorByDate());
    }

    private static int randomGeneratorByDate() {
        Date date = new Date();
        return (int) Math.abs(date.getTime() % NUM_QUOTES);
    }
}