//@@author A0112204E
package ui.Controllers.TitleQuotes;

import ui.Controllers.TitleQuotes.QuoteStorageParser;
import java.util.Date;

public class QuoteGenerator {
    
    private static final int NUM_QUOTES = 21;
    
    public static String generateRandomQuote() {
        return QuoteStorageParser.parseQuoteList(randomGeneratorByDate());
    }

    private static int randomGeneratorByDate() {
        Date date = new Date();
        return (int) Math.abs(date.getTime() % NUM_QUOTES);
    }
}