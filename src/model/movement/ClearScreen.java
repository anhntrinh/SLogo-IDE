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
public class ClearScreen extends TurtleCommand implements Command {
	private State myTurtleState;
	
	public ClearScreen(List<TreeNode> args, State st){
		children = args;
		myTurtleState = st;

	}
	
	public void execute(BackEndHandler myHandler){
		TurtleState st = (TurtleState) myTurtleState;
		st.setPosition(0,0);
		myHandler.handleReset(); 
	}
	
	public double getValue(){
		return 	Math.sqrt(Math.pow(myTurtleState.getX(), 2) + Math.pow(myTurtleState.getY(), 2));
	}
}
