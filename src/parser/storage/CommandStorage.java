/**
 * Written by Gideon Pfeffer
 * Used to store FunctionObj's with their associated TokenList functions
 */

package parser.storage;

import java.util.HashMap;
import java.util.Map;

import parser.tokenizer.TokenList;

public class CommandStorage {
	private Map<FunctionObj, TokenList> map;
	
	public CommandStorage(){
		map = new HashMap<>();
	}
	
	/**
	 * @return the map of FunctionObj's to TokenLists
	 */
	public Map<FunctionObj, TokenList> getMap(){
		return map;
	}
	
	/**
	 * @param key FunctionObj to store
	 * @param val TokenList that maps to key
	 * sets the <key,val> pairing in the storage
	 */
	public void setValue(FunctionObj key, TokenList val){
		map.put(key, val);
	}
}
