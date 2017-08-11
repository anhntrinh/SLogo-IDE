package model.math;

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
public class Minus extends LogicCommand implements Command {
	

	
	public Minus(List<TreeNode> args){
		children = args;
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return -1 * children.get(0).getValue(); 
		
	}
	
}
