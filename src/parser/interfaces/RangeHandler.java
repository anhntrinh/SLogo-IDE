/**
 * Written by Gideon Pfeffer
 * Used to determine and handle (replace in the TokenList) 
 * the ranges of a specific tokenList passed
 * For example, doTimes needs the range between 1 and the last value specified
 * Another example, for needs the range between the start and end
 */

package parser.interfaces;

import model.turtle.State;
import parser.tokenizer.TokenList;

public interface RangeHandler {
	
	/**
	 * @param t State of interest for the current turtle
	 * @param TL the list that you want to be scanned for the range and handled
	 */
	void handleRange(State t, TokenList TL);

}
