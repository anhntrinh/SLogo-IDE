/**
 * Written by Gideon Pfeffer
 * Used to help multiply or replace either String subLists or TokenList subLists
 */

package parser.helpers;

import java.util.ArrayList;
import java.util.List;

import parser.tokenizer.TokenList;

public class ListMultiplier {
	
	public ListMultiplier(){
		
	}
	
	/**
	 * @param toMultiply the List of Strings to multiply
	 * @param times the amount of times it should be multiplied
	 * @return the original list repeated the number of times specified
	 */
	public List<String> multiplyList(List<String> toMultiply, int times){
		List<String> multiplied = new ArrayList<>();
		for(int i = 0; i < times; i++){
			multiplied.addAll(toMultiply);
		}
		return multiplied;
	}
	
	/**
	 * @param start the start index of the list to be replaced
	 * @param end the last index to be replaced (inclusive)
	 * @param toFill the List of Strings that will be removed from/added to
	 * @param filler the new List to put in where the old list was removed
	 */
	public void replace(int start, int end, List<String> toFill, List<String> filler){
		if(start != end){
			for(int i = start; i <= end; i++){
				toFill.remove(start);
			}
		}
		toFill.addAll(start, filler);
	}
	
	/**
	 * @param start the start index of the list to be replaced
	 * @param end the last index to be replaced (inclusive)
	 * @param toFill the TokenList that will be removed from/added to
	 * @param filler the new String List to put in where the old list was removed (for Literals and Logo)
	 */
	public void replace(int start, int end, TokenList list, List<String> filler){
		replace(start, end, list.getLiterals(), filler);
		replace(start, end, list.getLogo(), filler);
	}
	
	/**
	 * @param start the start index of the list to be replaced
	 * @param end the last index to be replaced (inclusive)
	 * @param toFill the TokenList that will be removed from/added to
	 * @param filler the TokenList to put in where the old list was removed
	 */
	public void replace(int start, int end, TokenList list, TokenList filler){
		replace(start, end, list.getLiterals(), filler.getLiterals());
		replace(start, end, list.getLogo(), filler.getLogo());
	}

}
