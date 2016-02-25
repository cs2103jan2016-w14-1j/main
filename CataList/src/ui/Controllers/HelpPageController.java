
package Controllers;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author ericewe
 */
public class HelpPageController extends HelpCommandParser {
    
    @FXML private TableView<Help> helpList;
    @FXML private AnchorPane anchorPane;
    
    private static final String ID_HEADER = "ID";
    private static final String COMMAND_HEADER = "Command";
    private static final String ID_VALUE = "helpId";
    private static final String COMMAND_VALUE= "helpCommand";
    private static final int ID_COL_WIDTH= 125;
    private static final int COMMAND_COL_WIDTH = 370;
    
            
    private ObservableList<Help> table = 
            FXCollections.observableArrayList();
    
    private ArrayList<ArrayList<String>> parsedStorage;
 
    public void initialize() {
        TableColumn<Help, String> idCol = createColumn(ID_HEADER, ID_VALUE, ID_COL_WIDTH);
        TableColumn<Help, String> commandCol = createColumn(COMMAND_HEADER, COMMAND_VALUE, COMMAND_COL_WIDTH);
        
        helpList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        readFromStorage();
        helpList.setItems(table);
        helpList.getColumns().addAll(idCol, commandCol);
    }

    private void readFromStorage() {
        parsedStorage = HelpCommandParser.parseHelpList(); 
        for (int i = 0; i < parsedStorage.size(); i++) {
            table.add(new Help(parsedStorage.get(i).get(0), parsedStorage.get(i).get(1)));
        }
        parsedStorage.clear();
    }

    private TableColumn<Help, String> createColumn(String header, String value, int colWidth) {
        TableColumn<Help, String> idCol = new TableColumn(header);
        idCol.setMinWidth(colWidth);
        idCol.setCellValueFactory(new PropertyValueFactory(value));
        return idCol;
    }
    
}
