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
    
    private static final String ID_HEADER = "ID";
    private static final String COMMAND_HEADER = "Command";
    private static final String ID_VALUE = "helpId";
    private static final String COMMAND_VALUE= "helpCommand";
    
    private static int ARRAY_ID = 0;
    private static int ARRAY_COMMAND = 1;
    
    private static final int ID_COL_WIDTH = 125;
    private static final int COMMAND_COL_WIDTH = 370;
    
            
    private final ObservableList<Help> table = 
            FXCollections.observableArrayList();
    
    private ArrayList<ArrayList<String>> parsedStorage;
 
    @SuppressWarnings("unchecked")
	public void initialize() {
        TableColumn<Help, String> idCol = createColumn(ID_HEADER, ID_VALUE, ID_COL_WIDTH);
        TableColumn<Help, String> commandCol = createColumn(COMMAND_HEADER, COMMAND_VALUE, COMMAND_COL_WIDTH);
        
        helpList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        idCol.setStyle("-fx-alignment: TOP_RIGHT; -fx-text-fill: black");
        
        readFromStorage();
        helpList.setItems(table);
        helpList.getColumns().addAll(idCol, commandCol);
    }

    private void readFromStorage() {
        parsedStorage = HelpCommandStorageParser.parseHelpList(); 
        for (int i = 0; i < parsedStorage.size(); i++) {
            table.add(new Help(parsedStorage.get(i).get(ARRAY_ID), parsedStorage.get(i).get(ARRAY_COMMAND)));
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
