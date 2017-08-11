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
public class Home extends TurtleCommand implements Command {

	private State myTurtleState;
		
	public Home(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;
	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPosition(0,0);
	}
	
	public double getValue(){
		return Math.sqrt(Math.pow(myTurtleState.getX() - 0, 2) + Math.pow(myTurtleState.getY() - 0 , 2));
	}
}
