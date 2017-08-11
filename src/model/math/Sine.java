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
public class Sine extends LogicCommand implements Command{
	private double degrees;

	
	public Sine(List<TreeNode> args){
		children = args;
		degrees = children.get(0).getValue();
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		return Math.sin(Math.toRadians(degrees)); 
		
	}
}
