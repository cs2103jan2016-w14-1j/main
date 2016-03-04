package Controllers;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class CalendarGenerator {
	
    public static WebEngine we;
    public static WebView wb;
    
    public static void renderCalendar() {
    	StringBuffer sb = new StringBuffer();

    	sb.append("<div align=\"center\">");
    	sb.append("<table class=\"calendar\"><tr>");
    	sb.append("<tr><td>S</td><td>M</td><td>T</td><td>W</td><td>T</td><td>F</td><td>S</td></tr>");
    	for(int i = 0; i < 7; i++) {
    		sb.append("<tr>");
    		for(int j = 0; j < 7; j++) {
    			sb.append("<td>"+ j + "</td>");
    		}
    		sb.append("</tr>");
    	}
    	sb.append("</table");
    	sb.append("</div>");

    	String test = sb.toString();
    	wb = new WebView();
    	we = wb.getEngine();

    	we.documentProperty().addListener(new DocListener());
    	wb.setId("test");
    	wb.setPrefHeight(1000);

    	we.loadContent(test);
    }
}
