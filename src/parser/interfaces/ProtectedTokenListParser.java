/**
 * Written by Gideon Pfeffer
 * An interface for all of the parsers that takes in a String input and outputs a ProtectedTokenList
 * The newParser class is an example of this
 */

package parser.interfaces;

import parser.tokenizer.ProtectedTokenList;

public interface ProtectedTokenListParser {
	
	/**
	 * @param s the String that you want to be parsed into the ProtectedTokenList
	 * @return the ProtectedTokenList that resulted from parsing the the String passed in
	 */
	ProtectedTokenList parse(String s);

}
