/**
 * Written by Gideon Pfeffer
 * One of the most powerful classes in the Parser
 * Takes a String and converts it into its respective ProtectedTokenList
 */

package parser.main;

import parser.interfaces.ProtectedTokenListParser;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenListGenerator;

public class NewParser implements ProtectedTokenListParser{
	private String language;
	
	public NewParser(String language){
		this.language = language;
	}
	
	/**
	 * @param toParse the String which will be parsed
	 * @return the ProtectedTokenList generated from the String passed in
	 */
	public ProtectedTokenList parse(String toParse){	
		System.out.println("here");
		TokenListGenerator TLG = new TokenListGenerator(toParse, language);
		return new ProtectedTokenList(TLG.getList());
	}
}
