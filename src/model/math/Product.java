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
public class Product extends LogicCommand implements Command{
	

	
	public Product(List<TreeNode> args){
		children = args;
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return children.get(0).getValue() * children.get(1).getValue(); 
		
	}
	
}

