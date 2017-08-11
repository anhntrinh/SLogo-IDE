/**
 * Written by Gideon Pfeffer
 * Used to store the name of a function with its respective arguments
 */

package parser.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionObj{
	private String name;
	private List<String> args;
	private int numArgs;
	
	public FunctionObj(String name, List<String> args){
		this.name = name;
		this.args = new ArrayList<>(args);
		numArgs = args.size();
	}
	
	/**
	 * @return the number of arguments for the function
	 */
	public int numArgs(){
		return numArgs;
	}
	
	/**
	 * @return the name of the function
	 */
	public String name(){
		return name;
	}
	
	/**
	 * @return the list of argument names
	 */
	public List<String> getArgs(){
		return Collections.unmodifiableList(args);
	}

	/**
	 * redefines equals for function objects
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof FunctionObj)) return false;
		return this.hashCode() == o.hashCode();
	}
	
	/**
	 * Functons with the same name and same number of arguments are considered equal
	 * and will not overwrite one another
	 */
	@Override
	public int hashCode(){
		return (name + numArgs()).hashCode();
	}
}
