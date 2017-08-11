/**
 * Written by Gideon Pfeffer
 * Used to help with the more advanced control structure commands like ifelse, dotimes, and for
 */

package parser.helpers;

import java.util.Stack;

import controller.SLogoException;
import model.command.TreeNode;
import parser.interpreter.AbstractBracketAid;
import parser.reflection.TreeGenerator;

public abstract class AbstractAdvancedControl extends AbstractBracketAid{
	protected static final String CONSTANT = "Constant";
	protected int ifStart, ifEnd, elseStart, elseEnd;
	
	public AbstractAdvancedControl(String indicator){
		super(indicator);
	}
	
	/**
	 * resets all of the indices of interest (the bracket start and end locations)
	 */
	@Override
	protected void reset() {
		ifStart = ifEnd = elseStart = elseEnd = -1;	
	}

	/**
	 * re-finds all of the indices of interest (the bracket start and end locations)
	 */
	@Override
	protected void findIndices() {
		ifStart = findStartBracket(getLogoLocations(indicator).get(0));
		ifEnd = findEndBracket(ifStart);
		checkBracketValidity();
		elseStart = findStartBracket(ifEnd);
		elseEnd = findEndBracket(elseStart);
	}

	/**
	 * tells the children that they need to implement some form of a replacement for the control
	 * structure or else the program will never terminate (the structures will never be handled)
	 */
	@Override
	protected abstract void replace();

	/**
	 * Checks to make sure that the end of the first bracket for advanced controls 
	 * is followed by the start of the next bracket example ifelse [ blah ] <- looks at these -> [ blah ]
	 * throws an error otherwise
	 */
	protected void checkBracketValidity() {
		if(!list.getLiterals().get(ifEnd + 1).equals(LEFT_BRACKET)){
			throw new SLogoException("Else bracket doesnt follow if bracket");
		}
	}
	
	/**
	 * @return the Stack generated from the expression of interest after passing it through the TreeGenerator
	 */
	protected Stack<TreeNode> evaluateExpression(){
		TreeGenerator TG = new TreeGenerator(
				list.newSubList(getLogoLocations(indicator).get(0) + 1, ifStart), turtle);
		Stack<TreeNode> stack = TG.getCommandStack();
		if(stack.size() == 0) 
			throw new SLogoException("No arguments provided");
		return stack;
	}

}
