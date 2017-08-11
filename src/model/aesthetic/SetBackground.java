package model.aesthetic;
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
public class SetBackground extends LogicCommand implements Command {

	public SetBackground(List<TreeNode> args){
		children = args;
	}
	
	public double getValue(){
		return children.get(0).getValue(); 
	}
	
	@Override
	public void execute(BackEndHandler myHandler) {
		myHandler.setBackground(children.get(0).getValue());
	}
}
