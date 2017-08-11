/**
 * Written by Gideon Pfeffer
 * After refactoring the new Parser, this was used to test its functionality
 * Change testCode to try different tests
 */

package parser.tests;

import parser.main.NewParser;
import parser.tokenizer.ProtectedTokenList;

public class NewParserTest {
	public static void main(String args[]){
		String language = "resources/languages/English";
		String testCode = "tell [ 100 50 ] [ fd 50 ]";
		
		NewParser p = new NewParser(language);
		
		ProtectedTokenList PTL = p.parse(testCode);
		
		for(int i = 0; i < PTL.getLiterals().size(); i++){
			System.out.format("%s %s\n", PTL.getLiterals().get(i), PTL.getLogo().get(i));
		}
	}

}
