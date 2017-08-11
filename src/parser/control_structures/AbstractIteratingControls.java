/**
 * Written by Gideon Pfeffer
 * Used by all of the commands that need to iterate over specific values
 * ex. doTimes and for
 */
package parser.control_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controller.SLogoException;
import parser.helpers.AbstractRangeHandler;
import parser.helpers.AbstractAdvancedControl;
import parser.helpers.Replacer;
import parser.tokenizer.TokenList;

public abstract class AbstractIteratingControls extends AbstractAdvancedControl{
	private static final String VARIABLE = "Variable";
	private String key;
	protected AbstractRangeHandler range;
	private Replacer replacer;
	protected Map<String, Double> varMap;

	public AbstractIteratingControls(String INDICATOR, Map<String, Double> varMap){
		super(INDICATOR);
		this.varMap = varMap;
		replacer = new Replacer();
	}
	
	/**
	 * replaces the variables in an interating control with their iterated over values (stored in vals)
	 */
	@Override
	protected void replace() {
		setKey();
		range.handleRange(turtle, list.newSubList(ifStart + 2, ifEnd));
		List<Integer> vals = range.getList();
		replaceEach(vals);
	}
	
	/**
	 * @param ints the List of integers to be used in replacement
	 * replaces every instance of the variable in question with its iterated over value
	 * ex. doTimes[ :x 3 ] [ fd :x ] -> fd 1 fd 2 fd 3
	 */
	private void replaceEach(List<Integer> ints){
		TokenList genericReplacement = list.newSubList(elseStart + 1, elseEnd);
		int start = getLogoLocations(indicator).get(0);
		listMultiplier.replace(start, elseEnd, list, new ArrayList<>());
		Collections.reverse(ints);
		for(Integer i : ints){
			TokenList newRep = genericReplacement.newSubList(0, genericReplacement.getLiterals().size());
			replacer.replace(newRep, key, i);
			listMultiplier.replace(start, start, list.getLiterals(), newRep.getLiterals());
			listMultiplier.replace(start, start, list.getLogo(), newRep.getLogo());
		}
	}
	
	/**
	 * sets the keys of the iterating control structure
	 * for example in doTimes [ :x 10 ] [ fd :x ], :x is the key
	 * these keys will be used later on when replacing the values with integers
	 */
	private void setKey(){
		int place = ifStart + 1;
		if(!list.getLogo().get(place).equals(VARIABLE)){
			throw new SLogoException("Make sure to use an undefined variable");
		}
		key = list.getLiterals().get(ifStart + 1);
		if(varMap.keySet().contains(key)){
			throw new SLogoException("Cannot use a variable name that already exists: " + key);
		}
	}
}
