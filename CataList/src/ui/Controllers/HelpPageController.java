package ui.Controllers;

import ui.Controllers.HelpCommands.Help;
import ui.Controllers.HelpCommands.HelpCommandStorageParser;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class HelpPageController {
    
    @FXML 
    private TableView<Help> helpList;
    
    private static final String COMMAND_HEADER = "Command";
    private static final String COMMAND_FORMAT_HEADER = "Format";
    
    private static final String COMMAND_ID = "commandColumn";
    private static final String FORMAT_ID = "formatColumn";
    
    private static final String COMMAND_VALUE = "helpId";
    private static final String COMMAND_FORMAT_VALUE = "helpCommand";
    
    private static final int ARRAY_INDEX_COMMAND = 0;
    private static final int ARRAY_INDEX_FORMAT = 1;
    
    private static final int COMMAND_COL_WIDTH = 200;
    private static final int COMMAND_FORMAT_COL_WIDTH = 290;
    
            
    private final ObservableList<Help> table = 
            FXCollections.observableArrayList();
    
    private ArrayList<ArrayList<String>> parsedStorage;
 
    @SuppressWarnings("unchecked")
	public void initialize() {
        TableColumn<Help, String> commandCol = createColumn(COMMAND_HEADER, COMMAND_VALUE, COMMAND_COL_WIDTH);
        TableColumn<Help, String> formatCol = createColumn(COMMAND_FORMAT_HEADER, COMMAND_FORMAT_VALUE, COMMAND_FORMAT_COL_WIDTH);
        
        helpList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        commandCol.setId(COMMAND_ID);
        formatCol.setId(FORMAT_ID);
        
        readFromStorage();
        helpList.setItems(table);
        helpList.getColumns().addAll(commandCol, formatCol);
    }

    private void readFromStorage() {
        parsedStorage = HelpCommandStorageParser.parseHelpList(); 
        for (int i = 0; i < parsedStorage.size(); i++) {
            table.add(new Help(parsedStorage.get(i).get(ARRAY_INDEX_COMMAND), parsedStorage.get(i).get(ARRAY_INDEX_FORMAT)));
        }
        parsedStorage.clear();
    }

    private TableColumn<Help, String> createColumn(String header, String value, int colWidth) {
        TableColumn<Help, String> column = new TableColumn<Help, String>(header);
        column.setMinWidth(colWidth);
        column.setCellValueFactory(new PropertyValueFactory<Help, String>(value));
        return column;
    } 
}
