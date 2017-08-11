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
public class Remainder extends LogicCommand implements Command {
	
	public Remainder(List<TreeNode> args){
		children = args;
		
	}
	
	public void execute(BackEndHandler myHandler){
		
	}
	
	public double getValue(){
		
		Double val = children.get(0).getValue()%children.get(1).getValue();  // divide by 0.0 throw error
		if (val.isNaN()||val.isInfinite()){
			throw new SLogoException(children.get(0).getValue() + " cannot be divided by 0 to get the remainder" );
		}
		else{
			return val;
		}
	}
}
