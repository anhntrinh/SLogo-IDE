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
public class SetPalette extends LogicCommand implements Command {
	
	public SetPalette(List<TreeNode> args){
		children = args;
	}
	
	
	public double getValue(){
		return children.get(0).getValue(); 
		
	}
	@Override
	public void execute(BackEndHandler myHandler) {
		myHandler.setPalette(children.get(0).getValue(),children.get(1).getValue(),children.get(2).getValue(),children.get(3).getValue());

	}

}
