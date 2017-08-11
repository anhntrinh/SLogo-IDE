package model.aesthetic;

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
public class SetShape  extends TurtleCommand implements Command  {
	private double index;
	private State myTurtleState;

	
	public SetShape (List<TreeNode> args, State st){
		children = args;
		index = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setShapeIndex(index);
	}
	
	public double getValue(){
		return index;
	}
}
