/**
 * Written by Gideon Pfeffer
 * Handles the rnage for "doTimes"
 * a lot like the "for" hanlder, except start and increment are always 1
 */

package parser.helpers;

import java.util.ArrayList;
import java.util.Stack;

import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class doTimesRange extends AbstractRangeHandler{
	
	public doTimesRange(){
	}

	/**
	 * makes the list given the end location from the compiled commands
	 * assumes start and increment are always 1
	 */
	@Override
	public void handleRange(State t, TokenList TL) {
		list = new ArrayList<>();
		Stack<TreeNode> q = getQueue(t, TL);
		makeList(1, q.pop().getValue(), 1);	
	}

}
