/**
 * Written by Gideon Pfeffer
 * Used to help the Storage parsing run smoothly
 */

package parser.storage;

import parser.tokenizer.TokenList;

public abstract class AbstractStorageHandler {
	protected static final String CONSTANT = "Constant";
	protected String indicator, newIndicator;
	protected TokenList list;
	protected int location;
	protected boolean madeNew;
	
	public AbstractStorageHandler(String indicator, String newIndicator){
		this.indicator = indicator;
		this.newIndicator = newIndicator;
	}
	
	/**
	 * @return the location of the next instance of the indicator in Logo
	 * calls make if it is a new Storage (ex. new function) instance
	 */
	private int update(){
		int loc = list.getLogo().indexOf(indicator);
		if(loc != 0 && list.getLogo().get(loc - 1).equals(newIndicator)){
			make(loc);
		}
		handleUndefined(loc);
		return loc;
	}
	
	/**
	 * @return true if the list contains no more Logo indicators
	 */
	protected boolean isDone(){
		return !list.getLogo().contains(indicator);
	}
	
	/**
	 * resets the locations and calls replace to overwrite the TokenList with 
	 * its new form
	 */
	protected void fix(){
		while(!isDone()){
			location = update();
			replace(location);
			madeNew = false;
			location = -1;
		}
	}
	
	/**
	 * @param location replaces the location passed with the correct TokenList interpretation
	 */
	protected abstract void replace(int location);
	
	/**
	 * @param index handles an undefined Storage object (like a function) at a given index
	 * Fills (cube 10 with product 10 product 10 10 for example) 
	 */
	protected abstract void handleUndefined(int index);
	
	/**
	 * @param location the location where a new storage object (like a function object) 
	 * needs to me made from
	 */
	protected abstract void make(int location);
}
