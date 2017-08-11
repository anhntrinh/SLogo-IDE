package model.movement;

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
public class SetPosition extends TurtleCommand implements Command{
	
	private double x;
	private double y; 

	private State myTurtleState;
	

	
	public SetPosition(List<TreeNode> args, State st){
		children = args;
		x = children.get(0).getValue();
		y = children.get(1).getValue();
		myTurtleState = st;
		
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPosition(x, y);
	}
	
	public double getValue(){
		return 	Math.sqrt(Math.pow(x-myTurtleState.getX(), 2) + Math.pow(y-myTurtleState.getY(), 2));
	}
}
