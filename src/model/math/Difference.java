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
public class Difference extends LogicCommand implements Command{
	

	
	public Difference(List<TreeNode> args){
		children = args;
		
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return Math.abs(children.get(0).getValue() - children.get(1).getValue()); 
	}
	
}
