/**
 * Written by Gideon Pfeffer
 * Can be used to convert a command into a readable form for the GUI
 */

package parser.function_seperator;

import java.util.ArrayList;
import java.util.List;

import parser.storage.CommandStorage;
import parser.storage.FunctionObj;

public class FunctionReconstructor {
	
	public FunctionReconstructor(){
	}
	
	/**
	 * @param command Takes a CommandStorage object
	 * @return a List full of all commands after they have been converted into a readable format
	 */
	public List<String> getCommandList(CommandStorage command){
		List<String> functionList = new ArrayList<>();
		for(FunctionObj f: command.getMap().keySet()){
			functionList.add(reconstruct(f.name(), f.getArgs()));
		}
		return functionList;
	}
	
	/**
	 * @param name takes the String name of the command
	 * @param args the List of arguments for the command
	 * @return a String representing the command in a readable format
	 */
	private String reconstruct(String name, List<String> args){
		String params = "";
		for(String s: args){
			params = params + s + " ";
		}
		return name + " [ " + params + "]";
	}
}
