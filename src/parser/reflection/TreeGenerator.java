/**
 * Written by Gideon Pfeffer
 * Used to generate a SLogo tree from a TokenList and State
 */

package parser.reflection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import controller.SLogoException;
import model.ListEnd;
import model.ListStart;
import model.command.TreeNode;
import model.turtle.State;
import parser.tokenizer.TokenList;

public class TreeGenerator {
	private TokenList list;
	private Stack<TreeNode> parsed;
	private Stack<String> commands, logo;
	private NumArgsHandler numArgs;
	private Queue<TreeNode> commandQueue;
	private Stack<TreeNode> commandStack;
	private static final String CONSTANT = "Constant";
	private ClassGenerator CG;
	private State turtle;
	private static final String PI = "Pi";
	
	public TreeGenerator(TokenList t, State turtle){
		list = new TokenList(new ArrayList<String>(t.getLiterals()), new ArrayList<String>(t.getLogo()));
		CG = new ClassGenerator();
		this.turtle = turtle;
		initializeData();
		fillStack();
		generateTree();
	}
	
	/**
	 * initializes all of the data structures that will be used by the class
	 */
	private void initializeData(){
		parsed = new Stack<>();
		commands = new Stack<>();
		logo = new Stack<>();
		numArgs = new NumArgsHandler();
		commandQueue = new LinkedList<>();
		commandStack = new Stack<>();
	}
	
	/**
	 * fills the command and logo stacks wither the Literals and Logo from the TokenList
	 */
	private void fillStack(){
		if(!commands.isEmpty()|!logo.isEmpty()){
			commands.clear();
			logo.clear();
		}
		commands.addAll(list.getLiterals());
		logo.addAll(list.getLogo());
	}
	
	/**
	 * generates the tree using commands instance
	 */
	private void generateTree(){
		while(!commands.isEmpty()){
			grow();
		}
	}
	
	/**
	 * builds the stack and queues with TreeNode instances
	 * using the next popped element from the stacks 
	 */
	private void grow(){
		String command = commands.pop();
		String logoType = logo.pop();
		int arguments = numArgs.getNumArgs(logoType);
		TreeNode t = makeNode(command, logoType, arguments);
		t.deRootAllChild();
		if(!((t instanceof ListStart)||(t instanceof ListEnd))){
			parsed.add(t);
		}
		if(!logoType.equals(CONSTANT) && !logoType.equals(PI)){
			commandQueue.add(t);
		}
		commandStack.push(t);
	}
	
	/**
	 * @param command Literal String representation ex. "fd"
	 * @param logo Logo String representation ex. "Forward"
	 * @param arguments the number of arguments the command expects
	 * @return the TreeNode object generated from the specifications
	 */
	private TreeNode makeNode(String command, String logo, int arguments){
		List<TreeNode> list = makeList(arguments);
		if(logo.equals(CONSTANT)){
			return CG.generate(logo, Double.parseDouble(command), turtle);
		}
		else{
			return CG.generate(logo, list, turtle);
		}	
	}
	
	/**
	 * @param numArgs the number of arguments needed
	 * @return a List of length numArgs that contains the most recently added TreeNodes
	 */
	private List<TreeNode> makeList(int numArgs){
		List<TreeNode> list = new ArrayList<>();
		for(int i = 0; i < numArgs; i++){
			if(parsed.isEmpty()){
				throw new SLogoException("Empty treeNode Stack in the Tree Generator");
			}
			list.add(parsed.pop());
		}
		return list;
	}
	
	/**
	 * @return a Queue of TurtleCommands from the TokenList passed in
	 */
	public Queue<TreeNode> getCommandQueue(){
		return commandQueue;
	}
	
	/**
	 * @return a Stack of TurtleCommands from the TokenList passed in
	 */
	public Stack<TreeNode> getCommandStack(){
		return commandStack;
	}
	
	/**
	 * @return the last value on the queue to be executed
	 */
	public double getLast(){
		Queue<TreeNode> q = new LinkedList<>(commandQueue);
		while(!q.isEmpty()){
			double val = q.remove().getValue();
			if(q.isEmpty()){
				return val;
			}
		}
		throw new SLogoException("empty queue");
	}
}
