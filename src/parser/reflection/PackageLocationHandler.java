/**
 * Written by Gideon Pfeffer
 * Used to return the location of a given class
 */

package parser.reflection;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class PackageLocationHandler {
	private ResourceBundle bundle;
	private static final String LOC = "resources/languages/Locations";

	public PackageLocationHandler(){
		definePattern();
	}

	/**
	 * gets the Resource bundle for the file containing the class locations
	 */
	private void definePattern(){
		bundle = ResourceBundle.getBundle(LOC);
	}
	
	/**
	 * @param logoKey the Logo String representation ex. "Forward"
	 * @return the package location of the given class without the class name included ex. "src.parser.reflection."
	 */
	public String getLoc(String logoKey) {
		Enumeration<String> iter = bundle.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			if(logoKey.equals(key)){
				return bundle.getString(key);
			}
		}
		return "";
	}

}
