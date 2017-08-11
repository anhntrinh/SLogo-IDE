package model.queries;

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
public class Turtles extends LogicCommand implements Command {

	private BackEndHandler handler; 

	public Turtles(List<TreeNode> args){
		children = args;
	}
		
	public double getValue(){
		return handler.getNumTurtle();
	}

	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		handler = myHandler;
	}

}
