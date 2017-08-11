/**
 * Written by Gideon Pfeffer
 * Intended to be a safer form of the TokenList
 * Protects from getting off by one errors in
 * places where the original TokenList didn't
 */

package parser.tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProtectedTokenList {
	TokenList list;
	
	public ProtectedTokenList(TokenList list){
		if(list.getLogo().size() != list.getLiterals().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list = list;
	}
	
	/**
	 * @return unmodifiable List of Literals
	 */
	public List<String> getLiterals(){
		return Collections.unmodifiableList(list.getLiterals());
	}
	
	/**
	 * @return unmodifiable list of Logo
	 */
	public List<String> getLogo(){
		return Collections.unmodifiableList(list.getLogo());
	}
	
	/**
	 * @param list TokenList to add to instance
	 * @param start the start location of where the add will take place
	 */
	public void add(TokenList list, int start){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list.getLiterals().addAll(start, list.getLiterals());
		this.list.getLogo().addAll(start, list.getLogo());	
	}
	
	/**
	 * @param list adds the passed list at the end when not specified like a java List<>
	 */
	public void add(TokenList list){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.list.getLiterals().addAll(list.getLiterals());
		this.list.getLogo().addAll(list.getLogo());	
	}
	
	/**
	 * @param start the index to start removing from
	 * @param stop the index to stop at (exclusive)
	 */
	public void remove(int start, int stop){
		for(int i = start; i < stop; i++){
			list.getLiterals().remove(start);
			list.getLogo().remove(start);
		}
	}
	
	/**
	 * @param start start of the subList
	 * @param end end of the subList(exclusive)
	 * @return the old instances of that subList
	 */
	public ProtectedTokenList oldSubList(int start, int end){
		TokenList tokenList = new TokenList(list.getLiterals().subList(start, end),
				list.getLogo().subList(start, end));
		return new ProtectedTokenList(tokenList);
	}
	
	/**
	 * @param start start of the subList
	 * @param end end of the subList(exclusive)
	 * @return the new instances of that subList
	 */
	public ProtectedTokenList newSubList(int start, int end){
		TokenList tokenList = new TokenList(
				new ArrayList<>(list.getLiterals().subList(start, end)),
				new ArrayList<>(list.getLogo().subList(start, end)));
		return new ProtectedTokenList(tokenList);
	}
}
