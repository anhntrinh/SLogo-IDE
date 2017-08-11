/**
 * Written by Gideon Pfeffer
 * An integral part of the compiler
 * Interpreter is responsible for turning all SLogo inputs into 
 * exclusively commands and constants
 * This means no variables, functions, ifelse's, repeats's, etc
 */

package parser.interpreter;

import java.util.Collections;

import controller.SLogoException;
import model.turtle.State;
import parser.control_structures.DoTimesHandler;
import parser.control_structures.ForHandler;
import parser.control_structures.IfElseHandler;
import parser.control_structures.IfHandler;
import parser.control_structures.RepeatHandler;
import parser.interfaces.BracketHandler;
import parser.interfaces.StorageFixer;
import parser.storage.CommandHandler;
import parser.storage.TotalStorage;
import parser.tokenizer.TokenList;

public class Interpreter {
	private BracketHandler[] varLoops, regLoops;
	private TotalStorage storage;
	private StorageFixer commandHandler;

	public Interpreter(TotalStorage storage) {
		this.storage = storage;
		makeVarHandlers();
		makeRegHandlers();
	}
	
	/**
	 * Makes instances of all of the Handlers related to variables
	 */
	private void makeVarHandlers(){
		varLoops = new BracketHandler[]
				{new DoTimesHandler(Collections.unmodifiableMap(storage.getVars().getMap())), 
				new ForHandler(Collections.unmodifiableMap(storage.getVars().getMap()))};
		commandHandler = new CommandHandler(storage.getCommands());
	}
	
	/**
	 * makes all of the handlers not related to variables
	 */
	private void makeRegHandlers(){
		regLoops = new AbstractBracketHandler[] {new RepeatHandler(), new IfHandler(), new IfElseHandler()};
	}
	
	/**
	 * @param tokens cheks to make sure that the literal and logo lists are the same length
	 */
	private void checkValidity(TokenList tokens){
		if(tokens.getLiterals().size() == tokens.getLogo().size()) return;
		throw new SLogoException("Invalid Token List");
	}
	
	/**
	 * @param TL TokenList that needs to be interpreted
	 * @param state The State associated with the turtle the TokenList represents commands for
	 * this handles all of the Strings associated with variables
	 */
	public void handleVarLoops(TokenList TL, State state){
		checkValidity(TL);
		commandHandler.fix(TL);
		for(BracketHandler b: varLoops){
			b.handle(TL, state);
		}
	}
	
	/**
	 * @param TL TokenList that needs to be interpreted
	 * @param state The State associated with the turtle the TokenList represents commands for
	 * this handles all of the Strings not associated with variables
	 */
	public void handleRegLoops(TokenList TL, State state){
		checkValidity(TL);
		for(BracketHandler b: regLoops){
			b.handle(TL, state);
		}
	}
}
