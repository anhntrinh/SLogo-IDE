package model.command;
import java.util.*;
/**
 * each command extends this abstract class to be represented on the parsing tree. 
 * @author Anh
 *
 */
public abstract class TreeNode {
	protected List<TreeNode> children  = new ArrayList<TreeNode>();
	protected boolean root = true;
	
	/**
	 * this method gives the double value of the command. It is important 
	 * @return the double value of the command, default is 0.0
	 */
	public double getValue(){
		return 0.0; 
	}
	
	/**
	 * supporting method for parsing, especially handling of nested commands.
	 * @return boolean for whether this node is a root in the tree of commands
	 */
	public boolean isRoot(){
		return root;
	}
	
	/**
	 * supporting method for parsing, deroot this node
	 */
	public void deRoot(){
		root = false;
	}
	/**
	 * supporting method for parsing, deroot all child nodes.
	 */
	public void deRootAllChild(){
		for(TreeNode tn: children){
			tn.deRoot();
		}
	}
}
