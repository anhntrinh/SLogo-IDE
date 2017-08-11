/**
 * Written by Gideon Pfeffer
 * Used to handle the definition of new commands and to 
 * fill in the pre-existing ones with their functions
 */

package parser.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.SLogoException;
import model.turtle.TurtleState;
import parser.interfaces.StorageFixer;
import parser.tokenizer.TokenList;

public class CommandHandler extends AbstractStorageHandler implements StorageFixer{
	private static final String COMMAND = "Command";
	private static final String NEW = "MakeUserInstruction";
	private CommandStorage storage;
	private FunctionObj current;
	private FunctionBracketAid FBA;
	private String name;
	private List<String> arguments;
	
	
	public CommandHandler(CommandStorage storage){
		super(COMMAND, NEW);
		this.storage = storage;
		FBA = new FunctionBracketAid(NEW, storage);
	}
	
	/**
	 * replaces the given functon at the specified location
	 */
	@Override
	protected void replace(int location) {
		int numArgs = current.numArgs();
		fillArgs(numArgs);
		TokenList newList = getNewList();
		list.removeAll(location, location + numArgs);
		list.addAll(newList, location);
	}
	
	/**
	 * @return the new TokenList with all of the variables from the function replaced with their repesctive values
	 */
	private TokenList getNewList(){
		Map<String, String> varMap = makeMap();
		List<String> literals = new ArrayList<>();
		List<String> logo = new ArrayList<>();
		TokenList function = storage.getMap().get(current);
		for(int i = 0; i < function.size(); i++){
			if(varMap.containsKey(function.getLiterals().get(i))){
				literals.add(varMap.get(function.getLiterals().get(i)));
				logo.add(CONSTANT);
			}
			else{
				literals.add(function.getLiterals().get(i));
				logo.add(function.getLogo().get(i));
			}
		}
		return new TokenList(literals, logo);
	}
	
	/**
	 * @return the map of arguments to their respective values ex. <":x", "10">
	 */
	private Map<String, String> makeMap(){
		Map<String, String> map = new HashMap<>();
		for(int i = 0 ; i < current.numArgs(); i++){
			map.put(current.getArgs().get(i), arguments.get(i));
		}
		return map;
	}

	/**
	 * Handles the attemt to call a function that was never defined by the user
	 */
	@Override
	protected void handleUndefined(int index) {
		name = list.getLiterals().get(index);
		boolean found = false;
		for(FunctionObj f: storage.getMap().keySet()){
			if(f.name().equals(name)){
				found = true;
				current = f;
			}
		}
		if(!found) throw new SLogoException("function never defined");
	}
	
	/**
	 * fixes (fills it with the correct Logo with repect to functions) the TokenList passed in 
	 */
	public void fix(TokenList list){
		this.list = list;
		madeNew = false;
		FBA.handle(list, new TurtleState());
		fix();
	}

	/**
	 * Used as a test to make sure that make() is never called (no undefined functions are passing through)
	 */
	@Override
	protected void make(int location) {
		throw new IllegalStateException("FunctionBracketAid didn't work");
	}
	
	/**
	 * @param numArgs the number of arguments for a function
	 * fills "arguments" with the respective values for the given function
	 * ex. numArgs = 2 "arguments" = {"5", "10"}
	 */
	private void fillArgs(int numArgs){
		arguments = new ArrayList<>();
		if(location + numArgs >= list.getLiterals().size()){
			throw new SLogoException("Invalid number of arguments");
		}
		for(int i = 1; i <= numArgs; i++){
			arguments.add(list.getLiterals().get(i + location));
			if(!list.getLogo().get(i + location).equals(CONSTANT)){
				throw new SLogoException("Illegal number of function arguments");
			}
		}
	}
}
