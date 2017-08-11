/**
 * Written by Gideon Pfeffer
 * Used to store the variables and commands
 */

package parser.storage;

public class TotalStorage {
	private VariableStorage vars;
	private CommandStorage commands;
	
	public TotalStorage(){
		vars = new VariableStorage();
		commands = new CommandStorage();
	}
	
	/**
	 * @return the VariableStorage object
	 */
	public VariableStorage getVars(){
		return vars;
	}
	
	/**
	 * @return the CommandStorage object
	 */
	public CommandStorage getCommands(){
		return commands;
	}

}
