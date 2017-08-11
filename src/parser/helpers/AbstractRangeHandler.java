/**
 * Written by Gideon Pfeffer
 * Makes a list of numbers that range from the start to the end with a given increment
 */
package parser.helpers;

import java.util.List;
import java.util.Stack;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;
import parser.interfaces.RangeHandler;
import parser.reflection.TreeGenerator;
import parser.tokenizer.TokenList;

public abstract class AbstractRangeHandler implements RangeHandler{
	protected List<Integer> list;
	
	public AbstractRangeHandler(){
		
	}
	
	/**
	 * Tells the subs that they need to handle the Range
	 * in other owrds, determin the start, end, and increment
	 */
	public abstract void handleRange(State t, TokenList TL);
	
	/**
	 * @param t the TurtleState
	 * @param TL The TokenList to be compiled by the tree generator
	 * @return a Stack of TreeNode objects that have been compiled from the TokenList
	 * (was working on this right before the deadline fixing bugs and forgot to rename: should be getStack)
	 */
	protected Stack<TreeNode> getQueue(State t, TokenList TL){
		TreeGenerator TG = new TreeGenerator(TL, t);
		if(TG.getCommandStack().size() == 0) 
			throw new SLogoException("invalid syntax");
		return TG.getCommandStack();
	}
	
	/**
	 * @param increment the increment for the list being generated
	 * @param end the end number for the list being generated
	 * @param start the start number for the list being generated
	 */
	protected void makeList(double increment, double end, double start){
		list.clear();
		for(double i = start; i <= end; i += increment){
			list.add((int) i);
		}
	}
	
	/**
	 * @return the list that was generated
	 */
	public List<Integer> getList(){
		return list;
	}
	
}
