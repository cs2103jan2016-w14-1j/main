//@@author A0112204E
package ui.Controllers.TitleQuotes;

import ui.Controllers.TitleQuotes.QuoteStorage;
import java.util.Date;

public class QuoteGenerator {
    
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