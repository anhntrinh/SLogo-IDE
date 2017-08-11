/**
 * Written by Gideon Pfeffer
 * Used to print the tokens that are generated after passing a String into the TokenList Generator
 * Change testCode to try different tests
 */

package parser.tests;

import java.util.List;

import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;
import parser.tokenizer.TokenListGenerator;

public class TokenTest {

	public static void main(String args[]){
		String language = "resources/languages/English";
		//String testCode = "to blag [ :x :y ] [ fd :x rt :y ]";
		String testCode = "to blah [ :x ]";
		
		TokenListGenerator t = new TokenListGenerator(testCode, language);
		
		TokenList TL = t.getList();
		
		ProtectedTokenList PTL = new ProtectedTokenList(TL);
		
		List<String> literals = PTL.getLiterals();
		List<String> logo = PTL.getLogo();
		
		System.out.println(testCode + "\n====================\n");
		
		for(int i = 0; i < literals.size(); i++){
			System.out.format("%s %s\n", literals.get(i), logo.get(i));
		}
	}
	
}
