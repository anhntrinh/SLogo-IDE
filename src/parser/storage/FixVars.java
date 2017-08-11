/**
 * Written by Gideon Pfeffer
 * Used to fill the variables in with their respective values
 */

package parser.storage;

import controller.SLogoException;
import parser.interfaces.StorageFixer;
import parser.tokenizer.TokenList;

public class FixVars extends AbstractStorageHandler implements StorageFixer{
	private static final String VARIABLE = "Variable";
	private static final String MAKE_VAR = "MakeVariable";
	private VariableStorage VS;
	
	public FixVars(VariableStorage VS){
		super(VARIABLE, MAKE_VAR);
		this.VS = VS;
	}
	
	/**
	 * replaces an instance of variable with its value
	 * If a new variable was defined, it trims that variable definition from the TokenList
	 */
	protected void replace(int loc){
		if(madeNew){
			trim(loc);
		}
		String key = list.getLiterals().get(location);
		list.getLiterals().set(location, VS.getMap().get(key).toString());
		list.getLogo().set(location, CONSTANT);
	}
	
	/**
	 * @param loc the location of a new variabel definition
	 * removes the "make :x" for example from the tokenList
	 */
	private void trim(int loc){
		list.getLiterals().remove(loc - 1);
		list.getLogo().remove(loc - 1);
		list.getLiterals().remove(loc);
		list.getLogo().remove(loc);
		location = loc - 1;
	}
	
	/**
	 * defines a new variable in the VariableStorage instance
	 */
	protected void make(int loc){
		handleErrors(loc);
		String key = list.getLiterals().get(loc);
		double val = Double.parseDouble(list.getLiterals().get(loc + 1));
		VS.setValue(key, val);
		madeNew = true;
	}
	
	/**
	 * starts the loop of fixing the variables
	 */
	public void fix(TokenList list){
		this.list = list;
		madeNew = false;
		fix();
	}
	
	/**
	 * @param loc the location of a variable
	 * handles an invalid variable definition
	 */
	private void handleErrors(int loc){
		if(loc == list.getLiterals().size() - 1 || !list.getLogo().get(loc + 1).equals(CONSTANT)){
			throw new SLogoException("Invalid variable definition");
		}
	}
	
	/**
	 * Handles trying to use a variable that was never defined
	 */
	protected void handleUndefined(int loc){
		if(!VS.getMap().containsKey(list.getLiterals().get(loc))){
			throw new SLogoException("Variable never defined");
		}
	}
}
