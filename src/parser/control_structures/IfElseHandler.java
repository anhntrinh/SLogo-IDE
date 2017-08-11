/**
 * Written by Gideon Pfeffer
 * Used to handle the ifElse statements
 */
package parser.control_structures;

import java.util.List;
import parser.helpers.AbstractAdvancedControl;

public class IfElseHandler extends AbstractAdvancedControl{
	private static final String INDICATOR = "IfElse";
	
	public IfElseHandler(){
		super(INDICATOR);
	}

	/**
	 * replaces the ifelse statement with the corresponding if or else depending on the value
	 * ex1. ifelse 0 [ fd 5 ] [ rt 30 ] -> rt 30
	 * ex2. ifelse 1 [ fd 5 ] [ rt 30 ] -> fd 5
	 */
	@Override
	protected void replace(){
		int place = getLogoLocations(indicator).get(0);
		List<String> literalFiller = getSubList(list.getLiterals(), elseStart + 1, elseEnd);
		List<String> logoFiller = getSubList(list.getLogo(), elseStart + 1, elseEnd);
		if(evaluateExpression().pop().getValue() != 0){
			literalFiller = getSubList(list.getLiterals(), ifStart + 1, ifEnd);
			logoFiller = getSubList(list.getLogo(), ifStart + 1, ifEnd);
		}
		listMultiplier.replace(place, elseEnd, list.getLiterals(), literalFiller);
		listMultiplier.replace(place, elseEnd, list.getLogo(), logoFiller);
	}
}
