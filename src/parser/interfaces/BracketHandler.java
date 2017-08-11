/**
 * Written by Gideon Pfeffer
 * Used for classes that interpret TokenLists and replace the String Literals and Logo 
 * accordingly. Examples may include, handling repeats (taking "repeat 2 [ fd 50 ]" and
 * replacing it with "fd 50 fd 50")
 * Other control structure handlers like doTimes and for need handlers as well
 */

package parser.interfaces;

import model.turtle.State;
import parser.tokenizer.TokenList;

public interface BracketHandler {
	
	/**
	 * @param list the TokenList you want to be handled (be interpreted and replaced accordingly)
	 * @param state the State associated with the current Turtle of interest
	 */
	void handle(TokenList list, State state);
	
}
