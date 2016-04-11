//@@author A0112204E
package ui.Controllers.HelpCommands;

public class Help {
	
    private String helpCommand;
    private String helpFormat;
    
    /**
     * Constructor method
     * @param command This is the command name
     * @param format This is the format for the command
     */
    public Help(String command, String format) {
        this.helpCommand = command;
        this.helpFormat = format; 
    }
 
    /**
     * Gets command name
     * @return the command name
     */
    public String getHelpCommand() {
        return this.helpCommand;
    }
        
    /**
     * Gets command format
     * @return the command format
     */
    public String getHelpFormat() {
        return this.helpFormat;
    }
    
    /**
     * Sets the command name
     * @param command This is the command name
     */
    public void setHelpCommand(String command) {
        this.helpCommand = command;
     }
    
    /**
     * Sets the command format
     * @param format This is the command format
     */
    public void setHelpFormat(String format) {
        this.helpFormat = format;
    }
}
