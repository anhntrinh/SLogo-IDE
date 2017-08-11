/**
 * Written by Gideon Pfeffer
 * Acts as a data structure to hold both Literal and Logo translations
 * Literal being "fd" and Logo being "Forward" for example
 */

package parser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
	List<String> literals;
	List<String> logo;
	
	public TokenList(List<String> literals, List<String> logo){
		if(literals.size() != logo.size()){
			throw new IllegalStateException("Invalid TokenList");
		}
		this.literals = literals;
		this.logo = logo;
	}
	
	/**
	 * @return the List of Literal Strings
	 */
	public List<String> getLiterals(){
		return literals;
	}
	
	/**
	 * @return the List of Logo Strings
	 */
	public List<String> getLogo(){
		return logo;
	}
	
	/**
	 * @param start start index for the TokenList
	 * @param end end index for the TokenList
	 * @return a new subList instance equivalent of a TokenList starting at start and going up until, but not including end index
	 */
	public TokenList newSubList(int start, int end){
		return new TokenList(new ArrayList<>(literals.subList(start, end)),
				new ArrayList<>(logo.subList(start, end)));
	}
	
	/**
	 * @param start start index for the TokenList
	 * @param end end index for the TokenList
	 * @return the old subList instance of a TokenList starting at start and going up until, but not including end index
	 */
	public TokenList oldSubList(int start, int end){
		return new TokenList(literals.subList(start, end), logo.subList(start, end));
	}
	
	/**
	 * @param start the index where it will start removing from
	 * @param end last index it will remove (inclusive)
	 */
	public void removeAll(int start, int end){
		for(int i = start; i <= end; i++){
			literals.remove(start);
			logo.remove(start);
		}
	}
	
	/**
	 * @param list the new TokenList that will be added to the old TokenList
	 * @param loc the index where the new TokenList will be added (like adding a List<>)
	 */
	public void addAll(TokenList list, int loc){
		if(list.getLiterals().size() != list.getLogo().size()){
			throw new IllegalStateException("Invalid tokenList");
		}
		literals.addAll(loc, list.getLiterals());
		logo.addAll(loc, list.getLogo());
	}
	
	/**
	 * @return the length of the TokenList
	 */
	public int size(){
		return literals.size();
	}

}
