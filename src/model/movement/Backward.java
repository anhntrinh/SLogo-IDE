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
public class Backward extends TurtleCommand implements Command{
	private double pixels;
	private State myTurtleState;

	
	public Backward (List<TreeNode> args, State st){
		children = args;
		pixels = children.get(0).getValue();
		myTurtleState = st;
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		double newX = st.getX()-Math.cos(Math.toRadians(st.getHeadAngle()))*pixels;
		double newY = st.getY()-Math.sin(Math.toRadians(st.getHeadAngle()))*pixels;
		st.setPosition(newX, newY);
	}
	
	public double getValue(){
		return pixels;
	}
}
