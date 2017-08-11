/**
 * Written by Gideon Pfeffer
 * Takes a String input and generates the TokenList
 * for the Parser to interpret for commands
 */

package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenListGenerator {
	Tokenizer tokenizer;
	TokenList tokens;
	String tokenizing;
	
	public TokenListGenerator(String toGenerate, String language){
		tokenizing = new String(toGenerate);
		tokens = new TokenList(new ArrayList<>(), new ArrayList<>());
		setup(language);
	}
	
	/**
	 * @param language takes the location of a language resource bundle
	 * Makes a new Tokenizer after removing the comments from the String
	 */
	private void setup(String language){
		CommentRemover comment = new CommentRemover(tokenizing);
		String withoutComments = comment.getString();
		if(withoutComments.equals("")) return;
		tokenizer = new Tokenizer(withoutComments, language);
		generate();
	}
	
	/**
	 * Generates the Logo and Literal lists to be put into the TokenList
	 */
	private void generate(){
		List<String> literal = new ArrayList<>();
		List<String> logo = new ArrayList<>();
		
		while(!tokenizer.isEmpty()){
			TokenEntry<String,String> TI = tokenizer.getToken();
			literal.add(TI.getKey());
			logo.add(TI.getValue());
		}
		tokens = new TokenList(literal, logo);
	}
	
	/**
	 * @return the TokenList that was generted by the Class with the String it was passed
	 */
	public TokenList getList(){
		return tokens;
	}

}
