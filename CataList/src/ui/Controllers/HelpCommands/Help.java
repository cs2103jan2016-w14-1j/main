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
    public void setHelpId(String id) {
       this.helpId = id;
    }
        
    public String getHelpCommand() {
        return this.helpCommand;
    }
    public void setLastName(String command) {
        this.helpCommand = command;
    }

}
