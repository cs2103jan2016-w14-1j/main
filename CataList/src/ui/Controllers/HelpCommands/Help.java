package Controllers.HelpCommands;

public class Help {
    private String helpId;
    private String helpCommand;
    
    public Help(String id, String command) {
        this.helpId = id;
        this.helpCommand = command; 
    }
 
    public String getHelpId() {
        return this.helpId;
    }
        
    public String getHelpCommand() {
        return this.helpCommand;
    }
    
    public void setHelpId(String id) {
        this.helpId = id;
     }
    
    public void setHelpCommand(String command) {
        this.helpCommand = command;
    }
}
