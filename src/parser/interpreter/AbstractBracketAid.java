/**
 * Written by Gideon Pfeffer
 * Used to help find bracket locations and get subLists from the TokenList
 */

package parser.interpreter;
import java.util.List;
import java.util.Stack;
import controller.SLogoException;
import model.turtle.State;
import parser.helpers.ListMultiplier;
import parser.tokenizer.TokenList;

public abstract class AbstractBracketAid extends AbstractBracketHandler{
	protected ListMultiplier listMultiplier;
	protected State turtle; 
	
	public AbstractBracketAid(String indicator) {
		super(indicator);
		listMultiplier = new ListMultiplier();
	}
	
	/**
	 * handles all instances of the index passed up to the AbstractBracketHandler
	 */
	protected void correctList(){
		while(!isDone()){
			handle();
		}
	}
	
	/**
	 * Checks the syntax, sets the instance variables, and corrects the TokenList
	 */
	public void handle(TokenList TL, State t){
		list = TL;
		turtle = t;
		checkSyntax();
		correctList();
	}
	
	/**
	 * @param list the String List of interest
	 * @param start the start location
	 * @param end the end location (inclusive)
	 * @param times the amount of times you want that sublist (between start and end inclusive) multiplied 
	 * @return the List of Strings that has been multiplied
	 */
	protected List<String> getSubList(List<String> list, int start, int end, int times){
		return listMultiplier.multiplyList(
				list.subList(start, end), times);
	}
	
	/**
	 * @param list the String List of interest
	 * @param start the start location of the subList
	 * @param end the end location of the subList
	 * @return a new List comprised of the sublist between start and end (inclusive)
	 */
	protected List<String> getSubList(List<String> list, int start, int end){
		return listMultiplier.multiplyList(list.subList(start, end), 1);
	}
	
	/**
	 * @param startIndex the index of the left bracket of interest
	 * @return the index of the closing bracket ("]") associate with the opening one ("[")
	 */
	protected int findEndBracket(int startIndex){
		Stack<Integer> stack = new Stack<>();
		stack.push(startIndex);
		for(int i = startIndex + 1; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(LEFT_BRACKET))
				stack.push(i);
			if(list.getLiterals().get(i).equals(RIGHT_BRACKET)){
				stack.pop();
				if(stack.isEmpty()){
					return i;
				}
			}
		}
		throw new SLogoException("Invalid Bracket Syntax");
	}
	
	/**
	 * @param start the index to start searching from 
	 * @return the location of the next starting bracket ("[")
	 */
	protected int findStartBracket(int start){
		for(int i = start; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(LEFT_BRACKET)){
				return i;
			}
		}
		throw new SLogoException("Invalid Bracket Syntax");
	}
	
	/**
	 * tells the classes that extend that they need to reset their indices,
	 * refind the new indices, and replace (implemented differently for most commands) when handle() is called
	 */
	protected void handle(){
		reset();
		findIndices();
		replace();
	}
	
	/**
	 * Cheks to make sure that there are equal numbers of opening and closing brackets
	 * throws an error otherwise
	 */
	protected void checkValidity() {
		if(getLogoLocations(LEFT_BRACKET).size() != 
				getLogoLocations(RIGHT_BRACKET).size()){
			throw new IllegalArgumentException("Invalid bracket syntax");
		}
	}
	
	/**
	 * reset the instance variables that need to be reset for the handler
	 */
	protected abstract void reset();
	
	/**
	 * refind the new indices of interest for the current handle()
	 */
	protected abstract void findIndices();
	
	/**
	 * replace with the necessary Strings with respect to the indices found
	 * when findIndices() was called
	 */
	protected abstract void replace();
	
}