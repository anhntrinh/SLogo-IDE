package model.visibility;

import java.util.List;

import controller.BackEndHandler;
import model.command.Command;
import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.State;
import model.turtle.TurtleState;
/**
 * 
 * @author Anh
 *
 */
public class ShowTurtle extends TurtleCommand implements Command {
	private State myTurtleState;

	public ShowTurtle(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}

	
	public double getValue(){
		return 1.0;
	}

	@Override
	public void execute(BackEndHandler myHandler) {
		// TODO Auto-generated method stub
		TurtleState st = (TurtleState) myTurtleState;
		st.setVisibility(true);
	}
}
