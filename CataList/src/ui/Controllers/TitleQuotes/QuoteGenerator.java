package Controllers.TitleQuotes;

import Controllers.TitleQuotes.QuoteStorageParser;
import java.util.Date;

public class QuoteGenerator {
    
    private static final int NUM_QUOTES = 9;
    
    public static String generateRandomQuote() {
        return QuoteStorageParser.parseQuoteList(randomGeneratorByDate());
    }

    private static int randomGeneratorByDate() {
        Date date = new Date();
        return (int) date.getTime() % NUM_QUOTES;
    }
}