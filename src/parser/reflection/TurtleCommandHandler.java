/**
 * Written by Gideon Pfeffer
 * Used to check whether or not a command is a TurtleCommand
 */

package parser.reflection;

import java.util.Enumeration;
import java.util.ResourceBundle;

import controller.SLogoException;

public class TurtleCommandHandler {
	private ResourceBundle bundle;
	private static final String TURTLE_COMMANDS = "resources/languages/TurtleCommands";
	private static final String YES = "yes";

	public TurtleCommandHandler(){
		definePattern();
	}

	/**
	 * gets the resource bundle where the data is stored
	 */
	private void definePattern(){
		bundle = ResourceBundle.getBundle(TURTLE_COMMANDS);
	}
	
	/**
	 * @param logoKey gives a Logo command String (ex "Forward")
	 * @return boolean telling whether or not that command is a TurtleCommand
	 */
	public boolean isTurtleCommand(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return bundle.getString(key).equals(YES);
			}
		}
		throw new SLogoException("invalid read");
	}


}
