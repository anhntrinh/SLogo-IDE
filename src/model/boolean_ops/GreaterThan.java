package model.boolean_ops;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;
/**
 * 
 * @author Anh
 *
 */
public class GreaterThan extends LogicCommand  implements Command{

	
	public GreaterThan(List<TreeNode> args){
		children = args;
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return (children.get(0).getValue() > children.get(1).getValue()? 1: 0); 
		
	}
	
}
