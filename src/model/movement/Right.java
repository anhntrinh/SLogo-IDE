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
public class Right extends TurtleCommand implements Command{
	
	private double degrees;
	private State myTurtleState;

	
	public Right(List<TreeNode> args, State st){
		children = args;
		degrees = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;;
		st.setHeadAngle((st.getHeadAngle() - degrees) % 360);
		 
	}
	
	public double getValue(){
		return degrees;
	}
}