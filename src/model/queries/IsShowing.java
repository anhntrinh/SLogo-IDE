package model.queries;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
/**
 * 
 * @author Anh
 *
 */
public class IsShowing extends TurtleCommand implements Command{
	private State myTurtleState;

	public IsShowing(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
		
	public double getValue(){
		return (myTurtleState.getVisibility()?1.0:0.0);
	}

	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		
	}


}
