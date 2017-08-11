/**
 * Written by Gideon Pfeffer
 * Also one of the most powerful classes in the Parser directory
 * Allows for the user to get information (such as the command queue of functions)
 *  after passing in a ProtectedTokenList and a State
 */

package parser.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.function_seperator.FunctionReconstructor;
import parser.helpers.QueueSplitter;
import parser.interfaces.StorageFixer;
import parser.interfaces.TreeNodeCompiler;
import parser.interpreter.Interpreter;
import parser.reflection.TreeGenerator;
import parser.storage.FixVars;
import parser.storage.TotalStorage;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;
import parser.tokenizer.TokenList;

public class Compiler implements TreeNodeCompiler{
	private TotalStorage storage;
	private StorageFixer fixVars;
	private Interpreter IT;
	private FunctionReconstructor reconstructor;

	public Compiler(){
		reconstructor = new FunctionReconstructor();
		storage = new TotalStorage();
		fixVars = new FixVars(storage.getVars());
		IT = new Interpreter(storage);
	}
	
	/**
	 * @param list ProtectedTokenList to compile information out of
	 * @param state the State object to read the values from
	 * @return the Command Queue of TreeNodes generated using the ProtectedTokenList
	 */
	private Queue<TreeNode> interpret(ProtectedTokenList list, State state){
		TokenList TL = new TokenList(new ArrayList<>(list.getLiterals()),
				new ArrayList<>(list.getLogo()));
		IT.handleVarLoops(TL, state);
		fixVars.fix(TL);
		IT.handleRegLoops(TL, state);
		return makeTree(TL, state);
	}
	
	/**
	 * @param list the TokenList to generate the CommandQueue out of
	 * @param state The TurtleState to use when generating the Command queue
	 * @return the CommandQueue, corrected for nested commands
	 */
	private Queue<TreeNode> makeTree(TokenList list, State state){
		TreeGenerator TG = new TreeGenerator(list, state);
		QueueSplitter QS = new QueueSplitter(TG.getCommandQueue());
		return QS.getQueue();
	}
	
	/**
	 * returns the CommandQueue when passed the State and ProtectedTokenList to egenrate the queue from
	 */
	public Queue<TreeNode> compile(State state, ProtectedTokenList list){
		return interpret(list, state);
	}
	
	/**
	 * Returns the variables to be displayed on the GUI screen
	 */
	public VariableStorage getVars(){
		return storage.getVars();
	}
	
	/**
	 * the commands to be displayed on the GUI screen
	 */
	public List<String> getFunctionList(){
		return reconstructor.getCommandList(storage.getCommands());
	}
}
