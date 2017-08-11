package model.math;

import java.util.List;

import controller.BackEndHandler;
import controller.SLogoException;
import model.command.Command;
import model.command.LogicCommand;
import model.command.TreeNode;
/**
 * 
 * @author Anh
 *
 */
public class Quotient extends LogicCommand implements Command {
	

	
	public Quotient(List<TreeNode> args){
		children = args;
		
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		Double val = children.get(0).getValue()/children.get(1).getValue();  // divide by 0.0 throw error
		if (val.isNaN()||val.isInfinite()){
			throw new SLogoException("Cannot divide by 0 the number " + children.get(0).getValue());
		}
		else{
			return val;
		}
	}
	
}
