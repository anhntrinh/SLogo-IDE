/**
 * Written by Gideon Pfeffer
 * Specifies what methods need to be implements for all TreeNode Compilers
 */

package parser.interfaces;

import java.util.List;
import java.util.Queue;

import model.command.TreeNode;
import model.turtle.State;
import parser.storage.VariableStorage;
import parser.tokenizer.ProtectedTokenList;

public interface TreeNodeCompiler {
	
	/**
	 * @param state the Turtle State of interest
	 * @param list the Protected TokenList of to be interpreted by the compiler
	 * @return a Command Queue of TreeNode instances
	 */
	Queue<TreeNode> compile(State state, ProtectedTokenList list);
	
	/**
	 * @return the VariableStorage instance of the compiler
	 */
	VariableStorage getVars();
	
	/**
	 * @return a list of functions in a readable format for whomever might need/use it
	 */
	List<String> getFunctionList();

}
