/**
 * Written by Gideon Pfeffer
 * Used to help with the brackets for when a user defines a new function
 */

package parser.storage;

import java.util.ArrayList;
import java.util.List;

import model.turtle.State;
import parser.interpreter.AbstractBracketAid;
import parser.tokenizer.TokenList;

public class FunctionBracketAid extends AbstractBracketAid{
	private int varBracketStart, varBracketEnd, functionStart, functionEnd, start;
	private String key;
	private CommandStorage storage;

	public FunctionBracketAid(String indicator, CommandStorage storage) {
		super(indicator);
		this.storage = storage;
	}
	
	/**
	 * resets the start index for the search of a new function
	 */
	@Override
	protected void reset() {
		start = -1;
	}
	
	/**
	 * This finds all relevant locations for where variables start/end and where their arguments are
	 */
	@Override
	protected void findIndices() {
		checkValidity();
		start = getLogoLocations(indicator).get(0);
		key = list.getLiterals().get(start + 1);
		varBracketStart = findStartBracket(start);
		varBracketEnd = findEndBracket(varBracketStart);
		functionStart = findStartBracket(varBracketEnd);
		functionEnd = findEndBracket(functionStart);
	}

	/**
	 * This removes the function definition from the TokenList
	 */
	@Override
	protected void replace() {
		addKey();
		for(int i = start; i <= functionEnd; i++){
			list.getLiterals().remove(start);
			list.getLogo().remove(start);
		}
	}
	
	/**
	 * Adds the function key to the global CommandStorage instance
	 */
	private void addKey(){
		List<String> variables = list.getLiterals().subList(varBracketStart + 1, varBracketEnd);
		FunctionObj f = new FunctionObj(key, variables);
		List<String> literalsVal = new ArrayList<>(list.getLiterals().subList(functionStart + 1, functionEnd));
		List<String> logoVal = new ArrayList<>(list.getLogo().subList(functionStart + 1, functionEnd));
		TokenList value = new TokenList(literalsVal, logoVal);
		storage.setValue(f, value);	
	}

	/**
	 * Checks to make sure that the number of left and right brackets are equal
	 */
	@Override
	protected void checkValidity() {
		if(getLogoLocations(LEFT_BRACKET).size() != 
				getLogoLocations(RIGHT_BRACKET).size()){
			throw new IllegalArgumentException("Invalid bracket syntax");
		}
	}

	/**
	 * handles the next instance of a function generation
	 */
	@Override
	public void handle(TokenList TL, State t) {
		list = TL;
		checkSyntax();
		correctList();
	}
}
