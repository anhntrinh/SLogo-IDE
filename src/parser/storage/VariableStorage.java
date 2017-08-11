/**
 * Written by Gideon Pfeffer
 * Used to store the variable keys and their double values
 */

package parser.storage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public class VariableStorage extends Observable{
	private Map<String, Double> map;
	
	public VariableStorage(){
		map = new HashMap<>();
	}
	
	/**
	 * @return the map of String variable names to double values
	 */
	public Map<String, Double> getMap(){
		return map;
	}
	
	/**
	 * @param key variable name
	 * @param val variable value
	 * Sets the value specified in the map
	 */
	public void setValue(String key, double val){
		map.put(key, val);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return all of the variable keys in the program
	 */
	public Set<String> keySet(){
		return new HashSet<>(map.keySet());
	}
}
