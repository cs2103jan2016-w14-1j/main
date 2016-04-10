//@@author A0112204E
package ui.Controllers.HelpCommands;

public class Help {
    private String helpCommand;
    private String helpFormat;
    
    public Help(String command, String format) {
        this.helpCommand = command;
        this.helpFormat = format; 
    }
 
    public String getHelpCommand() {
        return this.helpCommand;
    }
        
    public String getHelpFormat() {
        return this.helpFormat;
    }
    
    public void setHelpCommand(String command) {
        this.helpCommand = command;
     }
    
    public void setHelpFormat(String format) {
        this.helpFormat = format;
    }
}
