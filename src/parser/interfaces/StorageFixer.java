/**
 * Written by Gideon Pfeffer
 * Used to help normalize "fixing" all of the storage elements
 * In our case, the Storage elements include variables and functions
 * "fixing" includes saving new, undefined instances, and replace existing ones
 * with their values in the TokenList
 */

package parser.interfaces;

import parser.tokenizer.TokenList;

public interface StorageFixer {

	/**
	 * @param list the TokenList that you want the storage objects to be updated in
	 */
	void fix(TokenList list);
	
}
