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
public class SetTowards extends TurtleCommand implements Command{
	
	private double x;
	private double y; 
	private double degrees;

	private State myTurtleState;
	

	
	public SetTowards(List<TreeNode> args, State st){
		children = args;
		y = children.get(1).getValue();
		x = children.get(0).getValue();
		myTurtleState = st;
		degrees = Math.toDegrees(Math.atan2(y - myTurtleState.getY() , x - myTurtleState.getX()));
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setHeadAngle(degrees % 360);
	}
	
	public double getValue(){
		return Math.abs(degrees - myTurtleState.getHeadAngle());
	}
}