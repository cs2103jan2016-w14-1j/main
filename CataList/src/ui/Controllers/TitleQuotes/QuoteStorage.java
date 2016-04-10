//@@author A0112204E
package ui.Controllers.TitleQuotes;

import java.util.ArrayList;

public class QuoteStorage {

	private static final String QUOTE_ONE = 
			"“Hard work beats talent when talent fails to work hard” "
					+ "-- Kevin Durant";
	private static final String QUOTE_TWO = 
			"“Strive not to be a success, but rather be a value” "
					+ "-- Albert Einstein";
	private static final String QUOTE_THREE = 
			"“The secret of success is to do the common things uncommonly well” "
					+ "-- John D. Rockefeller";
	private static final String QUOTE_FOUR = 
			"“Two roads diverged in a wood, and I took the one less traveled by, and that has made all the difference” "
					+ "-- Robert Frost";
	private static final String QUOTE_FIVE = 
			"“Should Plan A fails, remember, you have 25 letters left” "
					+ "-- Anonymous";
	private static final String QUOTE_SIX = 
			"“I won't run away anymore... I won't go back on my word... that is my ninja way!” "
					+ "-- Uzumaki Naruto";
	private static final String QUOTE_SEVEN = 
			"“Keep your eyes on the stars, and your feet on the ground” "
					+ "-- Theodore Roosevelt";
	private static final String QUOTE_EIGHT = 
			"“Your time is limited, so don’t waste it living someone else’s life” "
					+ "-- Steve Jobs";
	private static final String QUOTE_NINE = 
			"“People often say that motivation doesn’t last. Well, neither does bathing. That’s why we recommend it daily” "
					+ "-- Zig Ziglar";
	private static final String QUOTE_TEN = 
			"“You can never cross the ocean until you have the courage to lose sight of the shore” "
					+ "-- Christopher Columbus";
	private static final String QUOTE_ELEVEN = 
			"“Remember no one can make you feel inferior without your consent” "
					+ "-- Eleanor Roosevelt";
	private static final String QUOTE_TWELVE = 
			"“Always do your best. What you plant now, you will harvest later” "
					+ "-- Og Mandino";
	private static final String QUOTE_THIRTEEN = 
			"“Just do it” "
					+ "-- Nike";
	private static final String QUOTE_FOURTEEN = 
			"“If you think you are too small to be effective, you have never been in the dark with a mosquito” "
					+ "-- Betty Reese";
	private static final String QUOTE_FIFTEEN = 
			"“Age is of no importance unless you’re a cheese” "
					+ "-- Billy Burke";
	private static final String QUOTE_SIXTEEN = 
			"“Always borrow money from a pessimist. He won’t expect it back” "
					+ "-- Oscar Wilde";
	private static final String QUOTE_SEVENTEEN = 
			"“Every walk starts with a step” "
					+ "-- Anonymous";
	private static final String QUOTE_EIGHTEEN = 
			"“In my body, where the shame gland should be, there is a second awesome gland. True story” "
					+ "-- Barney Stinson";
	private static final String QUOTE_NINETEEN = 
			"“Believe you can and you're halfway there” "
					+ "-- Theodore Roosevelt";
	private static final String QUOTE_TWENTY = 
			"“Your most unhappy customers are your greatest source of learning” "
					+ "-- Bill Gates";
	private static final String QUOTE_TWENTYONE = 
			"“Innovation distinguishes between a leader and a follower” "
					+ "-- Steve Jobs";

	private static ArrayList<String> quoteList = new ArrayList<String>();

	public static String parseQuoteList(int quoteNumber) {
		initQuoteList();
		return quoteList.get(quoteNumber);
	}

	private static void initQuoteList() {
		quoteList.add(QUOTE_ONE);
		quoteList.add(QUOTE_TWO);
		quoteList.add(QUOTE_THREE);
		quoteList.add(QUOTE_FOUR);
		quoteList.add(QUOTE_FIVE);
		quoteList.add(QUOTE_SIX);
		quoteList.add(QUOTE_SEVEN);
		quoteList.add(QUOTE_EIGHT);
		quoteList.add(QUOTE_NINE);
		quoteList.add(QUOTE_TEN);
		quoteList.add(QUOTE_ELEVEN);
		quoteList.add(QUOTE_TWELVE);
		quoteList.add(QUOTE_THIRTEEN);
		quoteList.add(QUOTE_FOURTEEN);
		quoteList.add(QUOTE_FIFTEEN);
		quoteList.add(QUOTE_SIXTEEN);
		quoteList.add(QUOTE_SEVENTEEN);
		quoteList.add(QUOTE_EIGHTEEN);
		quoteList.add(QUOTE_NINETEEN);
		quoteList.add(QUOTE_TWENTY);
		quoteList.add(QUOTE_TWENTYONE);
	}
}