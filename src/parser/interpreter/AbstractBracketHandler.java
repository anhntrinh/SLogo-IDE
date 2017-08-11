/**
 * Written by Gideon Pfeffer
 * Used to help find Brackets and indices of the indicator in Logo commands
 */

package parser.interpreter;
import java.util.ArrayList;
import java.util.List;
import controller.SLogoException;
import model.turtle.State;
import parser.interfaces.BracketHandler;
import parser.tokenizer.TokenList;
public abstract class AbstractBracketHandler implements BracketHandler{
	protected TokenList list;
	protected String indicator;
	protected static final String LEFT_BRACKET 	= "[";
	protected static final String RIGHT_BRACKET = "]";
	
	public AbstractBracketHandler(String indicator){
		this.indicator = indicator;
	}
	
	/**
	 * Checks to make sure that there isn't an uneven number of brackets
	 */
	protected void checkSyntax(){
		if(getLiteralLocations(LEFT_BRACKET).size() !=
				getLiteralLocations(RIGHT_BRACKET).size()){
			throw new SLogoException("Invalid Syntax, uneven number of brackets");
		}
	}
	
	/**
	 * @return true when the indicator is not in the TokenList, false otherwise
	 */
	protected boolean isDone(){
		return !list.getLogo().contains(indicator);
	}
	
	/**
	 * @param str the String you want to find the location of in Logo commands
	 * @return a List of the indices of the specified String in the TokenList .getLogo()
	 */
	protected List<Integer> getLogoLocations(String str){
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < list.getLogo().size(); i++){
			if(list.getLogo().get(i).equals(str)){
				indices.add(i);
			}
		}
		return indices;
	}
	
	/**
	 * @param str the String you want to find the location of in Literal commands
	 * @return a List of the indices of the specified String in the TokenList .getLiterals()
	 */
	protected List<Integer> getLiteralLocations(String str){
		List<Integer> indices = new ArrayList<>();
		for(int i = 0; i < list.getLiterals().size(); i++){
			if(list.getLiterals().get(i).equals(str)){
				indices.add(i);
			}
		}
		return indices;
	}
	
	/**
	 * @return the TokenList passed in
	 */
	public TokenList getList(){
		return list;
	}
	
	/**
	 * handles the given indicator
	 * To be implemented by the specific interpreters
	 */
	public abstract void handle(TokenList TL, State t);
}