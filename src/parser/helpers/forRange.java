/**
 * Written by Gideon Pfeffer
 * Handles the range for the "for" command
 * ex. "for 5 10 1" picks out the start, end, and increment, and replaces accordingly
 */
package parser.helpers;

import java.util.ArrayList;
import java.util.Stack;

import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class forRange extends AbstractRangeHandler{

	public forRange(){
	}

	/**
	 * gets the Stack of compiled commands and tells the AbstractRangeHandler where
	 * to start, end, and increment given the values
	 */
	@Override
	public void handleRange(State t, TokenList TL) {
		list = new ArrayList<>();
		Stack<TreeNode> q = getQueue(t, TL);
		makeList(q.pop().getValue(), q.pop().getValue(), q.pop().getValue());	
	}
}
