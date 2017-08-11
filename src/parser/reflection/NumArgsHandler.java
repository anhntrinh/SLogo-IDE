/**
 * Written by Gideon Pfeffer
 * Used to get the number of arguments for a given command
 */

package parser.reflection;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class NumArgsHandler {
	private ResourceBundle bundle;
	private static final String NUM_ARGS = "resources/languages/NumArgs";

	public NumArgsHandler(){
		definePattern();
	}

	/**
	 * gets the resource bundle for the file that contains the number of arguments
	 */
	private void definePattern(){
		bundle = ResourceBundle.getBundle(NUM_ARGS);
	}
	
	/**
	 * @param logoKey the Logo representation of a function ex. "Forward"
	 * @return the number of arguments as an int
	 */
	public int getNumArgs(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return Integer.parseInt(bundle.getString(key));
			}
		}
		return -1;
	}

}
