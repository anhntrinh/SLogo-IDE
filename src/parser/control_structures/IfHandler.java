/**
 * Written by Gideon Pfeffer
 * Used to handle the if statements
 */
package parser.control_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parser.interpreter.AbstractBracketAid;

public class IfHandler extends AbstractBracketAid{
	private static final String INDICATOR = "If";
	private static final String LEFT_BRACKET = "ListStart";
	private static final String RIGHT_BRACKET = "ListEnd";
	private static final String IF_ELSE = "IfElse";
	private int addLoc;
	
	public IfHandler(){
		super(INDICATOR);
	}

	/**
	 * resets the location of where the else should be added
	 */
	@Override
	protected void reset() {
		addLoc = -1;
	}

	/**
	 * finds the indices of interest using the most recent indicator location
	 */
	@Override
	protected void findIndices() {
		checkValidity();
		int ifStart = findStartBracket(getLogoLocations(indicator).get(0));
		int ifEnd = findEndBracket(ifStart);
		addLoc = ifEnd + 1;
	}

	/**
	 * converts the if into an ifelse with an empty else statement
	 * ex. if 1 [ fd 50 ] -> ifelse 1 [ fd 50 ] [ ] 
	 */
	@Override
	protected void replace() {
		List<String> literalAdd = new ArrayList<>(Arrays.asList(new String[] {"[", "]"}));
		List<String> logoAdd = new ArrayList<>(Arrays.asList(new String[] {LEFT_BRACKET, RIGHT_BRACKET}));
		list.getLogo().set(getLogoLocations(indicator).get(0), IF_ELSE);
		list.getLiterals().addAll(addLoc, literalAdd);
		list.getLogo().addAll(addLoc, logoAdd);
	}
}
