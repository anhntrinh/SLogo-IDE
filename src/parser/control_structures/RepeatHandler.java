/**
 * Written by Gideon Pfeffer
 * Used to handle the repeat statements
 */
package parser.control_structures;

import java.util.List;

import parser.helpers.AbstractAdvancedControl;

public class RepeatHandler extends AbstractAdvancedControl{
	private static final String REPEAT = "Repeat";
	private int startIndex, endIndex, times;
	
	public RepeatHandler(){
		super(REPEAT);
	}
	
	/**
	 * resets the indices of the bracket locations
	 */
	protected void reset(){
		startIndex = endIndex = times = -1;
		checkValidity();
	}
	
	/**
	 * finds the indices of the brackets using the most recent location of the indicator
	 */
	protected void findIndices(){
		ifStart = startIndex = findStartBracket(getLogoLocations(indicator).get(0));
		times = getTimes();
		endIndex = findEndBracket(startIndex);
	}
	
	/**
	 * @return the amount of times the statement will be repeated
	 */
	private int getTimes(){
		return (int) evaluateExpression().pop().getValue();
	}
	
	/**
	 * replaces repeat statement with the literal commands repeated
	 * ex. repeat 2 [ fd 50 ] -> fd 50 fd 50 
	 */
	protected void replace(){
		List<String> literalFiller = getSubList(list.getLiterals(), startIndex + 1, endIndex, times);
		List<String> logoFiller = getSubList(list.getLogo(), startIndex + 1, endIndex, times);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLiterals(), literalFiller);
		listMultiplier.replace(getLogoLocations(indicator).get(0), endIndex, list.getLogo(), logoFiller);
	}

}
