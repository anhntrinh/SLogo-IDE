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
public class SetPenSize extends TurtleCommand implements Command {
	private double pixels;
	private State myTurtleState;

	
	public SetPenSize (List<TreeNode> args, State st){
		children = args;
		pixels  = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPenSize(pixels);
	}
	
	public double getValue(){
		return pixels;
	}
}
