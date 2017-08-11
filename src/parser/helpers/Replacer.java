/**
 * Written by Gideon Pfeffer
 * Used to replace a given String key in Literals with an integer value
 */

package parser.helpers;

import parser.tokenizer.TokenList;

public class Replacer {
	private static final String CONSTANT = "Constant";
	
	public Replacer(){
		
	}
	
	/**
	 * @param TL the TokenList that needs to be replaced
	 * @param key the string key to look for in the Literals
	 * @param value the value to replace that key with
	 */
	public void replace(TokenList TL, String key, int value){
		for(int i = 0; i < TL.getLiterals().size(); i++){
			String check = TL.getLiterals().get(i);
			if(check.contentEquals(key)){
				TL.getLiterals().set(i, "" + value);
				TL.getLogo().set(i, CONSTANT);
			}
		}
	}

}
